import com.msiops.ground.either.Either;
import parameters.*;
import response.data.ResponseData;
import response.models.IntraDayModel;
import response.models.ResponseModel;

import java.io.IOException;

public class StockTimeSeries {
  private final Settings settings;
  private final ApiConnector apiConnector;

  public StockTimeSeries(Settings settings, ApiConnector apiConnector) {
    this.settings = settings;
    this.apiConnector = apiConnector;
  }

  public StockTimeSeries get(Settings settings) {
    return new StockTimeSeries(settings, new AlphaVantageConnector());
  }

  public Either<ResponseData, String> intraDay(String symbol, Interval interval, OutputSize outputSize) {
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

  private Either<String, String> getRequest(String symbol, UrlParameter... urlParameters) {
    String params = getParameters(symbol, urlParameters);
    try {
      String json = apiConnector.sendRequest(params, settings.getTimeout());
      return Either.left(json);
    } catch (IOException e) {
      return Either.right(e.getMessage());
    }
  }

  private String getParameters(String symbol, UrlParameter... urlParameters) {
    UrlParameterBuilder urlBuilder = new UrlParameterBuilder();
    for (UrlParameter parameter : urlParameters) {
      urlBuilder.append(parameter);
    }
    urlBuilder.append("symbol", symbol);
    urlBuilder.append("apikey", settings.getApiKey());
    return urlBuilder.getUrl();
  }
}

