package output.time_series;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import output.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class TimeSeriesParser<Data> extends JsonParser<Data> {
  protected final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  protected final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

  abstract Data resolve(Map<String, String> metaData, Map<String, Map<String, String>> stockData);

  abstract String getStockDataKey();

  @Override
  public Data resolve(JsonObject rootObject) {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    Map<String, Map<String, String>> stockData = GSON.fromJson(rootObject.get(getStockDataKey()), dataType);

    return resolve(metaData, stockData);
  }
}
