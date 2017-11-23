package response.models;

import org.joda.time.format.DateTimeFormatter;
import response.data.MetaData;

import java.util.Map;

public class DailyModel extends ResponseModel {
  public DailyModel(DateTimeFormatter dateFormat) {
    super(dateFormat);
  }

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return null;
  }

  @Override
  public String getStocksKey() {
    return "Time Series (Daily)";
  }
}
