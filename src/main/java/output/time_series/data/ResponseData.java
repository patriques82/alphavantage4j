package output.time_series.data;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponseData {
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
  private final Map<String, String> metaData;
  private final List<StockData> stockData;

  public ResponseData(@Nullable Map<String, String> metaData) {
    this.metaData = metaData;
    this.stockData = new ArrayList<>();
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<StockData> getStockData() {
    return stockData;
  }

  public void addDaily(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Long.parseLong(values.get("5. volume"))
    ));
  }

  public void addIntraDay(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_WITH_TIME_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Long.parseLong(values.get("5. volume"))
    ));
  }

  public void addDailyAdjusted(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Double.parseDouble(values.get("5. adjusted close")),
            Long.parseLong(values.get("6. volume")),
            Double.parseDouble(values.get("7. dividend amount")),
            Double.parseDouble(values.get("8. split coefficient"))
    ));
  }

  public void addWeekly(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Long.parseLong(values.get("5. volume"))
    ));
  }

  public void addWeeklyAdjusted(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Double.parseDouble(values.get("5. adjusted close")),
            Long.parseLong(values.get("6. volume")),
            Double.parseDouble(values.get("7. dividend amount"))
    ));
  }

  public void addMonthly(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Long.parseLong(values.get("5. volume"))
    ));
  }

  public void addMonthlyAdjusted(String key, Map<String, String> values) {
    stockData.add(new StockData(
            DateTime.parse(key, DATE_FORMAT),
            Double.parseDouble(values.get("1. open")),
            Double.parseDouble(values.get("2. high")),
            Double.parseDouble(values.get("3. low")),
            Double.parseDouble(values.get("4. close")),
            Double.parseDouble(values.get("5. adjusted close")),
            Long.parseLong(values.get("6. volume")),
            Double.parseDouble(values.get("7. dividend amount"))
    ));
  }
}
