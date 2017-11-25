package response.models;

import parameters.Interval;
import response.data.MetaData;
import response.data.StockData;
import response.models.resolvers.MetaDataResolver;
import response.models.resolvers.StockDataResolver;

import java.util.List;
import java.util.Map;

public class IntraDay implements ResponseModel {
  private final Interval interval;

  public IntraDay(Interval interval) {
    this.interval = interval;
  }

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return MetaDataResolver.full(metaDataResponse);
  }

  @Override
  public String getStocksKey() {
    return "Time Series (" + interval.getApiParameterValue() + ")";
  }

  @Override
  public List<StockData> resolveStockData(Map<String, Map<String, String>> stockDataResponse) {
    return StockDataResolver.sparseWithTime(stockDataResponse);
  }

}
