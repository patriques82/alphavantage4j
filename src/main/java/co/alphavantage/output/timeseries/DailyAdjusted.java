package co.alphavantage.output.timeseries;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.timeseries.data.StockData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of daily adjusted response from api.
 */
public class DailyAdjusted {

  private final Map<String, String> metaData;
  private final List<StockData> stocks;

  private DailyAdjusted(Map<String, String> metaData, List<StockData> stocks) {
    this.metaData = metaData;
    this.stocks = stocks;
  }

  /**
   * Meta data for DailyAdjusted data
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
   * Creates {@code DailyAdjusted} instance from json.
   *
   * @param json string to parse
   * @return DailyAdjusted instance
   */
  public static DailyAdjusted from(String json)  {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code DailyAdjusted}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<DailyAdjusted> {

    @Override
    String getStockDataKey() {
      return "Time Series (Daily)";
    }

    @Override
    DailyAdjusted resolve(Map<String, String> metaData,
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
                Double.parseDouble(values.get("7. dividend amount")),
                Double.parseDouble(values.get("8. split coefficient"))

        )));
      } catch (Exception e) {
        throw new AlphaVantageException("Daily adjusted api change", e);
      }
      return new DailyAdjusted(metaData, stocks);
    }
  }
}
