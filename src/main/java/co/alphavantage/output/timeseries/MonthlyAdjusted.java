package co.alphavantage.output.timeseries;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.timeseries.data.StockData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of monthly adjusted response from api.
 *
 * @see TimeSeriesResponse
 */
public class MonthlyAdjusted extends TimeSeriesResponse {

  private MonthlyAdjusted(Map<String, String> metaData, List<StockData> stocks) {
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
                DateTime.parse(key, SIMPLE_DATE_FORMAT),
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
