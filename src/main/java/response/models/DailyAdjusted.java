package response.models;

import response.data.MetaData;
import response.data.StockData;
import response.models.resolvers.MetaDataResolver;
import response.models.resolvers.StockDataResolver;

import java.util.List;
import java.util.Map;

public class DailyAdjusted implements ResponseModel {

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return MetaDataResolver.withOutputSize(metaDataResponse);
  }

  @Override
  public String getStocksKey() {
    return "Time Series (Daily)";
  }

  @Override
  public List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse) {
    return StockDataResolver.full(stockDataResponse);
  }

}
