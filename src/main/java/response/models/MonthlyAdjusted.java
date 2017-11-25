package response.models;

import response.data.MetaData;
import response.data.StockData;
import response.models.resolvers.MetaDataResolver;
import response.models.resolvers.StockDataResolver;

import java.util.List;
import java.util.Map;

public class MonthlyAdjusted implements ResponseModel {

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return MetaDataResolver.sparse(metaDataResponse);
  }

  @Override
  public String getStocksKey() {
    return "Monthly Adjusted Time Series";
  }

  @Override
  public List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse) {
    return StockDataResolver.fullWithoutSplitCoefficient(stockDataResponse);
  }

}
