package co.alphavantage;

import co.alphavantage.input.Function;
import co.alphavantage.input.Symbol;
import co.alphavantage.input.technicalindicators.*;
import co.alphavantage.output.technicalindicators.*;

import javax.annotation.Nullable;

// TODO add optional in docs to optional values

/**
 * Technical indicator values are updated realtime: the latest data point is derived from the current trading day of a given equity.
 */
public class TechnicalIndicators {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Technical Indicator Data api endpoint with the help of an {@link ApiConnector}.
   *
   * @param apiConnector the connection to the api.
   */
  public TechnicalIndicators(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * Returns the double exponential moving average (DEMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link DEMA} indicator data.
   */
  public DEMA dema(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DEMA, timePeriod, interval, seriesType);
    return DEMA.from(json);
  }

  /**
   * Returns the exponential moving average (EMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link EMA} indicator data.
   */
  public EMA ema(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.EMA, timePeriod, interval, seriesType);
    return EMA.from(json);
  }

  /**
   * Returns the Kaufman adaptive moving average (KAMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link KAMA} indicator data.
   */
  public KAMA kama(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.KAMA, timePeriod, interval, seriesType);
    return KAMA.from(json);
  }

  /**
   * Returns the moving average convergence / divergence (MACD) values.
   *
   * @param symbol       the stock symbol to lookup.
   * @param interval     the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod   Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param fastPeriod   Positive integers are accepted, by default 12 {@link FastPeriod}
   * @param slowPeriod   Positive integers are accepted, by default 26 {@link SlowPeriod}
   * @param signalPeriod Positive integers are accepted, by default 9 {@link SignalPeriod}
   * @return {@link MACD} indicator data.
   */
  public MACD macd(String symbol,
                   Interval interval,
                   TimePeriod timePeriod,
                   @Nullable FastPeriod fastPeriod,
                   @Nullable SlowPeriod slowPeriod,
                   @Nullable SignalPeriod signalPeriod) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.MACD,
            timePeriod,
            interval,
            fastPeriod,
            slowPeriod,
            signalPeriod);
    return MACD.from(json);
  }

  /**
   * Returns the moving average convergence / divergence values with controllable moving average type values.
   *
   * @param symbol       the stock symbol to lookup.
   * @param interval     the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod   Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param fastPeriod   Positive integers are accepted, by default 12 {@link FastPeriod}
   * @param slowPeriod   Positive integers are accepted, by default 26 {@link SlowPeriod}
   * @param signalPeriod Positive integers are accepted, by default 9 {@link SignalPeriod}
   * @param fastMaType   Moving average type for the faster moving average, by default SMA {@link FastMaType}
   * @param slowMaType   Moving average type for the slower moving average. by default SMA {@link SlowMaType}
   * @param signalMaType Moving average type for the signal moving average. by default SMA {@link SignalMaType}
   * @return {@link MACDEXT} indicator data.
   */
  public MACD macdext(String symbol,
                      Interval interval,
                      TimePeriod timePeriod,
                      @Nullable FastPeriod fastPeriod,
                      @Nullable SlowPeriod slowPeriod,
                      @Nullable SignalPeriod signalPeriod,
                      @Nullable FastMaType fastMaType,
                      @Nullable SlowMaType slowMaType,
                      @Nullable SignalMaType signalMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.MACDEXT,
            timePeriod,
            interval,
            fastPeriod,
            slowPeriod,
            signalPeriod,
            fastMaType,
            slowMaType,
            signalMaType);
    return MACD.from(json);
  }

  /**
   * Returns the MESA adaptive moving average (MAMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @param fastLimit  Positive floats are accepted, by default 0.01 {@link FastLimit}
   * @param slowLimit  Positive floats are accepted, by default 0.01 {@link SlowLimit}
   * @return {@link MAMA} indicator data.
   */
  public MAMA mama(String symbol,
                   Interval interval,
                   TimePeriod timePeriod,
                   SeriesType seriesType,
                   @Nullable FastLimit fastLimit,
                   @Nullable SlowLimit slowLimit) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.MAMA,
            timePeriod,
            interval,
            seriesType,
            fastLimit,
            slowLimit);
    return MAMA.from(json);
  }

  /**
   * Returns the relative strength index (RSI) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link RSI} indicator data.
   */
  public RSI rsi(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.RSI, timePeriod, interval, seriesType);
    return RSI.from(json);
  }

  /**
   * Returns the simple moving average (SMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link SMA} indicator data.
   */
  public SMA sma(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.SMA, timePeriod, interval, seriesType);
    return SMA.from(json);
  }

  /**
   * Returns the stochastic oscillator (STOCH) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod  Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType  The desired price type in the time series {@link SeriesType}.
   * @param fastKPeriod The time period of the fastk moving average {@link FastKPeriod}.
   * @param slowKPeriod The desired price type in the time series {@link SlowKPeriod}.
   * @param slowDPeriod The desired price type in the time series {@link SlowDPeriod}.
   * @param slowKMaType The desired price type in the time series {@link SlowKMaType}.
   * @param slowDMaType The desired price type in the time series {@link SlowDMaType}.
   * @return {@link STOCH} indicator data.
   */
  public STOCH stoch(String symbol,
                     Interval interval,
                     TimePeriod timePeriod,
                     SeriesType seriesType,
                     @Nullable FastKPeriod fastKPeriod,
                     @Nullable SlowKPeriod slowKPeriod,
                     @Nullable SlowDPeriod slowDPeriod,
                     @Nullable SlowKMaType slowKMaType,
                     @Nullable SlowDMaType slowDMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.STOCH,
            timePeriod,
            interval,
            seriesType,
            fastKPeriod,
            slowKPeriod,
            slowDPeriod,
            slowKMaType,
            slowDMaType);
    return STOCH.from(json);
  }

  /**
   * Returns the stochastic fast (STOCHF) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod  Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType  The desired price type in the time series {@link SeriesType}.
   * @param fastKPeriod The time period of the fastk moving average, positive integers are accepted, by default 5 {@link FastKPeriod}.
   * @param fastDPeriod The time period of the fastd moving average, positive integers are accepted, by default 3 {@link FastDPeriod}.
   * @param fastDMaType Moving average type for the fastd moving average, by default SMA {@link FastDMaType}.
   * @return {@link STOCHF} indicator data.
   */
  public STOCHF stochf(String symbol,
                       Interval interval,
                       TimePeriod timePeriod,
                       SeriesType seriesType,
                       @Nullable FastKPeriod fastKPeriod,
                       @Nullable FastDPeriod fastDPeriod,
                       @Nullable FastDMaType fastDMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.STOCHF,
            timePeriod,
            interval,
            seriesType,
            fastKPeriod,
            fastDPeriod,
            fastDMaType);
    return STOCHF.from(json);
  }

  /**
   * Returns the stochastic relative strength index (STOCHRSI) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod  Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType  The desired price type in the time series {@link SeriesType}.
   * @param fastKPeriod The time period of the fastk moving average, positive integers are accepted, by default 5 {@link FastKPeriod}.
   * @param fastDPeriod The time period of the fastd moving average, positive integers are accepted, by default 3 {@link FastDPeriod}.
   * @param fastDMaType Moving average type for the fastd moving average, by default SMA {@link FastDMaType}.
   * @return {@link STOCHRSI} indicator data.
   */
  public STOCHRSI stochrsi(String symbol,
                           Interval interval,
                           TimePeriod timePeriod,
                           SeriesType seriesType,
                           @Nullable FastKPeriod fastKPeriod,
                           @Nullable FastDPeriod fastDPeriod,
                           @Nullable FastDMaType fastDMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.STOCHRSI,
            timePeriod,
            interval,
            seriesType,
            fastKPeriod,
            fastDPeriod,
            fastDMaType);
    return STOCHRSI.from(json);
  }

  /**
   * Returns the triple exponential moving average (T3) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link T3} indicator data.
   */
  public T3 t3(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.T3, timePeriod, interval, seriesType);
    return T3.from(json);
  }

  /**
   * Returns the triple exponential moving average (TEMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link TEMA} indicator data.
   */
  public TEMA tema(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TEMA, timePeriod, interval, seriesType);
    return TEMA.from(json);
  }

  /**
   * Returns the triangular moving average (TRIMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link TRIMA} indicator data.
   */
  public TRIMA trima(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TRIMA, timePeriod, interval, seriesType);
    return TRIMA.from(json);
  }

  /**
   * Returns the Williams' %R (WILLR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link WILLR} indicator data.
   */
  public WILLR willr(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.WILLR, timePeriod, interval, seriesType);
    return WILLR.from(json);
  }

  /**
   * Returns the double exponential moving average (DEMA) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link DEMA} indicator data.
   */
  public WMA wma(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.WMA, timePeriod, interval, seriesType);
    return WMA.from(json);
  }


}
