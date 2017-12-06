package co.alphavantage.output.sectorperformances;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.sectorperformances.data.SectorData;

import java.util.List;
import java.util.Map;

public class Sectors {
  private final Map<String, String> metadata;
  private final List<SectorData> sectors;

  Sectors(Map<String, String> metadata, List<SectorData> sectors) {
    this.metadata = metadata;
    this.sectors = sectors;
  }

  public Map<String, String> getMetaData() {
    return metadata;
  }

  public List<SectorData> getSectors() {
    return sectors;
  }

  public static Sectors from(String json) throws AlphaVantageException {
    SectorPerformancesParser parser = new SectorPerformancesParser();
    return parser.parseJson(json);
  }
}
