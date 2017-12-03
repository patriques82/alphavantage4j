package output;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public abstract class JsonParser<Data> {
  private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();
  protected static Gson GSON = new Gson();

  public abstract Data resolve(JsonObject rootObject);

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
