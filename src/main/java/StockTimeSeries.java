import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StockTimeSeries {
    private final Gson gson;
    private final JsonParser parser;
    private final Settings settings;

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

    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String DATE_PATTERN = "YYYY-MM-DD";
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormat.forPattern(DATE_PATTERN);

    public StockTimeSeries(Settings settings) {
        gson = new Gson();
        parser = new JsonParser();
        this.settings = settings;
    }

    public Try<ResponseData> intraDay(String symbol) {
        return fetch(symbol, Function.INTRADAY);
    }

    public Try<ResponseData> daily(String symbol) {
        return fetch(symbol, Function.DAILY);
    }

    public Try<ResponseData> dailyAdjusted(String symbol) {
        return fetch(symbol, Function.DAILY_ADJUSTED);
    }

    public Try<ResponseData> weekly(String symbol) {
        return fetch(symbol, Function.WEEKLY);
    }

    public Try<ResponseData> weeklyAdjusted(String symbol) {
        return fetch(symbol, Function.WEEKLY_ADJUSTED);
    }

    public Try<ResponseData> monthly(String symbol) {
        return fetch(symbol, Function.MONTHLY);
    }

    public Try<ResponseData> monthlyAdjusted(String symbol) {
        return fetch(symbol, Function.MONTHLY_ADJUSTED);
    }

    private Try<ResponseData> fetch(String symbol, Function func) {
        String url = BASE_URL + "?" + getParameters(symbol, func);
        try {
            URL request = new URL(url);

            URLConnection connection = request.openConnection();
            connection.setConnectTimeout(settings.getTimeout());
            connection.setReadTimeout(settings.getTimeout());

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder responseBuilder = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }
            bufferedReader.close();
            String json = responseBuilder.toString();

            return parseJson(json);
        } catch (IOException e) {
            return Try.failure(e.getMessage());
        }
    }

    private String getParameters(String stockName, Function func) {
        final String query = "function=%s&outputsize=compact&symbol=%s&apikey=%s";
        final String function = func.getUrlParameter();
        final String apiKey = settings.getApiKey();
        return String.format(query, function, stockName, apiKey);
    }

    private Try<ResponseData> parseJson(String json) {
        JsonElement jsonElement = parser.parse(json);
        JsonObject rootObject = jsonElement.getAsJsonObject();

        JsonElement errorMessage = rootObject.get("Error Message");
        if (errorMessage != null) {
            return Try.failure(errorMessage.getAsString());
        }

        Type metaDataType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> metaDataPrototype = gson.fromJson(rootObject.get("Meta Data"), metaDataType);
        MetaData metaData = convertToMetaData(metaDataPrototype);

        Type stockDataType = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
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

