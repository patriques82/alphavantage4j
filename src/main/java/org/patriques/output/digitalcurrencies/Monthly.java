package org.patriques.output.digitalcurrencies;

import org.patriques.input.digitalcurrencies.Market;
import org.patriques.output.JsonParser;
import org.patriques.output.digitalcurrencies.data.DigitalCurrencyData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of monthly response from api.
 *
 * @see DigitalCurrencyResponse
 */
public class Monthly extends DigitalCurrencyResponse<DigitalCurrencyData> {

  public Monthly(final Map<String, String> metaData,
                 final List<DigitalCurrencyData> digitalData) {
    super(metaData, digitalData);
  }

  /**
   * Creates {@code Monthly} instance from json.
   *
   * @param market parameter used to parse json correctly
   * @param json string to parse
   * @return Monthly instance
   */
  public static Monthly from(Market market, String json) {
    Parser parser = new Parser(market);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code Monthly}.
   *
   * @see DigitalCurrencyParser
   * @see JsonParser
   */
  private static class Parser extends DigitalCurrencyParser<Monthly> {

    /**
     * Used to find correct key values in json
     */
    private final Market market;

    public Parser(Market market) {
      this.market = market;
    }

    @Override
    String getDigitalCurrencyDataKey() {
      return "Time Series (Digital Currency Monthly)";
    }

    @Override
    Monthly resolve(Map<String, String> metaData,
                    Map<String, Map<String, String>> digitalCurrencyData) {
      List<DigitalCurrencyData> currencyDataList = new ArrayList<>();
      digitalCurrencyData.forEach((key, values) -> {
        currencyDataList.add(
                new DigitalCurrencyData(
                        LocalDate.parse(key, SIMPLE_DATE_FORMAT).atStartOfDay(),
                        Double.parseDouble(values.get("1a. open (" + market.getValue() + ")")),
                        Double.parseDouble(values.get("1b. open (USD)")),
                        Double.parseDouble(values.get("2a. high (" + market.getValue() + ")")),
                        Double.parseDouble(values.get("2b. high (USD)")),
                        Double.parseDouble(values.get("3a. low (" + market.getValue() + ")")),
                        Double.parseDouble(values.get("3b. low (USD)")),
                        Double.parseDouble(values.get("4a. close (" + market.getValue() + ")")),
                        Double.parseDouble(values.get("4b. close (USD)")),
                        Double.parseDouble(values.get("5. volume")),
                        Double.parseDouble(values.get("6. market cap (USD)"))
                )
        );
      });
      return new Monthly(metaData, currencyDataList);
    }
  }
}
