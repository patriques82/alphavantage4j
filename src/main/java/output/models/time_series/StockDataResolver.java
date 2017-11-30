package output.models.time_series;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import output.data.time_series.StockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StockDataResolver {
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  public static List<StockData> sparse(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, DATE_FORMAT),
              Double.parseDouble(v.get("1. open")),
              Double.parseDouble(v.get("2. high")),
              Double.parseDouble(v.get("3. low")),
              Double.parseDouble(v.get("4. close")),
              Long.parseLong(v.get("5. volume"))
      ));
    });
    return stockData;
  }

  public static List<StockData> sparseWithTime(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, DATE_WITH_TIME_FORMAT),
              Double.parseDouble(v.get("1. open")),
              Double.parseDouble(v.get("2. high")),
              Double.parseDouble(v.get("3. low")),
              Double.parseDouble(v.get("4. close")),
              Long.parseLong(v.get("5. volume"))
      ));
    });
    return stockData;
  }

  public static List<StockData> fullWithoutSplitCoefficient(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, DATE_FORMAT),
              Double.parseDouble(v.get("1. open")),
              Double.parseDouble(v.get("2. high")),
              Double.parseDouble(v.get("3. low")),
              Double.parseDouble(v.get("4. close")),
              Double.parseDouble(v.get("5. adjusted close")),
              Long.parseLong(v.get("6. volume")),
              Double.parseDouble(v.get("7. dividend amount"))
      ));
    });
    return stockData;
  }

  public static List<StockData> full(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, DATE_FORMAT),
              Double.parseDouble(v.get("1. open")),
              Double.parseDouble(v.get("2. high")),
              Double.parseDouble(v.get("3. low")),
              Double.parseDouble(v.get("4. close")),
              Double.parseDouble(v.get("5. adjusted close")),
              Long.parseLong(v.get("6. volume")),
              Double.parseDouble(v.get("7. dividend amount")),
              Double.parseDouble(v.get("8. split coefficient"))
      ));
    });
    return stockData;
  }

}
