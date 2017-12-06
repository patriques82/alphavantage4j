package co.alphavantage;

import co.alphavantage.input.sectorperformances.Function;
import co.alphavantage.output.sectorperformances.Sectors;

public class SectorPerformances {
  private final ApiConnector apiConnector;
  /**
   * Constructs a Sector Performances Data api endpoint with the help of an {@link ApiConnector}
   * @param apiConnector the connection to the api
   */
  public SectorPerformances(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  public Sectors sector() {
    String json = apiConnector.getRequest(Function.SECTOR);
    return Sectors.from(json);
  }
}
