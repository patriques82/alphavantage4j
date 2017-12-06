package co.alphavantage;
import co.alphavantage.common.lib.Function;
import co.alphavantage.input.exchange.FromCurrency;
import co.alphavantage.output.AlphaVantageException;

/**
 * 
 * Foreign Exchange Rate
 * 
 */
public class CurrencyExchange {	
	
	  private final ApiConnector apiConnector;
	  /**
	   * Constructs a ForeignExchange Data api endpoint with the help of an {@link ApiConnector}
	   * @param apiConnector the connection to the api
	   */
	  public CurrencyExchange(ApiConnector apiConnector) {
		    this.apiConnector = apiConnector;
	  }

	  public String currencyExchangeRate(FromCurrency fromCCY, FromCurrency toCCY) throws AlphaVantageException{
	    String json = apiConnector.getRequest(Function.CURRENCY_EXCHANGE,fromCCY, toCCY);
	    return
	  }

}
