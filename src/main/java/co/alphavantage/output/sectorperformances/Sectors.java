package co.alphavantage.output.sectorperformances;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.sectorperformances.data.SectorData;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of sectors percentual change over different timeperiods
 */
public class Sectors {
  private final Map<String, String> metadata;
  private final List<SectorData> sectors;

  Sectors(Map<String, String> metadata, List<SectorData> sectors) {
    this.metadata = metadata;
    this.sectors = sectors;
  }

  /**
   * Meta data for sectors data
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metadata;
  }

  /**
   * Percentual changes in different sectors during different timeperiods; from real-time to 10 years.
   * @return list of percentual changes in different sectors.
   */
  public List<SectorData> getSectors() {
    return sectors;
  }

  /**
   * Create Sectors data representation from json object
   * @param json string to parse
   * @return {@code Sectors} data
   * @throws AlphaVantageException
   */
  public static Sectors from(String json) throws AlphaVantageException {
    SectorPerformancesParser parser = new SectorPerformancesParser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Sector}
   * @see JsonParser
   */
  private static class SectorPerformancesParser extends JsonParser<Sectors> {

    @Override
    public Sectors resolve(JsonObject rootObject) {
      Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
      }.getType();
      try {
        Map<String, Map<String, String>> data = GSON.fromJson(rootObject, dataType);
        Map<String, String> metadata = data.remove("Meta Data");
        List<SectorData> sectors = new ArrayList<>();
        data.forEach((key, values) -> {
          try {
            sectors.add(createSectorData(key, values));
          } catch (ParseException e) {
            throw new AlphaVantageException("technical indicators api change", e);
          }
        });
        return new Sectors(metadata, sectors);
      } catch (JsonSyntaxException e) {
        throw new AlphaVantageException("technical indicators api change", e);
      }
    }

    private SectorData createSectorData(String key, Map<String, String> values) throws ParseException {
      return new SectorData(
              key,
              Double.parseDouble(values.getOrDefault("Information Technology", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Health Care", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Consumer Staples", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Real Estate", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Materials", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Consumer Discretionary", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Energy", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Financials", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Industrials", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Utilities", "0").trim().replace("%", "")),
              Double.parseDouble(values.getOrDefault("Telecommunication Services", "0").trim().replace("%", ""))
      );
    }
  }
}
