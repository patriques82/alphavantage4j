package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.Symbol;
import org.patriques.input.technicalindicators.*;
import org.patriques.output.technicalindicators.*;

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
   * Returns the Chaikin A/D line (AD) values.
   *
   * @param symbol   the stock symbol to lookup.
   * @param interval the interval between two consecutive data points in the time series {@link Interval}.
   * @return {@link AD} indicator data.
   */
  public AD ad(String symbol, Interval interval) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.AD, interval);
    return AD.from(interval, json);
  }

  /**
   * Returns the Chaikin A/D oscillator (ADOSC) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param fastPeriod the time period of the fast EMA, default 3 {@link FastPeriod}.
   * @param slowPeriod the time period of the fast EMA, default 10 {@link SlowPeriod}.
   * @return {@link ADOSC} indicator data.
   */
  public ADOSC adosc(String symbol,
                     Interval interval,
                     @Nullable FastPeriod fastPeriod,
                     @Nullable SlowPeriod slowPeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ADOSC, interval, fastPeriod);
    return ADOSC.from(interval, json);
  }

  /**
   * Returns the average directional movement index (ADX) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link ADX} indicator data.
   */
  public ADX adx(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ADX, timePeriod, interval);
    return ADX.from(interval, json);
  }

  /**
   * Returns the average directional movement index rating (ADXR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link ADX} indicator data.
   */
  public ADXR adxr(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ADXR, timePeriod, interval);
    return ADXR.from(interval, json);
  }

  /**
   * Returns the absolute price oscillator (APO) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @param fastPeriod default 12 {@link FastPeriod}.
   * @param slowPeriod default 26 {@link SlowPeriod}.
   * @param maType     Moving average type, default SMA {@link MaType}.
   * @return {@link APO} indicator data.
   */
  public APO apo(String symbol,
                 Interval interval,
                 SeriesType seriesType,
                 @Nullable FastPeriod fastPeriod,
                 @Nullable SlowPeriod slowPeriod,
                 @Nullable MaType maType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.APO, interval, seriesType, fastPeriod, slowPeriod, maType);
    return APO.from(interval, json);
  }

  /**
   * Returns the Aroon (AROON) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link AROON} indicator data.
   */
  public AROON aroon(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.AROON, interval, timePeriod);
    return AROON.from(interval, json);
  }

  /**
   * Returns the Aroon oscillator (AROONOSC) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link AROONOSC} indicator data.
   */
  public AROONOSC aroonosc(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.AROONOSC, interval, timePeriod);
    return AROONOSC.from(interval, json);
  }

  /**
   * Returns the average true range (ATR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link ATR} indicator data.
   */
  public ATR atr(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ATR, interval, timePeriod);
    return ATR.from(interval, json);
  }

  /**
   * Returns the Bollinger bands (BBANDS) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType the desired price type in the time series {@link SeriesType}.
   * @param nbDevUp    The standard deviation multiplier of the upper band. Positive integers are accepted. By default 2 {@link NBDevUp}.
   * @param nbDevDn    The standard deviation multiplier of the lower band. Positive integers are accepted. By default 2 {@link NBDevDn}.
   * @return {@link BBANDS} indicator data.
   */
  public BBANDS bbands(String symbol,
                       Interval interval,
                       TimePeriod timePeriod,
                       SeriesType seriesType,
                       @Nullable NBDevUp nbDevUp,
                       @Nullable NBDevDn nbDevDn,
                       @Nullable MaType maType) {
    String json = apiConnector.getRequest(new Symbol(symbol),
            Function.BBANDS,
            interval,
            timePeriod,
            seriesType,
            nbDevUp,
            nbDevDn,
            maType);
    return BBANDS.from(interval, json);
  }

  /**
   * Returns the balance of power (BOP) values.
   *
   * @param symbol   the stock symbol to lookup.
   * @param interval the interval between two consecutive data points in the time series {@link Interval}.
   * @return {@link BOP} indicator data.
   */
  public BOP bop(String symbol, Interval interval) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.BOP, interval);
    return BOP.from(interval, json);
  }

  /**
   * Returns the commodity channel index (CCI) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link CCI} indicator data.
   */
  public CCI cci(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.CCI, interval, timePeriod);
    return CCI.from(interval, json);
  }

  /**
   * Returns the Chande momentum oscillator (CMO) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link CMO} indicator data.
   */
  public CMO cmo(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.CMO, timePeriod, interval, seriesType);
    return CMO.from(interval, json);
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
    return DEMA.from(interval, json);
  }

  /**
   * Returns the directional movement index (DX) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link DX} indicator data.
   */
  public DX dx(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DX, interval, timePeriod);
    return DX.from(interval, json);
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
    return EMA.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, dominant cycle period (HT_DCPERIOD) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_DCPERIOD} indicator data.
   */
  public HT_DCPERIOD ht_dcperiod(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_DCPERIOD, interval, seriesType);
    return HT_DCPERIOD.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, dominant cycle phase (HT_DCPHASE) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_DCPERIOD} indicator data.
   */
  public HT_DCPHASE ht_dcphase(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_DCPHASE, interval, seriesType);
    return HT_DCPHASE.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, phasor components (HT_PHASOR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_PHASOR} indicator data.
   */
  public HT_PHASOR ht_phasor(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_PHASOR, interval, seriesType);
    return HT_PHASOR.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, sine wave (HT_SINE) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_SINE} indicator data.
   */
  public HT_SINE ht_sine(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_SINE, interval, seriesType);
    return HT_SINE.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, instantaneous trendline (HT_TRENDLINE) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_TRENDLINE} indicator data.
   */
  public HT_TRENDLINE ht_trendline(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_TRENDLINE, interval, seriesType);
    return HT_TRENDLINE.from(interval, json);
  }

  /**
   * Returns the Hilbert transform, trend vs cycle mode (HT_TRENDMODE) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link HT_TRENDMODE} indicator data.
   */
  public HT_TRENDMODE ht_trendmode(String symbol, Interval interval, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.HT_TRENDMODE, interval, seriesType);
    return HT_TRENDMODE.from(interval, json);
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
    return KAMA.from(interval, json);
  }

  /**
   * Returns the moving average convergence / divergence (MACD) values.
   *
   * @param symbol       the stock symbol to lookup.
   * @param interval     the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType   The desired price type in the time series {@link SeriesType}.
   * @param timePeriod   Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param fastPeriod   Positive integers are accepted, by default 12 {@link FastPeriod}
   * @param slowPeriod   Positive integers are accepted, by default 26 {@link SlowPeriod}
   * @param signalPeriod Positive integers are accepted, by default 9 {@link SignalPeriod}
   * @return {@link MACD} indicator data.
   */
  public MACD macd(String symbol,
                   Interval interval,
                   TimePeriod timePeriod,
                   SeriesType seriesType,
                   @Nullable FastPeriod fastPeriod,
                   @Nullable SlowPeriod slowPeriod,
                   @Nullable SignalPeriod signalPeriod) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.MACD,
            timePeriod,
            seriesType,
            interval,
            fastPeriod,
            slowPeriod,
            signalPeriod);
    return MACD.from(interval, json);
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
  public MACDEXT macdext(String symbol,
                         Interval interval,
                         TimePeriod timePeriod,
                         SeriesType seriesType,
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
            seriesType,
            interval,
            fastPeriod,
            slowPeriod,
            signalPeriod,
            fastMaType,
            slowMaType,
            signalMaType);
    return MACDEXT.from(interval, json);
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
    return MAMA.from(interval, json);
  }

  /**
   * Returns the money flow index (MFI) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link MFI} indicator data.
   */
  public MFI mfi(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MFI, interval, timePeriod);
    return MFI.from(interval, json);
  }

  /**
   * Returns the midpoint (MIDPOINT) values. MIDPOINT = (highest value + lowest value)/2.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link MIDPOINT} indicator data.
   */
  public MIDPOINT midpoint(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MIDPOINT, timePeriod, interval, seriesType);
    return MIDPOINT.from(interval, json);
  }

  /**
   * Returns the midpoint price (MIDPRICE) values. MIDPRICE = (highest high + lowest low)/2.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link MIDPRICE} indicator data.
   */
  public MIDPRICE midprice(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MIDPRICE, interval, timePeriod);
    return MIDPRICE.from(interval, json);
  }

  /**
   * Returns the minus directional indicator (MINUS_DI) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link MINUS_DI} indicator data.
   */
  public MINUS_DI minus_di(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MINUS_DI, interval, timePeriod);
    return MINUS_DI.from(interval, json);
  }

  /**
   * Returns the minus directional movement (MINUS_DM) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link MINUS_DM} indicator data.
   */
  public MINUS_DM minus_dm(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MINUS_DM, interval, timePeriod);
    return MINUS_DM.from(interval, json);
  }

  /**
   * Returns the momentum (MOM) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link MOM} indicator data.
   */
  public MOM mom(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MOM, timePeriod, interval, seriesType);
    return MOM.from(interval, json);
  }

  /**
   * Returns the normalized average true range (NATR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link NATR} indicator data.
   */
  public NATR natr(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.NATR, interval, timePeriod);
    return NATR.from(interval, json);
  }

  /**
   * Returns the on balance volume (OBV) values.
   *
   * @param symbol   the stock symbol to lookup.
   * @param interval the interval between two consecutive data points in the time series {@link Interval}.
   * @return {@link OBV} indicator data.
   */
  public OBV obv(String symbol, Interval interval) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.OBV, interval);
    return OBV.from(interval, json);
  }

  /**
   * Returns the plus directional indicator (PLUS_DI) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link PLUS_DI} indicator data.
   */
  public PLUS_DI plus_di(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.PLUS_DI, interval, timePeriod);
    return PLUS_DI.from(interval, json);
  }

  /**
   * Returns the plus directional movement (PLUS_DM) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link PLUS_DM} indicator data.
   */
  public PLUS_DM plus_dm(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.PLUS_DM, interval, timePeriod);
    return PLUS_DM.from(interval, json);
  }

  /**
   * Returns the percentage price oscillator (PPO) values values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @param fastPeriod default 12 {@link FastPeriod}.
   * @param slowPeriod default 26 {@link SlowPeriod}.
   * @param maType     Moving average type, default SMA {@link MaType}.
   * @return {@link PPO} indicator data.
   */
  public PPO ppo(String symbol,
                 Interval interval,
                 SeriesType seriesType,
                 @Nullable FastPeriod fastPeriod,
                 @Nullable SlowPeriod slowPeriod,
                 @Nullable MaType maType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.PPO, interval, seriesType, fastPeriod, slowPeriod, maType);
    return PPO.from(interval, json);
  }

  /**
   * Returns the rate of change (ROC) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link ROC} indicator data.
   */
  public ROC roc(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ROC, timePeriod, interval, seriesType);
    return ROC.from(interval, json);
  }

  /**
   * Returns the rate of change ratio (ROCR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link ROCR} indicator data.
   */
  public ROCR rocr(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ROCR, timePeriod, interval, seriesType);
    return ROCR.from(interval, json);
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
    return RSI.from(interval, json);
  }

  /**
   * Returns the parabolic SAR (SAR) values.
   *
   * @param symbol       the stock symbol to lookup.
   * @param interval     the interval between two consecutive data points in the time series {@link Interval}.
   * @param acceleration the acceleration factor. Positive floats are accepted, by default 0.01 {@link Acceleration}.
   * @param maximum      the acceleration factor maximum value. Positive floats are accepted, by default 0.20 {@link Maximum}.
   * @return {@link SAR} indicator data.
   */
  public SAR sar(String symbol,
                 Interval interval,
                 @Nullable Acceleration acceleration,
                 @Nullable Maximum maximum) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.SAR, interval, acceleration, maximum);
    return SAR.from(interval, json);
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
    return SMA.from(interval, json);
  }

  /**
   * Returns the stochastic oscillator (STOCH) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param fastKPeriod The time period of the fastk moving average {@link FastKPeriod}.
   * @param slowKPeriod The desired price type in the time series {@link SlowKPeriod}.
   * @param slowDPeriod The desired price type in the time series {@link SlowDPeriod}.
   * @param slowKMaType The desired price type in the time series {@link SlowKMaType}.
   * @param slowDMaType The desired price type in the time series {@link SlowDMaType}.
   * @return {@link STOCH} indicator data.
   */
  public STOCH stoch(String symbol,
                     Interval interval,
                     @Nullable FastKPeriod fastKPeriod,
                     @Nullable SlowKPeriod slowKPeriod,
                     @Nullable SlowDPeriod slowDPeriod,
                     @Nullable SlowKMaType slowKMaType,
                     @Nullable SlowDMaType slowDMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.STOCH,
            interval,
            fastKPeriod,
            slowKPeriod,
            slowDPeriod,
            slowKMaType,
            slowDMaType);
    return STOCH.from(interval, json);
  }


  /**
   * Returns the stochastic fast (STOCHF) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param fastKPeriod The time period of the fastk moving average, positive integers are accepted, by default 5 {@link FastKPeriod}.
   * @param fastDPeriod The time period of the fastd moving average, positive integers are accepted, by default 3 {@link FastDPeriod}.
   * @param fastDMaType Moving average type for the fastd moving average, by default SMA {@link FastDMaType}.
   * @return {@link STOCHF} indicator data.
   */
  public STOCHF stochf(String symbol,
                       Interval interval,
                       @Nullable FastKPeriod fastKPeriod,
                       @Nullable FastDPeriod fastDPeriod,
                       @Nullable FastDMaType fastDMaType) {
    String json = apiConnector.getRequest(
            new Symbol(symbol),
            Function.STOCHF,
            interval,
            fastKPeriod,
            fastDPeriod,
            fastDMaType);
    return STOCHF.from(interval, json);
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
    return STOCHRSI.from(interval, json);
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
    return T3.from(interval, json);
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
    return TEMA.from(interval, json);
  }

  /**
   * Returns the true range (TRANGE) values.
   *
   * @param symbol   the stock symbol to lookup.
   * @param interval the interval between two consecutive data points in the time series {@link Interval}.
   * @return {@link TRANGE} indicator data.
   */
  public TRANGE trange(String symbol, Interval interval) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TRANGE, interval);
    return TRANGE.from(interval, json);
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
    return TRIMA.from(interval, json);
  }

  /**
   * Returns the 1-day rate of change of a triple smooth exponential moving average (TRIX) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @param seriesType The desired price type in the time series {@link SeriesType}.
   * @return {@link TRIX} indicator data.
   */
  public TRIX trix(String symbol, Interval interval, TimePeriod timePeriod, SeriesType seriesType) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TRIX, timePeriod, interval, seriesType);
    return TRIX.from(interval, json);
  }

  /**
   * Returns the 1-day rate of change of a triple smooth exponential moving average (TRIX) values.
   *
   * @param symbol      the stock symbol to lookup.
   * @param interval    the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod1 the first time period for the indicator. Positive integers are accepted, by default 7 {@link TimePeriod1}.
   * @param timePeriod2 the second time period for the indicator. Positive integers are accepted, by default 14 {@link TimePeriod2}.
   * @param timePeriod3 the third time period for the indicator. Positive integers are accepted, by default 28 {@link TimePeriod3}.
   * @return {@link ULTOSC} indicator data.
   */
  public ULTOSC ultosc(String symbol,
                       Interval interval,
                       @Nullable TimePeriod1 timePeriod1,
                       @Nullable TimePeriod2 timePeriod2,
                       @Nullable TimePeriod3 timePeriod3) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.ULTOSC, timePeriod1, timePeriod2, timePeriod3, interval);
    return ULTOSC.from(interval, json);
  }

  /**
   * Returns the Williams' %R (WILLR) values.
   *
   * @param symbol     the stock symbol to lookup.
   * @param interval   the interval between two consecutive data points in the time series {@link Interval}.
   * @param timePeriod Number of data points used to calculate each moving average value. Positive integers are accepted {@link TimePeriod}.
   * @return {@link WILLR} indicator data.
   */
  public WILLR willr(String symbol, Interval interval, TimePeriod timePeriod) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.WILLR, timePeriod, interval);
    return WILLR.from(interval, json);
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
    return WMA.from(interval, json);
  }

}
