package output.time_series;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import input.time_series.Interval;
import output.JsonParser;
import output.time_series.data.ResponseData;

import java.lang.reflect.Type;
import java.util.Map;

public class IntraDay extends JsonParser<ResponseData> {
  private final Interval interval;

  public IntraDay(Interval interval) {
    this.interval = interval;
  }

  public String getDataKey() {
    return "Time Series (" + interval.getValue() + ")";
  }


  @Override
  public ResponseData resolve(JsonObject rootObject) {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    Map<String, Map<String, String>> stockData = GSON.fromJson(rootObject.get(getDataKey()), dataType);

    ResponseData response = new ResponseData(metaData);
    stockData.forEach(response::addIntraDay);

    return response;
  }
}
