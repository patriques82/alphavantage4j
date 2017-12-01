import com.msiops.ground.either.Either;
import input.time_series.Function;
import input.time_series.Interval;
import input.time_series.OutputSize;
import output.JsonParser;
import output.time_series.*;
import output.time_series.data.ResponseData;

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
  public Either<ResponseData, Exception> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.INTRADAY, interval, outputSize)
            .flatMap(jsonString -> new IntraDay(interval).parseJson(jsonString));
  }
  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> intraDay(String symbol, Interval interval) {
    return apiConnector.getRequest(symbol, Function.INTRADAY, interval)
            .flatMap(jsonString -> new IntraDay(interval).parseJson(jsonString));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> daily(String symbol, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.DAILY, outputSize)
            .flatMap(jsonString -> new Daily().parseJson(jsonString));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> daily(String symbol) {
    return apiConnector.getRequest(symbol, Function.DAILY)
            .flatMap(jsonString -> new Daily().parseJson(jsonString));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> dailyAdjusted(String symbol, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.DAILY_ADJUSTED, outputSize)
            .flatMap(jsonString -> new DailyAdjusted().parseJson(jsonString));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> dailyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.DAILY_ADJUSTED)
            .flatMap(jsonString -> new DailyAdjusted().parseJson(jsonString));
  }
  /**
   * This API returns weekly time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> weekly(String symbol) {
    return apiConnector.getRequest(symbol, Function.WEEKLY)
            .flatMap(jsonString -> new Weekly().parseJson(jsonString));
  }
  /**
   * This API returns weekly adjusted time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly adjusted close, weekly volume, weekly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> weeklyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.WEEKLY_ADJUSTED)
            .flatMap(jsonString -> new WeeklyAdjusted().parseJson(jsonString));
  }
  /**
   * This API returns monthly time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> monthly(String symbol) {
    return apiConnector.getRequest(symbol, Function.MONTHLY)
            .flatMap(jsonString -> new Monthly().parseJson(jsonString));
  }
  /**
   * This API returns monthly adjusted time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly adjusted close, monthly volume, monthly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData, Exception> monthlyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.MONTHLY_ADJUSTED)
            .flatMap(jsonString -> new MonthlyAdjusted().parseJson(jsonString));
  }

}

