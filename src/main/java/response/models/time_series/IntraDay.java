package response.models.time_series;

import parameters.time_series.Interval;
import response.data.time_series.ResponseData;
import response.models.ResponseModel;

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
  public ResponseData resolve(Map<String, String> metaData,
                              Map<String, Map<String, String>> stockDataResponse) {
    return new ResponseData(metaData, StockDataResolver.sparseWithTime(stockDataResponse));
  }
}
