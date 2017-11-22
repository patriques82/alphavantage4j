import common.Try;
import net.ApiConnector;
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

  public Try<ResponseData> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    Try<String> jsonString = getRequest(symbol, Function.INTRADAY, interval, outputSize);
    if (jsonString.isFailure())
      return Try.failure(jsonString.getError());
    ResponseModel responseModel = new IntraDayModel(ResponseModel.DATE_WITH_TIME_FORMAT, interval);
    return JsonParser.parseJson(jsonString.getValue(), responseModel);
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

  private Try<String> getRequest(String symbol, UrlParameter... urlParameters) {
    String params = getParameters(symbol, urlParameters);
    try {
      String json = apiConnector.sendRequest(params, settings.getTimeout());
      return Try.success(json);
    } catch (IOException e) {
      return Try.failure(e.getMessage());
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

