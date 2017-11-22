import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.msiops.ground.either.Either;
import response.data.MetaData;
import response.data.ResponseData;
import response.data.StockData;
import response.models.ResponseModel;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

class JsonParser {
  private static final Gson GSON = new Gson();
  private static final com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();

  static Either<ResponseData, Exception> parseJson(String json, ResponseModel responseModel) {
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
      Type stockDataType = new TypeToken<Map<String, Map<String, String>>>() {
      }.getType();
      Map<String, Map<String, String>> stockDataResponse = GSON.fromJson(rootObject.get(responseModel.getStocksKey()), stockDataType);

      MetaData metaData = responseModel.resolveMetaData(metaDataResponse);
      List<StockData> stockData = responseModel.resolveStockData(stockDataResponse);
      return Either.left(new ResponseData(metaData, stockData));

    } catch (JsonSyntaxException e) {
      return Either.right(e);
    }
  }
}
