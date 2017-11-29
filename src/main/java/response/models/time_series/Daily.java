package response.models.time_series;

import response.data.time_series.MetaData;
import response.data.time_series.ResponseData;
import response.data.time_series.StockData;
import response.models.ResponseModel;
import response.models.time_series.resolvers.MetaDataResolver;
import response.models.time_series.resolvers.StockDataResolver;

import java.util.List;
import java.util.Map;

public class Daily implements ResponseModel<ResponseData> {

  @Override
  public String getDataKey() {
    return "Time Series (Daily)";
  }

  @Override
  public ResponseData resolve(Map<String, String> metaDataResponse,
                              Map<String, Map<String, String>> stockDataResponse) {
    final MetaData metaData = MetaDataResolver.withOutputSize(metaDataResponse);
    final List<StockData> stockData = StockDataResolver.sparse(stockDataResponse);
    return new ResponseData(metaData, stockData);
  }

}
