package org.patriques.output.timeseries;

import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of monthly adjusted response from api.
 *
 * @see TimeSeriesResponse
 */
public class MonthlyAdjusted extends TimeSeriesResponse {

  private MonthlyAdjusted(final Map<String, String> metaData,
                          final List<StockData> stocks) {
    super(metaData, stocks);
  }

  /**
   * Creates {@code MonthlyAdjusted} instance from json.
   *
   * @param json string to parse
   * @return MonthlyAdjusted instance
   */
  public static MonthlyAdjusted from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);  }

  /**
   * Helper class for parsing json to {@code MonthlyAdjusted}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<MonthlyAdjusted> {

    @Override
    String getStockDataKey() {
      return "Monthly Adjusted Time Series";
    }

    @Override
    MonthlyAdjusted resolve(Map<String, String> metaData,
                            Map<String, Map<String, String>> stockData)  {
      List<StockData> stocks = new ArrayList<>();
      try {
        stockData.forEach((key, values) -> stocks.add(new StockData(
                LocalDate.parse(key, SIMPLE_DATE_FORMAT).atStartOfDay(),
                Double.parseDouble(values.get("1. open")),
                Double.parseDouble(values.get("2. high")),
                Double.parseDouble(values.get("3. low")),
                Double.parseDouble(values.get("4. close")),
                Double.parseDouble(values.get("5. adjusted close")),
                Long.parseLong(values.get("6. volume")),
                Double.parseDouble(values.get("7. dividend amount"))

        )));
      } catch (Exception e) {
        throw new AlphaVantageException("Monthly adjusted api change", e);
      }
      return new MonthlyAdjusted(metaData, stocks);
    }
  }
}
