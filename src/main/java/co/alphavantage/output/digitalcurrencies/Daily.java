package co.alphavantage.output.digitalcurrencies;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.digitalcurrencies.data.DigitalCurrencyData;

import java.util.List;
import java.util.Map;

/**
 * Representation of daily response from api.
 *
 * @see DigitalCurrencyResponse
 */
public class Daily extends DigitalCurrencyResponse {

  private Daily(final Map<String, String> metaData, final List<DigitalCurrencyData> digitalData) {
    super(metaData, digitalData);
  }

  /**
   * Creates {@code Daily} instance from json.
   *
   * @param json string to parse
   * @return Daily instance
   */
  public static Daily from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Daily}.
   *
   * @see DigitalCurrencyParser
   * @see JsonParser
   */
  private static class Parser extends DigitalCurrencyParser<Daily> {

    @Override
    String getDigitalCurrencyDataKey() {
      return "Time Series (Digital Currency Daily)";
    }

    @Override
    Daily resolve(Map<String, String> metaData, Map<String, Map<String, String>> digitalCurrencyData) {
      return null;
    }

  }
}
