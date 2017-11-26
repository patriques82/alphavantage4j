/**
 * Technical indicator values are updated realtime: the latest data point is derived from the current trading day of a given equity.
 */
public class StockTechnicalIndicators {
  private final ApiConnector apiConnector;
  /**
   * Constructs a Technical Indicator Data api endpoint with the help of an {@link ApiConnector}
   * @param apiConnector the connection to the api
   */
  public StockTechnicalIndicators(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }
}
