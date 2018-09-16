package org.patriques.output.digitalcurrencies;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

// TODO rename data to response
/**
 * Since the format for the digital and crypto currency responses differ slightly but on the whole
 * have the same structure the {@code DigitalCurrencyParser} extracts the similarity of
 * the parsing to this class.
 *
 * @see JsonParser
 * @param <Data> the response for each individual Response, i.e Intraday, Daily etc.
 */
abstract class DigitalCurrencyParser<Data> extends JsonParser<Data> {

  /**
   * The specifics of the resolution is pushed down to each response type, i.e Intraday, Daily etc.
   *
   * @param metaData the meta data
   * @param digitalCurrencyData the digital currency data
   * @return the response for each individual response, i.e Intraday, Daily etc.
   */
  abstract Data resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> digitalCurrencyData) ;

  /**
   * Gets the key for the digital currency data, this differs for each response type, i.e Intraday, Daily etc.
   * This is used by the resolve method below.
   *
   * @return the digital currency data key
   */
  abstract String getDigitalCurrencyDataKey();

  @Override
  public Data resolve(JsonObject rootObject)  {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    try {
      Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
      Map<String, Map<String, String>> digitalCurrencyData = GSON.fromJson(rootObject.get(getDigitalCurrencyDataKey()), dataType);
      return resolve(metaData, digitalCurrencyData);
    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("time series api change", e);
    }
  }

}
