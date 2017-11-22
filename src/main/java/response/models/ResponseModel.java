package response.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import response.data.MetaData;
import response.data.StockData;

public abstract class ResponseModel {
  private static final String OPEN = "1. open";
  private static final String HIGH = "2. high";
  private static final String LOW = "3. low";
  private static final String CLOSE = "4. close";
  private static final String VOLUME = "5. volume";

  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("YYYY-MM-DD");
  public static final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:ss");

  private final DateTimeFormatter dateTimeFormatter;

  ResponseModel(DateTimeFormatter dateTimeFormatter) {
    this.dateTimeFormatter = dateTimeFormatter;
  }

  public String getMetaDataKey() {
    return "Meta Data";
  }

  public abstract MetaData resolveMetaData(Map<String, String> metaDataResponse);

  public abstract String getStocksKey();

  public List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse) {
    List<StockData> stockData = new ArrayList<>();
    stockDataResponse.forEach((k, v) -> {
      stockData.add(new StockData(
              DateTime.parse(k, dateTimeFormatter),
              Double.parseDouble(v.get(OPEN)),
              Double.parseDouble(v.get(HIGH)),
              Double.parseDouble(v.get(LOW)),
              Double.parseDouble(v.get(CLOSE)),
              Long.parseLong(v.get(VOLUME))
      ));
    });
    return stockData;
  }
}
