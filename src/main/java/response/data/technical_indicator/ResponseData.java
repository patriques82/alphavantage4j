package response.data.technical_indicator;

import java.util.List;
import java.util.Map;

public class ResponseData<Data> {
  private final Map<String, String> metaData;
  private final List<Data> indicatorData;

  public ResponseData(Map<String, String> metaData, List<Data> indicatorData) {
    this.metaData = metaData;
    this.indicatorData = indicatorData;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<Data> getIndicatorData() {
    return indicatorData;
  }
}
