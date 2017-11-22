import parameters.UrlParameter;
import parameters.UrlParameterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AlphaVantageConnector implements ApiConnector {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";
  private final Settings settings;

  public AlphaVantageConnector(Settings settings) {
    this.settings = settings;
  }

  @Override
  public String sendRequest(String symbol, UrlParameter ...urlParameters) throws IOException {
    String params = getParameters(symbol, urlParameters);
    URL request = new URL(BASE_URL + params);

    URLConnection connection = request.openConnection();
    connection.setConnectTimeout(settings.getTimeout());
    connection.setReadTimeout(settings.getTimeout());

    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
    BufferedReader bufferedReader = new BufferedReader(inputStream);
    StringBuilder responseBuilder = new StringBuilder();

    String line;
    while ((line = bufferedReader.readLine()) != null) {
      responseBuilder.append(line);
    }
    bufferedReader.close();
    return responseBuilder.toString();
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
