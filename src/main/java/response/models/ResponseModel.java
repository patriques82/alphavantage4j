package response.models;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import response.data.time_series.MetaData;
import response.data.time_series.StockData;

import java.util.List;
import java.util.Map;

public interface ResponseModel<Data> {
  DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  default String getMetaDataKey() {
    return "Meta Data";
  }

  String getDataKey();

  Data resolve(Map<String, String> metaDataResponse, Map<String, Map<String, String>> stockDataResponse);

}
