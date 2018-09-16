package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.Symbol;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.JsonParser;
import org.patriques.output.timeseries.*;

/**
 * The Stock Time Series Data provides realtime and historical equity data in 4 different temporal resolutions:
 * (1) intraday, (2) daily, (3) weekly, and (4) monthly.
 */
public class TimeSeries {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Time Series Data api endpoint with the help of an {@link ApiConnector} and a {@link JsonParser}
   *
   * @param apiConnector the connection to the api
   */
  public TimeSeries(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   *
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link IntraDay} time series data
   */
  public IntraDay intraDay(String symbol, Interval interval, OutputSize outputSize)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_INTRADAY, interval, outputSize);
    return IntraDay.from(interval, json);
  }

  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   *
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @return {@link IntraDay} time series data
   */
  public IntraDay intraDay(String symbol, Interval interval)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_INTRADAY, interval);
    return IntraDay.from(interval, json);
  }

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   *
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link Daily} time series data
   */
  public Daily daily(String symbol, OutputSize outputSize)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_DAILY, outputSize);
    return Daily.from(json);
  }

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   *
   * @param symbol the stock symbol to lookup
   * @return {@link Daily} time series data
   */
  public Daily daily(String symbol) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_DAILY);
    return Daily.from(json);
  }

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events).
   *
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link DailyAdjusted} time series data
   */
  public DailyAdjusted dailyAdjusted(String symbol, OutputSize outputSize) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_DAILY_ADJUSTED, outputSize);
    return DailyAdjusted.from(json);
  }

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events).
   *
   * @param symbol the stock symbol to lookup
   * @return {@link DailyAdjusted} time series data
   */
  public DailyAdjusted dailyAdjusted(String symbol)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_DAILY_ADJUSTED);
    return DailyAdjusted.from(json);
  }

  /**
   * This API returns weekly time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly volume).
   *
   * @param symbol the stock symbol to lookup
   * @return {@link Weekly} time series data
   */
  public Weekly weekly(String symbol)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_WEEKLY);
    return Weekly.from(json);
  }

  /**
   * This API returns weekly adjusted time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly adjusted close, weekly volume, weekly dividend).
   *
   * @param symbol the stock symbol to lookup
   * @return {@link WeeklyAdjusted} time series data
   */
  public WeeklyAdjusted weeklyAdjusted(String symbol)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_WEEKLY_ADJUSTED);
    return WeeklyAdjusted.from(json);
  }

  /**
   * This API returns monthly time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly volume).
   *
   * @param symbol the stock symbol to lookup
   * @return {@link Monthly} time series data
   */
  public Monthly monthly(String symbol)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_MONTHLY);
    return Monthly.from(json);
  }

  /**
   * This API returns monthly adjusted time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly adjusted close, monthly volume, monthly dividend)
   *
   * @param symbol the stock symbol to lookup
   * @return {@link MonthlyAdjusted} time series data
   */
  public MonthlyAdjusted monthlyAdjusted(String symbol) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_MONTHLY_ADJUSTED);
    return MonthlyAdjusted.from(json);
  }

}

