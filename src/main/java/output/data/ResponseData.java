package output.data;

import java.util.List;
import java.util.Map;

public class ResponseData<Data> {
  private final Map<String, String> metaData;
  private final List<Data> data;

  public ResponseData(Map<String, String> metaData, List<Data> data) {
    this.metaData = metaData;
    this.data = data;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<Data> getData() {
    return data;
  }
}
