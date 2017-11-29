package response.models.time_series.resolvers;

import response.data.time_series.MetaData;

import java.util.Map;

public class MetaDataResolver {
  public static MetaData sparse(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            null,
            null,
            metaDataResponse.get("4. Time Zone")
    );
  }

  public static MetaData withOutputSize(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            null,
            metaDataResponse.get("4. Output Size"),
            metaDataResponse.get("5. Time Zone")
    );
  }

  public static MetaData full(Map<String, String> metaDataResponse) {
    return new MetaData(
            metaDataResponse.get("1. Information"),
            metaDataResponse.get("2. Symbol"),
            metaDataResponse.get("3. Last Refreshed"),
            metaDataResponse.get("4. Interval"),
            metaDataResponse.get("5. Output Size"),
            metaDataResponse.get("6. Time Zone")
    );
  }
}
