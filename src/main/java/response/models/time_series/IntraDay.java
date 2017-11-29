package response.models.time_series;

import parameters.time_series.Interval;
import response.data.time_series.MetaData;
import response.data.time_series.ResponseData;
import response.data.time_series.StockData;
import response.models.ResponseModel;
import response.models.time_series.resolvers.MetaDataResolver;
import response.models.time_series.resolvers.StockDataResolver;

import java.util.List;
import java.util.Map;

public class IntraDay implements ResponseModel<ResponseData> {
  private final Interval interval;

  public IntraDay(Interval interval) {
    this.interval = interval;
  }

  @Override
  public String getDataKey() {
    return "Time Series (" + interval.getValue() + ")";
  }

  @Override
  public ResponseData resolve(Map<String, String> metaDataResponse,
                              Map<String, Map<String, String>> stockDataResponse) {
    final MetaData metaData = MetaDataResolver.full(metaDataResponse);
    final List<StockData> stockData = StockDataResolver.sparseWithTime(stockDataResponse);
    return new ResponseData(metaData, stockData);
  }
}
