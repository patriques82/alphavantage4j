package co.alphavantage.output.sectorperformances;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.sectorperformances.data.SectorData;

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
   * Percentual changes in different sectors over different timeperiods; from real-time to 10 years.
   * @return list of percentual changes in different sectors.
   */
  public List<SectorData> getSectors() {
    return sectors;
  }

  /**
   * Create Sectors data representation from json object
   * @param json string to parse
   * @return Sectors data
   * @throws AlphaVantageException
   */
  public static Sectors from(String json) throws AlphaVantageException {
    SectorPerformancesParser parser = new SectorPerformancesParser();
    return parser.parseJson(json);
  }
}
