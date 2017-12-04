package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class TechnicalIndicatorParser<Data> extends JsonParser<Data> {

  abstract Data resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData) throws AlphaVantageException;

  abstract String getIndicatorKey();

  @Override
  public Data resolve(JsonObject rootObject) throws AlphaVantageException {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    try {
      Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
      Map<String, Map<String, String>> indicatorData = GSON.fromJson(rootObject.get(getIndicatorKey()), dataType);
      return resolve(metaData, indicatorData);
    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("technical indicators api change", e);
    }
  }
}
