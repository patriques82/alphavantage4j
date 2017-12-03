import input.Symbol;
import input.time_series.Function;
import input.time_series.Interval;
import input.time_series.OutputSize;
import output.AlphaVantageException;
import output.JsonParser;
import output.time_series.*;

/**
 * The Stock Time Series Data provides realtime and historical equity data in 4 different temporal resolutions:
 * (1) intraday, (2) daily, (3) weekly, and (4) monthly.
 */
public class TimeSeries {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Time Series Data api endpoint with the help of an {@link ApiConnector} and a {@link JsonParser}
   * @param apiConnector the connection to the api
   */
  public TimeSeries(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }
  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public IntraDay intraDay(String symbol, Interval interval, OutputSize outputSize) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.INTRADAY, interval, outputSize);
    return IntraDay.from(interval, json);
  }
  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @return either a successful response (left) or an exception (right)
   */
  public IntraDay intraDay(String symbol, Interval interval) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.INTRADAY, interval);
    return IntraDay.from(interval, json);
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public Daily daily(String symbol, OutputSize outputSize) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DAILY, outputSize);
    return Daily.from(json);
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Daily daily(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DAILY);
    return Daily.from(json);
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public DailyAdjusted dailyAdjusted(String symbol, OutputSize outputSize) throws AlphaVantageException{
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DAILY_ADJUSTED, outputSize);
    return DailyAdjusted.from(json);
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public DailyAdjusted dailyAdjusted(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DAILY_ADJUSTED);
    return DailyAdjusted.from(json);
  }
  /**
   * This API returns weekly time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Weekly weekly(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.WEEKLY);
    return Weekly.from(json);
  }
  /**
   * This API returns weekly adjusted time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly adjusted close, weekly volume, weekly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public WeeklyAdjusted weeklyAdjusted(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.WEEKLY_ADJUSTED);
    return WeeklyAdjusted.from(json);
  }
  /**
   * This API returns monthly time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Monthly monthly(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MONTHLY);
    return Monthly.from(json);
  }
  /**
   * This API returns monthly adjusted time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly adjusted close, monthly volume, monthly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public MonthlyAdjusted monthlyAdjusted(String symbol) throws AlphaVantageException {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.MONTHLY_ADJUSTED);
    return MonthlyAdjusted.from(json);
  }

}

