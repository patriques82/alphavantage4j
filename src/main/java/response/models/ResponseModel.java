package response.models;

import response.data.MetaData;
import response.data.StockData;

import java.util.List;
import java.util.Map;

public interface ResponseModel {

  default String getMetaDataKey() {
    return "Meta Data";
  }

  MetaData resolveMetaData(Map<String, String> metaDataResponse);

  String getStocksKey();

  List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse);
}
