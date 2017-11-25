import parameters.ApiParameter;

import java.io.IOException;

/**
 * Connection to api endpoint.
 */
@FunctionalInterface
public interface ApiConnector {
  /**
   * Sends request to api
   * @param symbol the stock symbol to lookup
   * @param apiParameters the api parameters (required/optional) to the api call
   * @return the raw Json string
   * @throws IOException
   */
  String sendRequest(String symbol, ApiParameter... apiParameters) throws IOException;
}
