package org.patriques;

import org.patriques.input.Function;
import org.patriques.output.sectorperformances.Sectors;

/**
 * Covers the US sectors (IT, Utilities, Energy, etc...)
 */
public class SectorPerformances {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Sector Performances Data api endpoint with the help of an {@link ApiConnector}
   * @param apiConnector the connection to the api
   */
  public SectorPerformances(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * Yields the percentage of change during different timeperiods, from real-time to 10 years.
   * @return {@link Sectors} data
   */
  public Sectors sector() {
    String json = apiConnector.getRequest(Function.SECTOR);
    return Sectors.from(json);
  }
}
