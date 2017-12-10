package co.alphavantage;

import co.alphavantage.input.ApiParameter;

/**
 * Connection to api endpoint.
 */
@FunctionalInterface
public interface ApiConnector {
  /**
   * Sends request to api
   * @param apiParameters the api parameters (required/optional) to the api call
   * @return Either the raw Json string
   */
  String getRequest(ApiParameter... apiParameters);
}
