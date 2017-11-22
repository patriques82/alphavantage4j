package response.models;

import org.joda.time.format.DateTimeFormatter;
import parameters.Interval;
import response.data.MetaData;
import java.util.Map;

public class IntraDayModel extends ResponseModel {
  private final Interval interval;

  public IntraDayModel(DateTimeFormatter dateWithTimeFormat, Interval interval) {
    super(dateWithTimeFormat);
    this.interval = interval;
  }

  @Override
  public MetaData resolveMetaData(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            metaDataResponse.get("4. Interval"),
            metaDataResponse.get("5. Output Size"),
            metaDataResponse.get("6. Time Zone")
    );
  }

  @Override
  public String getStocksKey() {
    return "Time Series (" + interval.getUrlParameterValue() + ")";
  }
}
