package response.models.time_series;

import response.data.time_series.ResponseData;
import response.models.ResponseModel;

import java.util.Map;

public class MonthlyAdjusted implements ResponseModel<ResponseData> {

  @Override
  public String getDataKey() {
    return "Monthly Adjusted Time Series";
  }

  @Override
  public ResponseData resolve(Map<String, String> metaData,
                              Map<String, Map<String, String>> stockDataResponse) {
    return new ResponseData(metaData, StockDataResolver.fullWithoutSplitCoefficient(stockDataResponse));
  }
}
