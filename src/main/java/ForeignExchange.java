import com.msiops.ground.either.Either;

import parameters.Function;
import response.data.ResponseData;

/**
 * 
 * Foreign Exchange Rate
 * 
 */
public class ForeignExchange {
	
	  private final ApiConnector apiConnector;
	  /**
	   * TODO
	   * @param apiConnector the connection to the api
	   */
	  public ForeignExchange(ApiConnector apiConnector) {
		    this.apiConnector = apiConnector;
	  }

	  
	  
	public Either<ResponseData, Exception> currencyExchangeRate(String fromCCY, String toCCY){
	    return apiConnector.getRequest(symbol, Function.CURRENCY_EXCHANGE, interval, outputSize)

	}

}
