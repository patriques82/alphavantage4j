import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.msiops.ground.either.Either;
import response.models.ResponseModel;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Helper class to parse Json response from ApiConnector
 */
class JsonParser {
  private static final Gson GSON = new Gson();
  private static final com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();

  /**
   * Parse json according to response model
   * @param json the string to parse
   * @param responseModel the model that describes the response.
   * @return either a successful response (left) or an exception (right)
   */
  static <Data> Either<Data, Exception> parseJson(String json, ResponseModel<Data> responseModel) {
    try {
      JsonElement jsonElement = PARSER.parse(json);
      JsonObject rootObject = jsonElement.getAsJsonObject();

      JsonElement errorMessage = rootObject.get("Error Message");
      if (errorMessage != null) {
        return Either.right(new RuntimeException(errorMessage.getAsString()));
      }

      Type metaDataType = new TypeToken<Map<String, String>>() {
      }.getType();
      Map<String, String> metaDataResponse = GSON.fromJson(rootObject.get(responseModel.getMetaDataKey()), metaDataType);
      Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
      }.getType();
      Map<String, Map<String, String>> dataResponse = GSON.fromJson(rootObject.get(responseModel.getDataKey()), dataType);

      return Either.left(responseModel.resolve(metaDataResponse, dataResponse));

    } catch (JsonSyntaxException e) {
      return Either.right(e);
    }
  }
}
