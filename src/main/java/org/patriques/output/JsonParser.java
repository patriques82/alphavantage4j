package org.patriques.output;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.time.format.DateTimeFormatter;

/**
 * Every Data response from a top-level call, i.e intraday, daily, sector, etc, uses this class internally
 * to resolve the generic Data response. Since all Data responses have different structures they need to
 * implement the resolve logic.
 *
 * @param <Data>
 */
public abstract class JsonParser<Data> {

  /**
   * Different formats to help child responses to interpret different dates in json
   */
  protected final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  protected final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  protected final DateTimeFormatter DATE_WITH_SIMPLE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  protected final String SIMPLE_DATE_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d";
  protected final String DATE_WITH_SIMPLE_TIME_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d\\s+[0-2]\\d:[0-5]\\d:[0-5]\\d";
  protected final String DATE_WITH_TIME_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d\\s+[0-2]\\d:[0-5]\\d:[0-5]\\d";

  private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();

  /**
   * Helper object to interpret the json.
   * @see Gson
   */
  protected static Gson GSON = new Gson();

  /**
   * The logic the helper for the response need to implement in order to interpret the json correctly.
   *
   * @param rootObject the object to interpret and build the response from
   * @return the generic Data type, i.e Intraday, Daily, Sector etc.
   */
  protected abstract Data resolve(JsonObject rootObject);

  /**
   * The top level call to interpret json.
   *
   * @param json the string to interpret
   * @return the generic Data type, i.e Intraday, Daily, Sector etc.
   */
  public Data parseJson(String json) {
    try {
      JsonElement jsonElement = PARSER.parse(json);
      JsonObject rootObject = jsonElement.getAsJsonObject();

      JsonElement errorMessage = rootObject.get("Error Message");
      if (errorMessage != null) {
        throw new AlphaVantageException(errorMessage.getAsString());
      }

      return resolve(rootObject);

    } catch (JsonSyntaxException e) {
      throw new AlphaVantageException("error parsing json", e);
    }
  }

}
