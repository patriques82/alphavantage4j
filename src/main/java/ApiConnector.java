import input.ApiParameter;
import output.AlphaVantageException;

/**
 * Connection to api endpoint.
 */
@FunctionalInterface
public interface ApiConnector {
  /**
   * Sends request to api
   * @param symbol the stock symbol to lookup
   * @param apiParameters the api parameters (required/optional) to the api call
   * @return Either the raw Json string or IOExcpetion
   */
  String getRequest(String symbol, ApiParameter... apiParameters) throws AlphaVantageException;
}
