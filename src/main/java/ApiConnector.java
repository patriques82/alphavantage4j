import com.msiops.ground.either.Either;
import parameters.ApiParameter;

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
  Either<String, Exception> getRequest(String symbol, ApiParameter... apiParameters);
}
