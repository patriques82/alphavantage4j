package org.patriques.output.digitalcurrencies;

import org.patriques.input.digitalcurrencies.Market;
import org.patriques.output.JsonParser;
import org.patriques.output.digitalcurrencies.data.SimpelDigitalCurrencyData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of intra day response from api.
 *
 * @see DigitalCurrencyResponse
 */
public class IntraDay extends DigitalCurrencyResponse<SimpelDigitalCurrencyData> {

  public IntraDay(final Map<String, String> metaData,
                  final List<SimpelDigitalCurrencyData> digitalData) {
    super(metaData, digitalData);
  }

  /**
   * Creates {@code IntraDay} instance from json.
   *
   * @param json string to parse
   * @return IntraDay instance
   */
  public static IntraDay from(Market market, String json) {
    Parser parser = new Parser(market);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code IntraDay}.
   *
   * @see DigitalCurrencyParser
   * @see JsonParser
   */
  private static class Parser extends DigitalCurrencyParser<IntraDay> {

    /**
     * Used to find correct key values in json
     */
    private final Market market;

    public Parser(Market market) {
      this.market = market;
    }

    @Override
    String getDigitalCurrencyDataKey() {
      return "Time Series (Digital Currency Intraday)";
    }

    @Override
    IntraDay resolve(Map<String, String> metaData,
                     Map<String, Map<String, String>> digitalCurrencyData) {
      List<SimpelDigitalCurrencyData> currencyDataList = new ArrayList<>();
      digitalCurrencyData.forEach((key, values) -> {
        currencyDataList.add(
                new SimpelDigitalCurrencyData(
                        LocalDateTime.parse(key, DATE_WITH_TIME_FORMAT),
                        Double.parseDouble(values.get("1a. price (" + market.getValue() + ")")),
                        Double.parseDouble(values.get("1b. price (USD)")),
                        Double.parseDouble(values.get("2. volume")),
                        Double.parseDouble(values.get("3. market cap (USD)"))
                )
        );
      });
      return new IntraDay(metaData, currencyDataList);
    }

  }
}
