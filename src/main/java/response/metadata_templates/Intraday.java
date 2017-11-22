package response.metadata_templates;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import response.MetaData;

import java.util.Map;


public class Intraday implements MetaDataTemplate {

  private static final String DATE_WITH_TIME_PATTERN = "YYYY-MM-DD HH:mm:ss";
  private static final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern(DATE_WITH_TIME_PATTERN);

  @Override
  public MetaData resolve(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            metaDataResponse.get("4. Interval"),
            metaDataResponse.get("5. Output Size"),
            metaDataResponse.get("6. Time Zone")
    );
  }

  private DateTime parseDate(String dateTime, DateTimeFormatter formatter) {
    return formatter.parseDateTime(dateTime);
  }
}
