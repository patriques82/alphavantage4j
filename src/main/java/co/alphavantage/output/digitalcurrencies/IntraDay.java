package co.alphavantage.output.digitalcurrencies;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.digitalcurrencies.data.DigitalCurrencyData;

import java.util.List;
import java.util.Map;

/**
 * Representation of intra day response from api.
 *
 * @see DigitalCurrencyResponse
 */
public class IntraDay extends DigitalCurrencyResponse {
  public IntraDay(Map<String, String> metaData,
                  List<DigitalCurrencyData> digitalData) {
    super(metaData, digitalData);
  }

  /**
   * Creates {@code IntraDay} instance from json.
   *
   * @param json string to parse
   * @return IntraDay instance
   */
  public static IntraDay from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code IntraDay}.
   *
   * @see DigitalCurrencyParser
   * @see JsonParser
   */
  private static class Parser extends DigitalCurrencyParser<IntraDay> {

    @Override
    IntraDay resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> digitalCurrencyData) {
      return new IntraDay(metaData, null);
    }

    @Override
    String getDigitalCurrencyDataKey() {
      return "Time Series (Digital Currency Intraday)";
    }

  }
}
