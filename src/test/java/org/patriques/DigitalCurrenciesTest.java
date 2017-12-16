package org.patriques;

import org.junit.Test;
import org.patriques.input.digitalcurrencies.Market;
import org.patriques.output.digitalcurrencies.Daily;
import org.patriques.output.digitalcurrencies.IntraDay;
import org.patriques.output.digitalcurrencies.Monthly;
import org.patriques.output.digitalcurrencies.Weekly;
import org.patriques.output.digitalcurrencies.data.DigitalCurrencyData;
import org.patriques.output.digitalcurrencies.data.SimpelDigitalCurrencyData;

import java.time.LocalDateTime;
import java.util.List;
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

    IntraDay resp = digitalCurrencies.intraDay("DUMMY", Market.CNY);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Intraday Prices and Volumes for Digital Currency")));
    assertThat(metaData.get("2. Digital Currency Code"), is(equalTo("DUMMY")));
    assertThat(metaData.get("3. Digital Currency Name"), is(equalTo("Dummy")));
    assertThat(metaData.get("4. Market Code"), is(equalTo("CNY")));
    assertThat(metaData.get("5. Market Name"), is(equalTo("Chinese Yuan")));
    assertThat(metaData.get("6. Interval"), is(equalTo("5min")));
    assertThat(metaData.get("7. Last Refreshed"), is(equalTo("2017-12-11 18:40:00")));
    assertThat(metaData.get("8. Time Zone"), is(equalTo("UTC")));

    List<SimpelDigitalCurrencyData> digitalCurrencyData = resp.getDigitalData();
    assertThat(digitalCurrencyData.size(), is(equalTo(3)));

    SimpelDigitalCurrencyData data = digitalCurrencyData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 11, 18, 40, 0))));
    assertThat(data.getPriceA(), is(equalTo(114859.12710526)));
    assertThat(data.getPriceB(), is(equalTo(17359.23693517)));
    assertThat(data.getVolume(), is(equalTo(0.21386198)));
    assertThat(data.getMarketCap(), is(equalTo(3712.48078224)));
  }

  @Test
  public void daily() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Daily Prices and Volumes for Digital Currency\",\n" +
            "        \"2. Digital Currency Code\": \"BTC\",\n" +
            "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
            "        \"4. Market Code\": \"CNY\",\n" +
            "        \"5. Market Name\": \"Chinese Yuan\",\n" +
            "        \"6. Last Refreshed\": \"2017-12-09 (end of day)\",\n" +
            "        \"7. Time Zone\": \"UTC\"\n" +
            "    },\n" +
            "    \"Time Series (Digital Currency Daily)\": {\n" +
            "        \"2017-12-09\": {\n" +
            "            \"1a. open (CNY)\": \"127123.63427426\",\n" +
            "            \"1b. open (USD)\": \"19214.73274937\",\n" +
            "            \"2a. high (CNY)\": \"128103.84174009\",\n" +
            "            \"2b. high (USD)\": \"19362.89107258\",\n" +
            "            \"3a. low (CNY)\": \"104939.89018000\",\n" +
            "            \"3b. low (USD)\": \"15861.66062722\",\n" +
            "            \"4a. close (CNY)\": \"119425.23825748\",\n" +
            "            \"4b. close (USD)\": \"18049.19986058\",\n" +
            "            \"5. volume\": \"0.56187000\",\n" +
            "            \"6. market cap (USD)\": \"10141.30392566\"\n" +
            "        },\n" +
            "        \"2017-12-08\": {\n" +
            "            \"1a. open (CNY)\": \"114354.57297884\",\n" +
            "            \"1b. open (USD)\": \"17274.36562166\",\n" +
            "            \"2a. high (CNY)\": \"129105.27893503\",\n" +
            "            \"2b. high (USD)\": \"19508.27245899\",\n" +
            "            \"3a. low (CNY)\": \"100833.84418000\",\n" +
            "            \"3b. low (USD)\": \"15228.13301719\",\n" +
            "            \"4a. close (CNY)\": \"127123.63427426\",\n" +
            "            \"4b. close (USD)\": \"19214.73274937\",\n" +
            "            \"5. volume\": \"1.05116000\",\n" +
            "            \"6. market cap (USD)\": \"20197.75847683\"\n" +
            "        },\n" +
            "        \"2014-04-01\": {\n" +
            "            \"1a. open (CNY)\": \"2813.95451515\",\n" +
            "            \"1b. open (USD)\": \"453.42760964\",\n" +
            "            \"2a. high (CNY)\": \"2918.63938797\",\n" +
            "            \"2b. high (USD)\": \"470.55596761\",\n" +
            "            \"3a. low (CNY)\": \"2813.95451515\",\n" +
            "            \"3b. low (USD)\": \"453.42760964\",\n" +
            "            \"4a. close (CNY)\": \"2859.93706764\",\n" +
            "            \"4b. close (USD)\": \"461.29103486\",\n" +
            "            \"5. volume\": \"5628.02114700\",\n" +
            "            \"6. market cap (USD)\": \"2596155.69911398\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    digitalCurrencies = new DigitalCurrencies(apiParameters -> json);

    Daily resp = digitalCurrencies.daily("BTC", Market.CNY);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Daily Prices and Volumes for Digital Currency")));
    assertThat(metaData.get("2. Digital Currency Code"), is(equalTo("BTC")));
    assertThat(metaData.get("3. Digital Currency Name"), is(equalTo("Bitcoin")));
    assertThat(metaData.get("4. Market Code"), is(equalTo("CNY")));
    assertThat(metaData.get("5. Market Name"), is(equalTo("Chinese Yuan")));
    assertThat(metaData.get("6. Last Refreshed"), is(equalTo("2017-12-09 (end of day)")));
    assertThat(metaData.get("7. Time Zone"), is(equalTo("UTC")));

    List<DigitalCurrencyData> digitalCurrencyData = resp.getDigitalData();
    assertThat(digitalCurrencyData.size(), is(equalTo(3)));

    DigitalCurrencyData data = digitalCurrencyData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 9, 0, 0))));
    assertThat(data.getOpenA(), is(equalTo(127123.63427426)));
    assertThat(data.getOpenB(), is(equalTo(19214.73274937)));
    assertThat(data.getHighA(), is(equalTo(128103.84174009)));
    assertThat(data.getHighB(), is(equalTo(19362.89107258)));
    assertThat(data.getLowA(), is(equalTo(104939.89018000)));
    assertThat(data.getLowB(), is(equalTo(15861.66062722)));
    assertThat(data.getCloseA(), is(equalTo(119425.23825748)));
    assertThat(data.getCloseB(), is(equalTo(18049.19986058)));
    assertThat(data.getVolume(), is(equalTo(0.56187000)));
    assertThat(data.getMarketCap(), is(equalTo(10141.30392566)));
  }

  @Test
  public void weekly() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Weekly Prices and Volumes for Digital Currency\",\n" +
            "        \"2. Digital Currency Code\": \"BTC\",\n" +
            "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
            "        \"4. Market Code\": \"CNY\",\n" +
            "        \"5. Market Name\": \"Chinese Yuan\",\n" +
            "        \"6. Last Refreshed\": \"2017-12-09 (end of day)\",\n" +
            "        \"7. Time Zone\": \"UTC\"\n" +
            "    },\n" +
            "    \"Time Series (Digital Currency Weekly)\": {\n" +
            "        \"2017-12-09\": {\n" +
            "            \"1a. open (CNY)\": \"127123.63427426\",\n" +
            "            \"1b. open (USD)\": \"19214.73274937\",\n" +
            "            \"2a. high (CNY)\": \"128103.84174009\",\n" +
            "            \"2b. high (USD)\": \"19362.89107258\",\n" +
            "            \"3a. low (CNY)\": \"104939.89018000\",\n" +
            "            \"3b. low (USD)\": \"15861.66062722\",\n" +
            "            \"4a. close (CNY)\": \"119425.23825748\",\n" +
            "            \"4b. close (USD)\": \"18049.19986058\",\n" +
            "            \"5. volume\": \"0.56187000\",\n" +
            "            \"6. market cap (USD)\": \"10141.30392566\"\n" +
            "        },\n" +
            "        \"2017-12-08\": {\n" +
            "            \"1a. open (CNY)\": \"114354.57297884\",\n" +
            "            \"1b. open (USD)\": \"17274.36562166\",\n" +
            "            \"2a. high (CNY)\": \"129105.27893503\",\n" +
            "            \"2b. high (USD)\": \"19508.27245899\",\n" +
            "            \"3a. low (CNY)\": \"100833.84418000\",\n" +
            "            \"3b. low (USD)\": \"15228.13301719\",\n" +
            "            \"4a. close (CNY)\": \"127123.63427426\",\n" +
            "            \"4b. close (USD)\": \"19214.73274937\",\n" +
            "            \"5. volume\": \"1.05116000\",\n" +
            "            \"6. market cap (USD)\": \"20197.75847683\"\n" +
            "        },\n" +
            "        \"2014-04-01\": {\n" +
            "            \"1a. open (CNY)\": \"2813.95451515\",\n" +
            "            \"1b. open (USD)\": \"453.42760964\",\n" +
            "            \"2a. high (CNY)\": \"2918.63938797\",\n" +
            "            \"2b. high (USD)\": \"470.55596761\",\n" +
            "            \"3a. low (CNY)\": \"2813.95451515\",\n" +
            "            \"3b. low (USD)\": \"453.42760964\",\n" +
            "            \"4a. close (CNY)\": \"2859.93706764\",\n" +
            "            \"4b. close (USD)\": \"461.29103486\",\n" +
            "            \"5. volume\": \"5628.02114700\",\n" +
            "            \"6. market cap (USD)\": \"2596155.69911398\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    digitalCurrencies = new DigitalCurrencies(apiParameters -> json);

    Weekly resp = digitalCurrencies.weekly("BTC", Market.CNY);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Weekly Prices and Volumes for Digital Currency")));
    assertThat(metaData.get("2. Digital Currency Code"), is(equalTo("BTC")));
    assertThat(metaData.get("3. Digital Currency Name"), is(equalTo("Bitcoin")));
    assertThat(metaData.get("4. Market Code"), is(equalTo("CNY")));
    assertThat(metaData.get("5. Market Name"), is(equalTo("Chinese Yuan")));
    assertThat(metaData.get("6. Last Refreshed"), is(equalTo("2017-12-09 (end of day)")));
    assertThat(metaData.get("7. Time Zone"), is(equalTo("UTC")));

    List<DigitalCurrencyData> digitalCurrencyData = resp.getDigitalData();
    assertThat(digitalCurrencyData.size(), is(equalTo(3)));

    DigitalCurrencyData data = digitalCurrencyData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 9, 0, 0))));
    assertThat(data.getOpenA(), is(equalTo(127123.63427426)));
    assertThat(data.getOpenB(), is(equalTo(19214.73274937)));
    assertThat(data.getHighA(), is(equalTo(128103.84174009)));
    assertThat(data.getHighB(), is(equalTo(19362.89107258)));
    assertThat(data.getLowA(), is(equalTo(104939.89018000)));
    assertThat(data.getLowB(), is(equalTo(15861.66062722)));
    assertThat(data.getCloseA(), is(equalTo(119425.23825748)));
    assertThat(data.getCloseB(), is(equalTo(18049.19986058)));
    assertThat(data.getVolume(), is(equalTo(0.56187000)));
    assertThat(data.getMarketCap(), is(equalTo(10141.30392566)));
  }

  @Test
  public void monthly() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Monthly Prices and Volumes for Digital Currency\",\n" +
            "        \"2. Digital Currency Code\": \"BTC\",\n" +
            "        \"3. Digital Currency Name\": \"Bitcoin\",\n" +
            "        \"4. Market Code\": \"CNY\",\n" +
            "        \"5. Market Name\": \"Chinese Yuan\",\n" +
            "        \"6. Last Refreshed\": \"2017-12-09 (end of day)\",\n" +
            "        \"7. Time Zone\": \"UTC\"\n" +
            "    },\n" +
            "    \"Time Series (Digital Currency Monthly)\": {\n" +
            "        \"2017-12-09\": {\n" +
            "            \"1a. open (CNY)\": \"127123.63427426\",\n" +
            "            \"1b. open (USD)\": \"19214.73274937\",\n" +
            "            \"2a. high (CNY)\": \"128103.84174009\",\n" +
            "            \"2b. high (USD)\": \"19362.89107258\",\n" +
            "            \"3a. low (CNY)\": \"104939.89018000\",\n" +
            "            \"3b. low (USD)\": \"15861.66062722\",\n" +
            "            \"4a. close (CNY)\": \"119425.23825748\",\n" +
            "            \"4b. close (USD)\": \"18049.19986058\",\n" +
            "            \"5. volume\": \"0.56187000\",\n" +
            "            \"6. market cap (USD)\": \"10141.30392566\"\n" +
            "        },\n" +
            "        \"2017-12-08\": {\n" +
            "            \"1a. open (CNY)\": \"114354.57297884\",\n" +
            "            \"1b. open (USD)\": \"17274.36562166\",\n" +
            "            \"2a. high (CNY)\": \"129105.27893503\",\n" +
            "            \"2b. high (USD)\": \"19508.27245899\",\n" +
            "            \"3a. low (CNY)\": \"100833.84418000\",\n" +
            "            \"3b. low (USD)\": \"15228.13301719\",\n" +
            "            \"4a. close (CNY)\": \"127123.63427426\",\n" +
            "            \"4b. close (USD)\": \"19214.73274937\",\n" +
            "            \"5. volume\": \"1.05116000\",\n" +
            "            \"6. market cap (USD)\": \"20197.75847683\"\n" +
            "        },\n" +
            "        \"2014-04-01\": {\n" +
            "            \"1a. open (CNY)\": \"2813.95451515\",\n" +
            "            \"1b. open (USD)\": \"453.42760964\",\n" +
            "            \"2a. high (CNY)\": \"2918.63938797\",\n" +
            "            \"2b. high (USD)\": \"470.55596761\",\n" +
            "            \"3a. low (CNY)\": \"2813.95451515\",\n" +
            "            \"3b. low (USD)\": \"453.42760964\",\n" +
            "            \"4a. close (CNY)\": \"2859.93706764\",\n" +
            "            \"4b. close (USD)\": \"461.29103486\",\n" +
            "            \"5. volume\": \"5628.02114700\",\n" +
            "            \"6. market cap (USD)\": \"2596155.69911398\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    digitalCurrencies = new DigitalCurrencies(apiParameters -> json);

    Monthly resp = digitalCurrencies.monthly("BTC", Market.CNY);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Monthly Prices and Volumes for Digital Currency")));
    assertThat(metaData.get("2. Digital Currency Code"), is(equalTo("BTC")));
    assertThat(metaData.get("3. Digital Currency Name"), is(equalTo("Bitcoin")));
    assertThat(metaData.get("4. Market Code"), is(equalTo("CNY")));
    assertThat(metaData.get("5. Market Name"), is(equalTo("Chinese Yuan")));
    assertThat(metaData.get("6. Last Refreshed"), is(equalTo("2017-12-09 (end of day)")));
    assertThat(metaData.get("7. Time Zone"), is(equalTo("UTC")));

    List<DigitalCurrencyData> digitalCurrencyData = resp.getDigitalData();
    assertThat(digitalCurrencyData.size(), is(equalTo(3)));

    DigitalCurrencyData data = digitalCurrencyData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 9, 0, 0))));
    assertThat(data.getOpenA(), is(equalTo(127123.63427426)));
    assertThat(data.getOpenB(), is(equalTo(19214.73274937)));
    assertThat(data.getHighA(), is(equalTo(128103.84174009)));
    assertThat(data.getHighB(), is(equalTo(19362.89107258)));
    assertThat(data.getLowA(), is(equalTo(104939.89018000)));
    assertThat(data.getLowB(), is(equalTo(15861.66062722)));
    assertThat(data.getCloseA(), is(equalTo(119425.23825748)));
    assertThat(data.getCloseB(), is(equalTo(18049.19986058)));
    assertThat(data.getVolume(), is(equalTo(0.56187000)));
    assertThat(data.getMarketCap(), is(equalTo(10141.30392566)));
  }
}
