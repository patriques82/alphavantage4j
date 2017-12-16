package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.exchange.FromCurrency;
import org.patriques.input.exchange.ToCurrency;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.exchange.CurrencyExchange;

/**
 * Foreign Exchange Rate
 */
public class ForeignExchange {

  private final ApiConnector apiConnector;

  /**
   * Constructs a ForeignExchange Data api endpoint with the help of an {@link ApiConnector}.
   *
   * @param apiConnector the connection to the api
   */
  public ForeignExchange(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * Functionality to show the ratio exchange between two currencies.
   *
   * @return {@link CurrencyExchange} data
   */
  public CurrencyExchange currencyExchangeRate(String fromCCY, String toCCY) throws AlphaVantageException {
    String json = apiConnector.getRequest(Function.CURRENCY_EXCHANGE_RATE, new FromCurrency(fromCCY), new ToCurrency(toCCY));
    return CurrencyExchange.from(json);
  }
}
