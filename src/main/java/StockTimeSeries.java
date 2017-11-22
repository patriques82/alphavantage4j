import com.msiops.ground.either.Either;
import parameters.Function;
import parameters.Interval;
import parameters.OutputSize;
import parameters.UrlParameter;
import response.data.ResponseData;
import response.models.IntraDayModel;
import response.models.ResponseModel;

import java.io.IOException;

public class StockTimeSeries {
  private final ApiConnector apiConnector;

  StockTimeSeries(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  public static StockTimeSeries get(Settings settings) {
    return new StockTimeSeries(new AlphaVantageConnector(settings));
  }

  public Either<ResponseData, Exception> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    ResponseModel responseModel = new IntraDayModel(ResponseModel.DATE_WITH_TIME_FORMAT, interval);
    return getRequest(symbol, Function.INTRADAY, interval, outputSize)
            .flatMap(jsonString -> JsonParser.parseJson(jsonString, responseModel));
  }

//  public Try<ResponseData> intraDay(String symbol, Interval interval) {
//    return getRequest(symbol, DATE_WITH_TIME_FORMAT, Function.INTRADAY, interval);
//  }
//
//  public Try<ResponseData> daily(String symbol, OutputSize outputSize) {
//    return getRequest(symbol, DATE_FORMAT, Function.DAILY, outputSize);
//  }
//
//  public Try<ResponseData> daily(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.DAILY);
//  }
//
//  public Try<ResponseData> dailyAdjusted(String symbol, OutputSize outputSize) {
//    return getRequest(symbol, DATE_FORMAT, Function.DAILY_ADJUSTED, outputSize);
//  }
//
//  public Try<ResponseData> dailyAdjusted(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.DAILY_ADJUSTED);
//  }
//
//  public Try<ResponseData> weekly(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.WEEKLY);
//  }
//
//  public Try<ResponseData> weeklyAdjusted(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.WEEKLY_ADJUSTED);
//  }
//
//  public Try<ResponseData> monthly(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY);
//  }
//
//  public Try<ResponseData> monthlyAdjusted(String symbol) {
//    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY_ADJUSTED);
//  }

  private Either<String, Exception> getRequest(String symbol, UrlParameter... urlParameters) {
    try {
      String json = apiConnector.sendRequest(symbol, urlParameters);
      return Either.left(json);
    } catch (IOException e) {
      return Either.right(e);
    }
  }
}

