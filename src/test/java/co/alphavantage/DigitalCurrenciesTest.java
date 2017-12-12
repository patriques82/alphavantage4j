package co.alphavantage;

import co.alphavantage.input.digitalcurrencies.Market;
import co.alphavantage.output.digitalcurrencies.IntraDay;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DigitalCurrenciesTest {
  private DigitalCurrencies digitalCurrencies;

  @Test
  public void intraDay() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Intraday Prices and Volumes for Digital Currency\",\n" +
            "        \"2. Digital Currency Code\": \"DUMMY\",\n" +
            "        \"3. Digital Currency Name\": \"Dummy\",\n" +
            "        \"4. Market Code\": \"CNY\",\n" +
            "        \"5. Market Name\": \"Chinese Yuan\",\n" +
            "        \"6. Interval\": \"5min\",\n" +
            "        \"7. Last Refreshed\": \"2017-12-11 18:40:00\",\n" +
            "        \"8. Time Zone\": \"UTC\"\n" +
            "    },\n" +
            "    \"Time Series (Digital Currency Intraday)\": {\n" +
            "        \"2017-12-11 18:40:00\": {\n" +
            "            \"1a. price (CNY)\": \"114859.12710526\",\n" +
            "            \"1b. price (USD)\": \"17359.23693517\",\n" +
            "            \"2. volume\": \"0.21386198\",\n" +
            "            \"3. market cap (USD)\": \"3712.48078224\"\n" +
            "        },\n" +
            "        \"2017-12-11 18:35:00\": {\n" +
            "            \"1a. price (CNY)\": \"114859.12710526\",\n" +
            "            \"1b. price (USD)\": \"17359.23693517\",\n" +
            "            \"2. volume\": \"0.21386198\",\n" +
            "            \"3. market cap (USD)\": \"3712.48078224\"\n" +
            "        },\n" +
            "        \"2017-12-08 13:55:00\": {\n" +
            "            \"1a. price (CNY)\": \"101220.62669000\",\n" +
            "            \"1b. price (USD)\": \"15290.58451908\",\n" +
            "            \"2. volume\": \"0.17665000\",\n" +
            "            \"3. market cap (USD)\": \"2701.08175530\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    digitalCurrencies = new DigitalCurrencies(apiParameters -> json);

    IntraDay resp = digitalCurrencies.intraDay("DUMMY", Market.ALL);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Intraday Prices and Volumes for Digital Currency")));
    assertThat(metaData.get("2. Digital Currency Code"), is(equalTo("DUMMY")));
    assertThat(metaData.get("3. Digital Currency Name"), is(equalTo("Dummy")));
    assertThat(metaData.get("4. Market Code"), is(equalTo("CNY")));
    assertThat(metaData.get("5. Market Name"), is(equalTo("Chinese Yuan")));
    assertThat(metaData.get("6. Interval"), is(equalTo("5min")));
    assertThat(metaData.get("7. Last Refreshed"), is(equalTo("2017-12-11 18:40:00")));
    assertThat(metaData.get("8. Time Zone"), is(equalTo("UTC")));

    //List<DigitalCurrencyData> digitalData = resp.getDigitalData();
    //assertThat(digitalData.size(), is(equalTo(3)));

  }
}
