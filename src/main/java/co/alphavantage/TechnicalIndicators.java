package co.alphavantage;

import co.alphavantage.input.Symbol;
import co.alphavantage.input.technicalindicator.Function;
import co.alphavantage.input.technicalindicator.Interval;
import co.alphavantage.input.technicalindicator.SeriesType;
import co.alphavantage.input.technicalindicator.TimePeriod;
import co.alphavantage.output.technicalindicators.EMA;
import co.alphavantage.output.technicalindicators.MACD;
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

  /**
   * Returns the simple moving average (SMA) values
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link co.alphavantage.input.technicalindicator.Interval}
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link co.alphavantage.input.technicalindicator.TimePeriod}
   * @param seriesType The desired price type in the time series {@link co.alphavantage.input.technicalindicator.SeriesType}
   * @return SMA indicator data
   */
  public SMA sma(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.SMA, timePeriod, interval, seriesType);
    return SMA.from(json);
  }
  /**
   * Returns the exponential moving average (EMA) values
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link co.alphavantage.input.technicalindicator.Interval}
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link co.alphavantage.input.technicalindicator.TimePeriod}
   * @param seriesType The desired price type in the time series {@link co.alphavantage.input.technicalindicator.SeriesType}
   * @return EMA indicator data
   */
  public EMA ema(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.EMA, timePeriod, interval, seriesType);
    return EMA.from(json);
  }
  
  /**
   * Returns the moving average converge/divergence (MACD) values
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link co.alphavantage.input.technicalindicator.Interval}
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link co.alphavantage.input.technicalindicator.TimePeriod}
   * @param seriesType The desired price type in the time series {@link co.alphavantage.input.technicalindicator.SeriesType}
   * @return MACD indicator data
   */
  public MACD macd(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
	  String json = apiConnector.getRequest(new Symbol(symbol), Function.MACD, timePeriod, interval, seriesType);
	  return MACD.from(json);
  }
}
