import org.junit.Before;
import org.junit.Test;

import com.msiops.ground.either.Either;

import parameters.FromCurrency;
import parameters.ToCurrency;

public class ForeignExchangeTest {
	private ForeignExchange foreignExchange;
	
	@Before
	public void setup(){
		String json = "{\r\n  \"Realtime Currency Exchange Rate\": {\r\n    \"1. From_Currency Code\": \"EUR\",\r\n    \"2. From_Currency Name\": \"Euro\",\r\n    \"3. To_Currency Code\": \"USD\",\r\n    \"4. To_Currency Name\": \"United States Dollar\",\r\n    \"5. Exchange Rate\": \"1.18929963\",\r\n    \"6. Last Refreshed\": \"2017-12-01 23:28:54\",\r\n    \"7. Time Zone\": \"UTC\"\r\n  }\r\n}";
		foreignExchange = new ForeignExchange((symbol, parameters) -> Either.left(json));
	}
	
	@Test
	public void conversionFromEURToUSD(){
	    Either<String, Exception> resp = foreignExchange.currencyExchangeRate(FromCurrency.EUR, ToCurrency.USD);

	    System.out.println("------------" + resp);
	}

}
