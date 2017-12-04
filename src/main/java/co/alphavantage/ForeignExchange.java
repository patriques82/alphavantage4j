package co.alphavantage;
import com.msiops.ground.either.Either;

import co.alphavantage.input.timeseries.Function;

/**
 * 
 * Foreign Exchange Rate
 * 
 */
public class ForeignExchange {
	
	  private final ApiConnector apiConnector;
	  /**
	   * Constructs a ForeignExchange Data api endpoint with the help of an {@link ApiConnector}
	   * @param apiConnector the connection to the api
	   */
	  public ForeignExchange(ApiConnector apiConnector) {
		    this.apiConnector = apiConnector;
	  }

	  public Either<String, Exception> currencyExchangeRate(Currency fromCCY, Currency toCCY){
	    return apiConnector.getRequest(Function.CURRENCY_EXCHANGE, fromCCY, toCCY);
	  }

}
