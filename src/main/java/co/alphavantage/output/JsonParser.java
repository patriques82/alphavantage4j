package co.alphavantage.output;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class JsonParser<Data> {
  protected final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
  protected final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
  protected final DateTimeFormatter DATE_WITH_SIMPLE_TIME_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

  private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();
  protected static Gson GSON = new Gson();

  public abstract Data resolve(JsonObject rootObject) throws AlphaVantageException;

  public Data parseJson(String json) throws AlphaVantageException {
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
