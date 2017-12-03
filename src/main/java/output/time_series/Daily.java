package output.time_series;

import org.joda.time.DateTime;
import output.AlphaVantageException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Daily implements Response {
  private final Map<String, String> metaData;
  private final List<StockData> stockData;

  private Daily(final Map<String, String> metaData, final List<StockData> stockData) {
    this.metaData = metaData;
    this.stockData = stockData;
  }

  @Override
  public Map<String, String> getMetaData() {
    return metaData;
  }

  @Override
  public List<StockData> getStockData() {
    return stockData;
  }

  public static Daily from(String json) throws AlphaVantageException {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  private static class Parser extends TimeSeriesParser<Daily> {

    @Override
    String getStockDataKey() {
      return "Time Series (Daily)";
    }

    @Override
    Daily resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> stockData) throws AlphaVantageException {
      List<StockData> stocks = new ArrayList<>();
      try {
        stockData.forEach((key, values) -> stocks.add(new StockData(
                DateTime.parse(key, DATE_FORMAT),
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
