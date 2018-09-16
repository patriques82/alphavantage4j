package org.patriques;

import org.junit.Test;
import org.patriques.output.exchange.CurrencyExchange;
import org.patriques.output.exchange.data.CurrencyExchangeData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrencyExchangeTest {
  private ForeignExchange foreignExchange;

  @Test
  public void conversionFromEURToUSD() {
    String json = "" +
            "{\n" +
            "    \"Realtime Currency Exchange Rate\": {\n" +
            "        \"1. From_Currency Code\": \"BTC\",\n" +
            "        \"2. From_Currency Name\": \"Bitcoin\",\n" +
            "        \"3. To_Currency Code\": \"CNY\",\n" +
            "        \"4. To_Currency Name\": \"Chinese Yuan\",\n" +
            "        \"5. Exchange Rate\": \"115369.98857500\",\n" +
            "        \"6. Last Refreshed\": \"2017-12-12 20:06:05\",\n" +
            "        \"7. Time Zone\": \"UTC\"\n" +
            "    }\n" +
            "}";
    foreignExchange = new ForeignExchange(apiParameters -> json);
    CurrencyExchange exchange = foreignExchange.currencyExchangeRate("EUR", "USD");

    CurrencyExchangeData data = exchange.getData();
    assertThat(data.getFromCurrencyCode(), equalTo("BTC"));
    assertThat(data.getFromCurrencyName(), equalTo("Bitcoin"));
    assertThat(data.getToCurrencyCode(), equalTo("CNY"));
    assertThat(data.getToCurrencyName(), equalTo("Chinese Yuan"));
    assertThat(data.getExchangeRate(), equalTo(115369.98857500f));
    assertThat(data.getTime().toString(), equalTo("2017-12-12T20:06:05"));
    assertThat(data.getTimezone(), equalTo("UTC"));
  }

}
