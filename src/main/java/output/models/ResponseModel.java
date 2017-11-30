package output.models;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

public interface ResponseModel<Data> {
  DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  default String getMetaDataKey() {
    return "Meta Data";
  }

  String getDataKey();

  Data resolve(Map<String, String> metaDataResponse, Map<String, Map<String, String>> dataResponse);
}
