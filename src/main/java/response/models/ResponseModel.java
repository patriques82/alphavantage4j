package response.models;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import response.data.MetaData;
import response.data.StockData;

import java.util.List;
import java.util.Map;

public interface ResponseModel {

  DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  default String getMetaDataKey() {
    return "Meta Data";
  }

  MetaData resolveMetaData(Map<String, String> metaDataResponse);

  String getStocksKey();

  List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse);

}
