import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import javax.annotation.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import api_parameters.*;
import api_response.MetaData;
import api_response.ResponseData;
import api_response.StockData;

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
  private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormat.forPattern(DATE_PATTERN);

  public StockTimeSeries(Settings settings, ApiConnector apiConnector) {
    gson = new Gson();
    parser = new JsonParser();
    this.settings = settings;
    this.apiConnector = apiConnector;
  }

  public Try<ResponseData> intraDay(String symbol, Interval interval, @Nullable OutputSize outputSize) {
    return fetch(symbol, Function.INTRADAY, outputSize);
  }

  public Try<ResponseData> daily(String symbol, @Nullable OutputSize outputSize) {
    return fetch(symbol, Function.DAILY, outputSize);
  }

  public Try<ResponseData> dailyAdjusted(String symbol, @Nullable OutputSize outputSize) {
    return fetch(symbol, Function.DAILY_ADJUSTED, outputSize);
  }

  public Try<ResponseData> weekly(String symbol) {
    return fetch(symbol, Function.WEEKLY, null);
  }

  public Try<ResponseData> weeklyAdjusted(String symbol) {
    return fetch(symbol, Function.WEEKLY_ADJUSTED, null);
  }

  public Try<ResponseData> monthly(String symbol) {
    return fetch(symbol, Function.MONTHLY, null);
  }

  public Try<ResponseData> monthlyAdjusted(String symbol) {
    return fetch(symbol, Function.MONTHLY_ADJUSTED, null);
  }

  private Try<ResponseData> fetch(String symbol, Function func, @Nullable OutputSize outputSize) {
    String params = getParameters(symbol, func, outputSize);
    try {
      String json = apiConnector.sendRequest(params, settings.getTimeout());
      return parseJson(json);
    } catch (IOException e) {
      return Try.failure(e.getMessage());
    }
  }

  private String getParameters(String symbol, Function func, @Nullable OutputSize outputSize) {
    UrlParameterBuilder urlBuilder = new UrlParameterBuilder();
    urlBuilder.append(func);
    if (outputSize != null) {
      urlBuilder.append(outputSize);
    }
    urlBuilder.append("symbol", symbol);
    urlBuilder.append("apikey", settings.getApiKey());
    return urlBuilder.getUrl();
  }

  private Try<ResponseData> parseJson(String json) {
    JsonElement jsonElement = parser.parse(json);
    JsonObject rootObject = jsonElement.getAsJsonObject();

    JsonElement errorMessage = rootObject.get("Error Message");
    if (errorMessage != null) {
      return Try.failure(errorMessage.getAsString());
    }

    Type metaDataType = new TypeToken<Map<String, String>>() {
    }.getType();
    Map<String, String> metaDataPrototype = gson.fromJson(rootObject.get("Meta Data"), metaDataType);
    MetaData metaData = convertToMetaData(metaDataPrototype);

    Type stockDataType = new TypeToken<Map<String, Map<String, String>>>() {
    }.getType();
    Map<String, Map<String, String>> stockDataPrototype = gson.fromJson(rootObject.get("Time Series (Daily)"), stockDataType);
    List<StockData> stockData = convertToStockData(stockDataPrototype);

    return Try.success(new ResponseData(metaData, stockData));
  }

  private List<StockData> convertToStockData(Map<String, Map<String, String>> stockDataPrototype) {
    return stockDataPrototype.entrySet().stream()
            .map(e -> {
              Map<String, String> valueMap = e.getValue();
              return new StockData(
                      parseDate(e.getKey()),
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
    return new MetaData(
            metaDataPrototype.get(INFORMATION),
            metaDataPrototype.get(SYMBOL),
            parseDate(metaDataPrototype.get(LAST_REFRESH)),
            metaDataPrototype.get(TIME_ZONE)
    );
  }

  private DateTime parseDate(String dateString) {
    return DateTime.parse(dateString, DATETIME_FORMAT);
  }

}

