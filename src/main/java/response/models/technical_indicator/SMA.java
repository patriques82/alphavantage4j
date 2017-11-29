package response.models.technical_indicator;

import org.joda.time.DateTime;
import response.data.ResponseData;
import response.data.technical_indicator.SMAData;
import response.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SMA implements ResponseModel<ResponseData<SMAData>> {

  @Override
  public String getDataKey() {
    return "Technical Analysis: SMA";
  }

  @Override
  public ResponseData<SMAData> resolve(Map<String, String> metaData,
                                       Map<String, Map<String, String>> indicatorDataResponse) {
    List<SMAData> indicatorData = new ArrayList<>();
    indicatorDataResponse.forEach((k, v) -> {
      indicatorData.add(new SMAData(
              DateTime.parse(k, DATE_WITH_TIME_FORMAT),
              Double.parseDouble(v.get("SMA"))
      ));
    });
    return new ResponseData<>(metaData, indicatorData);
  }
}
