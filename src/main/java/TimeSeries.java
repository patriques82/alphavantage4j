import com.msiops.ground.either.Either;
import parameters.time_series.Function;
import parameters.time_series.Interval;
import parameters.time_series.OutputSize;
import response.data.ResponseData;
import response.data.time_series.StockData;
import response.models.time_series.*;

/**
 * The Stock Time Series Data provides realtime and historical equity data in 4 different temporal resolutions:
 * (1) intraday, (2) daily, (3) weekly, and (4) monthly.
 */
public class TimeSeries {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Time Series Data api endpoint with the help of an {@link ApiConnector}
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
  public Either<ResponseData<StockData>, Exception> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.INTRADAY, interval, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new IntraDay(interval)));
  }
  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> intraDay(String symbol, Interval interval) {
    return apiConnector.getRequest(symbol, Function.INTRADAY, interval)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new IntraDay(interval)));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> daily(String symbol, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.DAILY, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new Daily()));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> daily(String symbol) {
    return apiConnector.getRequest(symbol, Function.DAILY)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new Daily()));  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> dailyAdjusted(String symbol, OutputSize outputSize) {
    return apiConnector.getRequest(symbol, Function.DAILY_ADJUSTED, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyAdjusted()));
  }
  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume, daily adjusted close, and split/dividend events)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> dailyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.DAILY_ADJUSTED)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyAdjusted()));
  }
  /**
   * This API returns weekly time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> weekly(String symbol) {
    return apiConnector.getRequest(symbol, Function.WEEKLY)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new Weekly()));
  }
  /**
   * This API returns weekly adjusted time series (last trading day of each week, weekly open, weekly high, weekly low, weekly close, weekly adjusted close, weekly volume, weekly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> weeklyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.WEEKLY_ADJUSTED)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new WeeklyAdjusted()));
  }
  /**
   * This API returns monthly time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly volume)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> monthly(String symbol) {
    return apiConnector.getRequest(symbol, Function.MONTHLY)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new Monthly()));
  }
  /**
   * This API returns monthly adjusted time series (last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly adjusted close, monthly volume, monthly dividend)
   * @param symbol the stock symbol to lookup
   * @return either a successful response (left) or an exception (right)
   */
  public Either<ResponseData<StockData>, Exception> monthlyAdjusted(String symbol) {
    return apiConnector.getRequest(symbol, Function.MONTHLY_ADJUSTED)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new MonthlyAdjusted()));
  }

}

