package output.models.time_series;

import input.time_series.Interval;
import output.data.ResponseData;
import output.data.time_series.StockData;
import output.models.ResponseModel;

import java.util.Map;

public class IntraDay implements ResponseModel<ResponseData<StockData>> {
  private final Interval interval;

  public IntraDay(Interval interval) {
    this.interval = interval;
  }

  @Override
  public String getDataKey() {
    return "Time Series (" + interval.getValue() + ")";
  }

  @Override
  public ResponseData<StockData> resolve(Map<String, String> metaData,
                                         Map<String, Map<String, String>> dataResponse) {
    return new ResponseData<>(metaData, StockDataResolver.sparseWithTime(dataResponse));
  }
}
