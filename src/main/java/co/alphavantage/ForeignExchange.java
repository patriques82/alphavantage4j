package co.alphavantage;

import co.alphavantage.input.Function;
import co.alphavantage.input.exchange.FromCurrency;
import co.alphavantage.input.exchange.ToCurrency;
import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.exchange.CurrencyExchange;

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
