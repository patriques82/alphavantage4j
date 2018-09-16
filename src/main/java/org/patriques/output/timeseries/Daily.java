package org.patriques.output.timeseries;

import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of daily response from api.
 *
 * @see TimeSeriesResponse
 */
public class Daily extends TimeSeriesResponse {

  private Daily(final Map<String, String> metaData,
                final List<StockData> stockData) {
    super(metaData, stockData);
  }

  /**
   * Creates {@code Daily} instance from json.
   *
   * @param json string to parse
   * @return Daily instance
   */
  public static Daily from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Daily}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<Daily> {

    @Override
    String getStockDataKey() {
      return "Time Series (Daily)";
    }

    @Override
    Daily resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> stockData)  {
      List<StockData> stocks = new ArrayList<>();
      try {
        stockData.forEach((key, values) -> stocks.add(new StockData(
                LocalDate.parse(key, SIMPLE_DATE_FORMAT).atStartOfDay(),
                Double.parseDouble(values.get("1. open")),
                Double.parseDouble(values.get("2. high")),
                Double.parseDouble(values.get("3. low")),
                Double.parseDouble(values.get("4. close")),
                Long.parseLong(values.get("5. volume"))
        )));
      } catch (Exception e) {
        throw new AlphaVantageException("Daily api change", e);
      }
      return new Daily(metaData, stocks);
    }
  }

}
