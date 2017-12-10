package co.alphavantage.output.timeseries;

import co.alphavantage.input.timeseries.Interval;
import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.timeseries.data.StockData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of intra day response from api.
 */
public class IntraDay {
  private final Map<String, String> metaData;
  private final List<StockData> stocks;

  private IntraDay(Map<String, String> metaData, List<StockData> stocks) {
    this.metaData = metaData;
    this.stocks = stocks;
  }

  /**
   * Meta data for IntraDay data
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
   * Creates {@code IntraDay} instance from json.
   *
   * @param json string to parse
   * @return IntraDay instance
   */
  public static IntraDay from(Interval interval, String json)  {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code IntraDay}.
   *
   * @see TimeSeriesParser
   * @see JsonParser
   */
  private static class Parser extends TimeSeriesParser<IntraDay> {
    private final Interval interval;

    Parser(Interval interval) {
      this.interval = interval;
    }

    @Override
    String getStockDataKey() {
      return "Time Series (" + interval.getValue() + ")";
    }

    @Override
    IntraDay resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> stockData)  {
      List<StockData> stocks = new ArrayList<>();
      try {
        stockData.forEach((key, values) -> stocks.add(new StockData(
                DateTime.parse(key, DATE_WITH_TIME_FORMAT),
                Double.parseDouble(values.get("1. open")),
                Double.parseDouble(values.get("2. high")),
                Double.parseDouble(values.get("3. low")),
                Double.parseDouble(values.get("4. close")),
                Long.parseLong(values.get("5. volume"))
        )));
      } catch (Exception e) {
        throw new AlphaVantageException("Intraday api change", e);
      }
      return new IntraDay(metaData, stocks);
    }
  }

}
