package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.Symbol;
import org.patriques.input.digitalcurrencies.Market;
import org.patriques.output.digitalcurrencies.Daily;
import org.patriques.output.digitalcurrencies.IntraDay;
import org.patriques.output.digitalcurrencies.Monthly;
import org.patriques.output.digitalcurrencies.Weekly;

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
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DIGITAL_CURRENCY_INTRADAY, market);
    return IntraDay.from(market, json);
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
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DIGITAL_CURRENCY_DAILY, market);
    return Daily.from(market, json);
  }

  /**
   * This API returns the weekly historical time series for a digital currency (e.g., BTC) traded on a specific market
   * (e.g., CNY/Chinese Yuan), refreshed daily at midnight (UTC). Prices and volumes are quoted in both the
   * market-specific currency and USD.
   *
   * @param symbol The digital/crypto currency of your choice.
   * @param market The exchange market of your choice.
   * @return {@link Weekly} time series data
   */
  public Weekly weekly(String symbol, Market market) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DIGITAL_CURRENCY_WEEKLY, market);
    return Weekly.from(market, json);
  }

  /**
   * This API returns the monthly historical time series for a digital currency (e.g., BTC) traded on a specific market
   * (e.g., CNY/Chinese Yuan), refreshed daily at midnight (UTC). Prices and volumes are quoted in both the
   * market-specific currency and USD.
   *
   * @param symbol The digital/crypto currency of your choice.
   * @param market The exchange market of your choice.
   * @return {@link Monthly} time series data
   */
  public Monthly monthly(String symbol, Market market) {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.DIGITAL_CURRENCY_WEEKLY, market);
    return Monthly.from(market, json);
  }
}
