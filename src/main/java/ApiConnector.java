import input.ApiParameter;
import output.AlphaVantageException;

/**
 * Connection to api endpoint.
 */
@FunctionalInterface
public interface ApiConnector {
  /**
   * Sends request to api
   * @param apiParameters the api parameters (required/optional) to the api call
   * @return Either the raw Json string or IOExcpetion
   */
  String getRequest(ApiParameter... apiParameters) throws AlphaVantageException;
}
