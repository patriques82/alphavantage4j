package response.models.time_series;

import response.data.time_series.ResponseData;
import response.models.ResponseModel;

import java.util.Map;

public class Weekly implements ResponseModel<ResponseData> {

  @Override
  public String getDataKey() {
    return "Weekly Time Series";
  }

  @Override
  public ResponseData resolve(Map<String, String> metaData,
                              Map<String, Map<String, String>> stockDataResponse) {
    return new ResponseData(metaData, StockDataResolver.sparse(stockDataResponse));
  }
}
