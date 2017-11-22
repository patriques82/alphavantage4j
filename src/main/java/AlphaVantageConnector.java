
import net.ApiConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AlphaVantageConnector implements ApiConnector {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";

  @Override
  public String sendRequest(String params, int timeout) throws IOException {
    URL request = new URL(BASE_URL + params);

    URLConnection connection = request.openConnection();
    connection.setConnectTimeout(timeout);
    connection.setReadTimeout(timeout);

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
}
