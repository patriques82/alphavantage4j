package org.patriques.output.quote;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.BatchStockQuotes;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.quote.data.StockQuote;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of batch stock quote response from api.
 *
 * @see BatchStockQuotes
 */
public class BatchStockQuotesResponse {
  private final Map<String, String> metaData;
  private final List<StockQuote> stockQuotes;

  private BatchStockQuotesResponse(final Map<String, String> metaData, final List<StockQuote> stockQuotes) {
    this.stockQuotes = stockQuotes;
    this.metaData = metaData;
  }

  /**
   * Meta data for response
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of StockQuote
   *
   * @return list of {@link StockQuote}
   */
  public List<StockQuote> getStockQuotes() {
    return stockQuotes;
  }

  /**
   * Creates {@code BatchStockQuotesResponse} instance from json.
   *
   * @param json string to parse
   * @return BatchStockQuotesResponse instance
   */
  public static BatchStockQuotesResponse from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code BatchStockQuotesResponse}.
   *
   * @see JsonParser
   */
  private static class Parser extends JsonParser<BatchStockQuotesResponse> {

    @Override
    protected BatchStockQuotesResponse resolve(final JsonObject rootObject) {
      Type metaDataType = new TypeToken<Map<String, String>>() {
      }.getType();
      Type dataType = new TypeToken<List<Map<String, String>>>() {
      }.getType();
      try {
        Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
        List<Map<String, String>> stockQuotes = GSON.fromJson(rootObject.get("Stock Quotes"), dataType);
        List<StockQuote> stocks = new ArrayList<>();
        stockQuotes.forEach((stockData) -> stocks.add(new StockQuote(
                stockData.get("1. symbol"),
                Double.parseDouble(stockData.get("2. price")),
                getVolume(stockData),
                LocalDateTime.parse(stockData.get("4. timestamp"), DATE_WITH_TIME_FORMAT)
        )));
        return new BatchStockQuotesResponse(metaData, stocks);
      } catch (JsonSyntaxException e) {
        throw new AlphaVantageException("BatchStockQuotes api change", e);
      }
    }

    private long getVolume(final Map<String, String> values) {
      try {
        return Long.parseLong(values.get("3. volume"));
      } catch (NumberFormatException e) {
        return 0L;
      }
    }
  }
}
