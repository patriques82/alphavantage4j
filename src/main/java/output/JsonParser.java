package output;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.msiops.ground.either.Either;

public abstract class JsonParser<Data> {
  private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();
  protected static Gson GSON = new Gson();

  public abstract Data resolve(JsonObject rootObject);

  public Either<Data, Exception> parseJson(String json) {
    try {
      JsonElement jsonElement = PARSER.parse(json);
      JsonObject rootObject = jsonElement.getAsJsonObject();

      JsonElement errorMessage = rootObject.get("Error Message");
      if (errorMessage != null) {
        return Either.right(new RuntimeException(errorMessage.getAsString()));
      }

      return Either.left(resolve(rootObject));

    } catch (JsonSyntaxException e) {
      return Either.right(e);
    }
  }

}
