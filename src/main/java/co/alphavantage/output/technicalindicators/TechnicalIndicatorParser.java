package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Since the format for the technical indicator responses differ slightly but on the whole
 * have the same structure the {@code TechnicalIndicatorParser} extracts the similarity of
 * the parsing to this class.
 *
 * @see JsonParser
 * @param <Data> the response for each individual Response, i.e MACD, EMA etc.
 */
public abstract class TechnicalIndicatorParser<Data> extends JsonParser<Data> {

  /**
   * The specifics of the resolution is pushed down to each response type, i.e MACD, EMA etc.
   *
   * @param metaData the meta data
   * @param indicatorData the indicator data
   * @return the response for each individual response, i.e MACD, EMA etc.
   */
  abstract Data resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData);

  /**
   * Gets the key for the indicators, this differs for each response type, i.e MACD, EMA etc.
   * This is used by the resolve method below.
   *
   * @return the meta data key
   */
  abstract String getIndicatorKey();

  @Override
  public Data resolve(JsonObject rootObject) {
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
