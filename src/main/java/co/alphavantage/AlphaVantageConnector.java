package co.alphavantage;

import co.alphavantage.input.ApiParameter;
import co.alphavantage.input.ApiParameterBuilder;
import co.alphavantage.output.AlphaVantageException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Connection to Alpha Vantage API.
 */
public class AlphaVantageConnector implements ApiConnector {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";
  private final String apiKey;
  private final int timeOut;

  /**
   * Create Alpha
   * @param apiKey
   * @param timeOut
   */
  public AlphaVantageConnector(String apiKey, int timeOut) {
    this.apiKey = apiKey;
    this.timeOut = timeOut;
  }

  @Override
  public String getRequest(ApiParameter... apiParameters) {
    String params = getParameters(apiParameters);
    try {
      URL request = new URL(BASE_URL + params);
      URLConnection connection = request.openConnection();
      connection.setConnectTimeout(timeOut);
      connection.setReadTimeout(timeOut);

      InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
      BufferedReader bufferedReader = new BufferedReader(inputStream);
      StringBuilder responseBuilder = new StringBuilder();

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        responseBuilder.append(line);
      }
      bufferedReader.close();
      return responseBuilder.toString();
    } catch (IOException e) {
      throw new AlphaVantageException("failure sending request", e);
    }
  }

  private String getParameters(ApiParameter... apiParameters) {
    ApiParameterBuilder urlBuilder = new ApiParameterBuilder();
    for (ApiParameter parameter : apiParameters) {
      urlBuilder.append(parameter);
    }
    urlBuilder.append("apikey", apiKey);
    return urlBuilder.getUrl();
  }
}
