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
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            null,
            metaDataResponse.get("4. Output Size"),
            metaDataResponse.get("5. Time Zone")
    );
  }

  @Override
  public String getStocksKey() {
    return "Time Series (Daily)";
  }
}
