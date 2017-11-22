import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import common.Try;
import response.data.MetaData;
import response.data.ResponseData;
import response.data.StockData;
import response.models.ResponseModel;

public class JsonParser {
  private static final Gson GSON = new Gson();
  private static final com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();;

  public static Try<ResponseData> parseJson(String json, ResponseModel responseModel) {
    JsonElement jsonElement = PARSER.parse(json);
    JsonObject rootObject = jsonElement.getAsJsonObject();

    JsonElement errorMessage = rootObject.get("Error Message");
    if (errorMessage != null) {
      return Try.failure(errorMessage.getAsString());
    }

    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Map<String, String> metaDataResponse =
            GSON.fromJson(rootObject.get(responseModel.getMetaDataKey()), metaDataType);
    MetaData metaData = responseModel.resolveMetaData(metaDataResponse);

    Type stockDataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    Map<String, Map<String, String>> stockDataResponse =
            GSON.fromJson(rootObject.get(responseModel.getStocksKey()), stockDataType);
    List<StockData> stockData = responseModel.resolveStockData(stockDataResponse);

    return Try.success(new ResponseData(metaData, stockData));
  }
}
