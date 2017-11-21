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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import parameters.*;
import response.MetaData;
import response.ResponseData;
import response.StockData;

public class StockTimeSeries {
  private final Gson gson;
  private final JsonParser parser;
  private final Settings settings;
  private final ApiConnector apiConnector;

  // Meta data
  private static final String INFORMATION = "1. Information";
  private static final String SYMBOL = "2. Symbol";
  private static final String LAST_REFRESH = "3. Last Refreshed";
  private static final String TIME_ZONE = "5. Time Zone";

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
    return getRequest(symbol, DATE_WITH_TIME_FORMAT, Function.INTRADAY, outputSize, interval);
  }

  public Try<ResponseData> intraDay(String symbol, Interval interval) {
    return getRequest(symbol, DATE_WITH_TIME_FORMAT, Function.INTRADAY, interval);
  }

  public Try<ResponseData> daily(String symbol, OutputSize outputSize) {
    return getRequest(symbol, DATE_FORMAT, Function.DAILY, outputSize);
  }

  public Try<ResponseData> daily(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.DAILY);
  }

  public Try<ResponseData> dailyAdjusted(String symbol, OutputSize outputSize) {
    return getRequest(symbol, DATE_FORMAT, Function.DAILY_ADJUSTED, outputSize);
  }

  public Try<ResponseData> dailyAdjusted(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.DAILY_ADJUSTED);
  }

  public Try<ResponseData> weekly(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.WEEKLY);
  }

  public Try<ResponseData> weeklyAdjusted(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.WEEKLY_ADJUSTED);
  }

  public Try<ResponseData> monthly(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY);
  }

  public Try<ResponseData> monthlyAdjusted(String symbol) {
    return getRequest(symbol, DATE_FORMAT, Function.MONTHLY_ADJUSTED);
  }

  private Try<ResponseData> getRequest(String symbol, DateTimeFormatter timeFormat, UrlParameter ...urlParameters) {
    String params = getParameters(symbol, urlParameters);
    try {
      String json = apiConnector.sendRequest(params, settings.getTimeout());
      return parseJson(json, timeFormat);
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
  private Try<ResponseData> parseJson(String json, DateTimeFormatter timeFormat) {
    JsonElement jsonElement = parser.parse(json);
    JsonObject rootObject = jsonElement.getAsJsonObject();

    JsonElement errorMessage = rootObject.get("Error Message");
    if (errorMessage != null) {
      return Try.failure(errorMessage.getAsString());
    }

    Type metaDataType = new TypeToken<Map<String, String>>() {}.getType();
    Map<String, String> metaDataPrototype = gson.fromJson(rootObject.get("Meta Data"), metaDataType);
    MetaData metaData = convertToMetaData(metaDataPrototype);

    Type stockDataType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
    Map<String, Map<String, String>> stockDataPrototype = gson.fromJson(rootObject.get("Time Series (Daily)"), stockDataType);
    List<StockData> stockData = convertToStockData(stockDataPrototype, timeFormat);

    return Try.success(new ResponseData(metaData, stockData));
  }

  private List<StockData> convertToStockData(Map<String, Map<String, String>> stockDataPrototype, DateTimeFormatter timeFormat) {
    return stockDataPrototype.entrySet().stream()
            .map(e -> {
              Map<String, String> valueMap = e.getValue();
              return new StockData(
                      parseDate(e.getKey(), timeFormat),
                      Double.parseDouble(valueMap.get(OPEN)),
                      Double.parseDouble(valueMap.get(HIGH)),
                      Double.parseDouble(valueMap.get(LOW)),
                      Double.parseDouble(valueMap.get(CLOSE)),
                      Long.parseLong(valueMap.get(VOLUME))
              );
            })
            .collect(Collectors.toList());
  }

  private MetaData convertToMetaData(Map<String, String> metaDataPrototype) {
    // TODO metaDataPrototype varies.
    return new MetaData(
            metaDataPrototype.get(INFORMATION),
            metaDataPrototype.get(SYMBOL),
            parseDate(metaDataPrototype.get(LAST_REFRESH), DATE_WITH_TIME_FORMAT),
            metaDataPrototype.get(TIME_ZONE)
    );
  }

  private DateTime parseDate(String dateString, DateTimeFormatter format) {
    return DateTime.parse(dateString, format);
  }

}

