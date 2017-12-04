package co.alphavantage.input;

/**
 * Parameters for api call. Ex: interval=15min&outputsize=full;
 * 'interval' is key, and '15min' is value.
 */
public interface ApiParameter {
  /**
   * get key for parameter
   * @return the key
   */
  String getKey();
  /**
   * get value for parameter
   * @return the value
   */
  String getValue();
}
