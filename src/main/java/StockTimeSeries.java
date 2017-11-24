import com.msiops.ground.either.Either;
import parameters.Function;
import parameters.Interval;
import parameters.OutputSize;
import parameters.UrlParameter;
import response.data.ResponseData;
import response.models.DailyAdjustedModel;
import response.models.DailyModel;
import response.models.IntraDayModel;
import response.models.WeeklyModel;

import java.io.IOException;

public class StockTimeSeries {
  private final ApiConnector apiConnector;

  public StockTimeSeries(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  public Either<ResponseData, Exception> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    return getRequest(symbol, Function.INTRADAY, interval, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new IntraDayModel(interval)));
  }

  public Either<ResponseData, Exception> intraDay(String symbol, Interval interval) {
    return getRequest(symbol, Function.INTRADAY, interval)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new IntraDayModel(interval)));
  }

  public Either<ResponseData, Exception> daily(String symbol, OutputSize outputSize) {
    return getRequest(symbol, Function.DAILY, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyModel()));
  }

  public Either<ResponseData, Exception> daily(String symbol) {
    return getRequest(symbol, Function.DAILY)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyModel()));  }

  public Either<ResponseData, Exception> dailyAdjusted(String symbol, OutputSize outputSize) {
    return getRequest(symbol, Function.DAILY_ADJUSTED, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyAdjustedModel()));
  }

  public Either<ResponseData, Exception> dailyAdjusted(String symbol) {
    return getRequest(symbol, Function.DAILY_ADJUSTED)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new DailyAdjustedModel()));
  }

  public Either<ResponseData, Exception> weekly(String symbol) {
    return getRequest(symbol, Function.WEEKLY)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, new WeeklyModel()));
  }

//  public Try<ResponseData> weeklyAdjusted(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.WEEKLY_ADJUSTED);
//  }

//  public Try<ResponseData> monthly(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY);
//  }
//
//  public Try<ResponseData> monthlyAdjusted(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY_ADJUSTED);
//  }

  private Either<String, Exception> getRequest(String symbol, UrlParameter ...urlParameters) {
    try {
      String json = apiConnector.sendRequest(symbol, urlParameters);
      return Either.left(json);
    } catch (IOException e) {
      return Either.right(e);
    }
  }
}

