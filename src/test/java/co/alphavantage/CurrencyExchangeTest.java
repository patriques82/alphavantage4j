package co.alphavantage;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import co.alphavantage.output.exchange.CurrencyExchange;
import co.alphavantage.output.exchange.data.CurrencyExchangeData;

public class CurrencyExchangeTest {
	private ForeignExchange foreignExchange;
	
	@Test
	public void conversionFromEURToUSD(){
		String json = "{\r\n  \"Realtime Currency Exchange Rate\": {\r\n    \"1. From_Currency Code\": \"EUR\",\r\n    \"2. From_Currency Name\": \"Euro\",\r\n    \"3. To_Currency Code\": \"USD\",\r\n    \"4. To_Currency Name\": \"United States Dollar\",\r\n    \"5. Exchange Rate\": \"1.18929963\",\r\n    \"6. Last Refreshed\": \"2017-12-01 23:28:54\",\r\n    \"7. Time Zone\": \"UTC\"\r\n  }\r\n}";
		foreignExchange = new ForeignExchange(apiParameters -> json);
		CurrencyExchange exchange = foreignExchange.currencyExchangeRate("EUR", "USD");
		CurrencyExchangeData data = exchange.getData();
	    assertThat(data.getFromCurrencyCode(), equalTo("EUR"));
	    assertThat(data.getFromCurrencyName(),equalTo("Euro"));
	    assertThat(data.getToCurrencyCode(),equalTo("USD"));
	    assertThat(data.getToCurrencyName(),equalTo("United States Dollar"));
	    assertThat(data.getExchangeRate(),equalTo(1.18929963f));
	    assertThat(data.getTime().toString(),equalTo("2017-12-01T23:28:54"));
	    assertThat(data.getTimezone(),equalTo("UTC"));
	}

}
