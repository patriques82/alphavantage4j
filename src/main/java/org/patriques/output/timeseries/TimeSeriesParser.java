package org.patriques.output.timeseries;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Since the format for the time series responses differ slightly but on the whole
 * have the same structure the {@code TimeSeriesParser} extracts the similarity of
 * the parsing to this class.
 *
 * @see JsonParser
 * @param <Data> the response for each individual Response, i.e Intraday, Daily etc.
 */
public abstract class TimeSeriesParser<Data> extends JsonParser<Data> {

  /**
   * The specifics of the resolution is pushed down to each response type, i.e Intraday, Daily etc.
   *
   * @param metaData the meta data
   * @param stockData the stock data
   * @return the response for each individual response, i.e Intraday, Daily etc.
   */
  abstract Data resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> stockData) ;

  /**
   * Gets the key for the stock data, this differs for each response type, i.e Intraday, Daily etc.
   * This is used by the resolve method below.
   *
   * @return the stock data key
   */
  abstract String getStockDataKey();

  @Override
  public Data resolve(JsonObject rootObject)  {
    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    try {
      Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
      Map<String, Map<String, String>> stockData = GSON.fromJson(rootObject.get(getStockDataKey()), dataType);
      return resolve(metaData, stockData);
    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("time series api change", e);
    }
  }
}
