package output.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.msiops.ground.either.Either;

import java.lang.reflect.Type;
import java.util.Map;

class MetaAndListParser<Data> implements JsonParser<Data> {
  private static Gson GSON = new Gson();
  private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();

  @Override
  public Either<Data, Exception> parseJson(String json, ResponseModel<Data> responseModel) {
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
