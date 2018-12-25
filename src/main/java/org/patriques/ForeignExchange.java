package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.exchange.FromCurrency;
import org.patriques.input.exchange.ToCurrency;
import org.patriques.input.symbol.FromSymbol;
import org.patriques.input.symbol.ToSymbol;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.exchange.CurrencyExchange;
import org.patriques.output.exchange.Daily;

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

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   *
   * @param fromSymbol the forex symbol convert from
   * @param toSymbol the forex symbol convert to
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link Daily} daily forex data
   */
  public Daily daily(String fromSymbol, String toSymbol, OutputSize outputSize) throws AlphaVantageException {
    String json = apiConnector.getRequest(Function.FX_DAILY, new FromSymbol(fromSymbol), new ToSymbol(toSymbol), outputSize);
    return Daily.from(json);
  }
}
