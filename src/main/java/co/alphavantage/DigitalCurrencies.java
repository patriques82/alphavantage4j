package co.alphavantage;

import co.alphavantage.input.Symbol;
import co.alphavantage.input.digitalcurrencies.Function;
import co.alphavantage.input.digitalcurrencies.Market;
import co.alphavantage.output.digitalcurrencies.Daily;
import co.alphavantage.output.digitalcurrencies.IntraDay;

/**
 * APIs under this section provide a wide range of data feed for digital and crypto currencies such as Bitcoin.
 */
public class DigitalCurrencies {

  private final ApiConnector apiConnector;

  /**
   * Constructs a Digital and Crypto Currencies Data api endpoint with the help of an {@link ApiConnector}
   *
   * @param apiConnector the connection to the api
   */
  public DigitalCurrencies(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * This API returns the realtime intraday time series (in 5-minute intervals) for any digital currency (e.g., BTC)
   * traded on a specific market (e.g., CNY/Chinese Yuan). Prices and volumes are quoted in both the market-specific
   * currency and USD.
   *
   * @param symbol The digital/crypto currency of your choice.
   * @param market The exchange market of your choice.
   * @return {@link IntraDay} time series data.
   */
  public IntraDay intraDay(String symbol, Market market)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.INTRADAY, market);
    return IntraDay.from(json);
  }

  /**
   * This API returns the daily historical time series for a digital currency (e.g., BTC) traded on a specific market
   * (e.g., CNY/Chinese Yuan), refreshed daily at midnight (UTC). Prices and volumes are quoted in both the
   * market-specific currency and USD.
   *
   * @param symbol The digital/crypto currency of your choice.
   * @param market The exchange market of your choice.
   * @return {@link Daily} time series data
   */
  public Daily daily(String symbol, Market market)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DAILY, market);
    return Daily.from(json);
  }

}
