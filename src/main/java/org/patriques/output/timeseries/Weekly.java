package org.patriques.output.timeseries;

import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of weekly response from api.
 *
 * @see TimeSeriesResponse
 */
public class Weekly extends TimeSeriesResponse {

  private Weekly(final Map<String, String> metaData,
                 final List<StockData> stocks) {
    super(metaData, stocks);
  }

  /**
   * Creates {@code Weekly} instance from json.
   *
   * @param json string to parse
   * @return Weekly instance
   */
  public static Weekly from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Weekly}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<Weekly> {

    @Override
    String getStockDataKey() {
      return "Weekly Time Series";
    }

    @Override
    Weekly resolve(Map<String, String> metaData,
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
        throw new AlphaVantageException("Weekly api change", e);
      }
      return new Weekly(metaData, stocks);
    }

  }
}
