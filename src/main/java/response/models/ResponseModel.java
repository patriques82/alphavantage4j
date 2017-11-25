package response.models;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import response.data.MetaData;
import response.data.StockData;

import java.util.List;
import java.util.Map;

public abstract class ResponseModel {

  static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  static DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  public String getMetaDataKey() {
    return "Meta Data";
  }

  public abstract MetaData resolveMetaData(Map<String, String> metaDataResponse);

  public abstract String getStocksKey();

  public abstract List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse);

}
