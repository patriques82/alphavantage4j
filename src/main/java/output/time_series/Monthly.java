package output.time_series;

import org.joda.time.DateTime;
import output.AlphaVantageException;
import output.time_series.data.StockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Monthly {

  private final Map<String, String> metaData;
  private final List<StockData> stocks;

  private Monthly(Map<String, String> metaData, List<StockData> stocks) {
    this.metaData = metaData;
    this.stocks = stocks;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<StockData> getStockData() {
    return stocks;
  }

  public static Monthly from(String json) throws AlphaVantageException {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  private static class Parser extends TimeSeriesParser<Monthly> {

    @Override
    String getStockDataKey() {
      return "Monthly Time Series";
    }

    @Override
    Monthly resolve(Map<String, String> metaData,
                    Map<String, Map<String, String>> stockData) throws AlphaVantageException {
      List<StockData> stocks = new ArrayList<>();
      try {
        stockData.forEach((key, values) -> stocks.add(new StockData(
                DateTime.parse(key, SIMPLE_DATE_FORMAT),
                Double.parseDouble(values.get("1. open")),
                Double.parseDouble(values.get("2. high")),
                Double.parseDouble(values.get("3. low")),
                Double.parseDouble(values.get("4. close")),
                Long.parseLong(values.get("5. volume"))

        )));
      } catch (Exception e) {
        throw new AlphaVantageException("Monthly api change", e);
      }
      return new Monthly(metaData, stocks);
    }
  }
}
