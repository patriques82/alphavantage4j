import input.Symbol;
import input.technical_indicator.Function;
import input.technical_indicator.Interval;
import input.technical_indicator.SeriesType;
import input.technical_indicator.TimePeriod;
import output.AlphaVantageException;
import output.technical_indicators.SMA;

/**
 * Technical indicator values are updated realtime: the latest data point is derived from the current trading day of a given equity.
 */
public class TechnicalIndicators {
  private final ApiConnector apiConnector;
  /**
   * Constructs a Technical Indicator Data api endpoint with the help of an {@link ApiConnector}
   * @param apiConnector the connection to the api
   */
  public TechnicalIndicators(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  public SMA sma(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) throws AlphaVantageException{
    String json = apiConnector.getRequest(new Symbol(symbol), Function.SMA, timePeriod, interval, seriesType);
    return SMA.from(json);
  }
}
