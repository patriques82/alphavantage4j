import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import common.Try;
import net.AlphaVantageConnector;
import net.ApiConnector;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import parameters.*;
import response.MetaData;
import response.ResponseData;
import response.StockData;
import response.metadata_templates.Intraday;
import response.metadata_templates.MetaDataTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StockTimeSeries {

  // Stock data
  private static final String OPEN = "1. open";
  private static final String HIGH = "2. high";
  private static final String LOW = "3. low";
  private static final String CLOSE = "4. close";
  private static final String VOLUME = "5. volume";

  private static final String DATE_PATTERN = "YYYY-MM-DD";
  private static final String DATE_WITH_TIME_PATTERN = "YYYY-MM-DD HH:mm:ss";
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern(DATE_PATTERN);
  private static final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormat.forPattern(DATE_WITH_TIME_PATTERN);

  private final Gson gson;
  private final JsonParser parser;
  private final Settings settings;
  private final ApiConnector apiConnector;

  StockTimeSeries(Settings settings, ApiConnector apiConnector) {
    gson = new Gson();
    parser = new JsonParser();
    this.settings = settings;
    this.apiConnector = apiConnector;
  }

  public StockTimeSeries get(Settings settings) {
    return new StockTimeSeries(settings, new AlphaVantageConnector());
  }

  public Try<ResponseData> intraDay(String symbol, Interval interval, OutputSize outputSize) {
    Try<String> jsonString = getRequest(symbol, Function.INTRADAY, outputSize, interval);
    if (jsonString.isFailure())
      return Try.failure(jsonString.getError());
    return parseJson(jsonString.getValue(), new Intraday(), "1min", DATE_WITH_TIME_FORMAT);
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

  private Try<String> getRequest(String symbol, UrlParameter ...urlParameters) {
    String params = getParameters(symbol, urlParameters);
    try {
      String json = apiConnector.sendRequest(params, settings.getTimeout());
      return Try.success(json);
    } catch (IOException e) {
      return Try.failure(e.getMessage());
    }
  }

  private String getParameters(String symbol, UrlParameter ...urlParameters) {
    UrlParameterBuilder urlBuilder = new UrlParameterBuilder();
    for (UrlParameter parameter : urlParameters) {
      urlBuilder.append(parameter);
    }
    urlBuilder.append("symbol", symbol);
    urlBuilder.append("apikey", settings.getApiKey());
    return urlBuilder.getUrl();
  }

  // TODO: move to separate class (JSONParser)
  private Try<ResponseData> parseJson(String json, MetaDataTemplate metaTemplate, String freq, DateTimeFormatter dateTimeFormatter) {
    JsonElement jsonElement = parser.parse(json);
    JsonObject rootObject = jsonElement.getAsJsonObject();

    JsonElement errorMessage = rootObject.get("Error Message");
    if (errorMessage != null) {
      return Try.failure(errorMessage.getAsString());
    }

    Type metaDataType = new TypeToken<Map<String, String>>() {}.getType();
    Map<String, String> metaDataResponse = gson.fromJson(rootObject.get("Meta Data"), metaDataType);
    MetaData metaData = metaTemplate.resolve(metaDataResponse);

    //TODO return Map<DateTime, StockData> instead (remove datetime from stockData and create type stock that has date)
    Type stockDataType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
    Map<String, Map<String, String>> stockDataResponse = gson.fromJson(rootObject.get("Time Series (" + freq + ")"), stockDataType);
    List<StockData> stockData = convertToStockData(stockDataResponse, dateTimeFormatter);

    return Try.success(new ResponseData(metaData, stockData));
  }

  private List<StockData> convertToStockData(Map<String, Map<String, String>> stockDataPrototype, DateTimeFormatter dateTimeFormatter) {
    return stockDataPrototype.entrySet().stream()
            .map(e -> {
              Map<String, String> valueMap = e.getValue();
              return new StockData(
                      DateTime.parse(e.getKey(), dateTimeFormatter),
                      Double.parseDouble(valueMap.get(OPEN)),
                      Double.parseDouble(valueMap.get(HIGH)),
                      Double.parseDouble(valueMap.get(LOW)),
                      Double.parseDouble(valueMap.get(CLOSE)),
                      Long.parseLong(valueMap.get(VOLUME))
              );
            })
            .collect(Collectors.toList());
  }

}

