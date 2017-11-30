package output.models.technical_indicator;

import org.joda.time.DateTime;
import output.data.ResponseData;
import output.data.technical_indicator.SMAData;
import output.models.ResponseModel;

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
                                       Map<String, Map<String, String>> dataResponse) {
    List<SMAData> indicatorData = new ArrayList<>();
    dataResponse.forEach((k, v) -> {
      indicatorData.add(new SMAData(
              DateTime.parse(k, DATE_WITH_TIME_FORMAT),
              Double.parseDouble(v.get("SMA"))
      ));
    });
    return new ResponseData<>(metaData, indicatorData);
  }
}
