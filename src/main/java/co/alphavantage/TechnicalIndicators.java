package co.alphavantage;

import co.alphavantage.input.Symbol;
import co.alphavantage.input.technicalindicator.Function;
import co.alphavantage.input.technicalindicator.Interval;
import co.alphavantage.input.technicalindicator.SeriesType;
import co.alphavantage.input.technicalindicator.TimePeriod;
import co.alphavantage.output.technicalindicators.SMA;

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

  public SMA sma(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.SMA, timePeriod, interval, seriesType);
    return SMA.from(json);
  }
}
