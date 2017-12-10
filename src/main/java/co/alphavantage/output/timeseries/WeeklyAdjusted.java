package co.alphavantage.output.timeseries;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.timeseries.data.StockData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of weekly adjusted response from api.
 */
public class WeeklyAdjusted {

  private final Map<String, String> metaData;
  private final List<StockData> stocks;

  private WeeklyAdjusted(Map<String, String> metaData, List<StockData> stocks) {
    this.metaData = metaData;
    this.stocks = stocks;
  }

  /**
   * Meta data for WeeklyAdjusted data
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of StockData
   *
   * @return list of {@link StockData}.
   */
  public List<StockData> getStockData() {
    return stocks;
  }

  /**
   * Creates {@code WeeklyAdjusted} instance from json.
   *
   * @param json string to parse
   * @return WeeklyAdjusted instance
   */
  public static WeeklyAdjusted from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code WeeklyAdjusted}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<WeeklyAdjusted> {

    @Override
    String getStockDataKey() {
      return "Weekly Adjusted Time Series";
    }

    @Override
    WeeklyAdjusted resolve(Map<String, String> metaData,
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
        throw new AlphaVantageException("Weekly adjusted api change", e);
      }
      return new WeeklyAdjusted(metaData, stocks);
    }

  }
}
