import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.msiops.ground.either.Either;
import org.joda.time.DateTime;
import org.junit.Test;
import parameters.Interval;
import parameters.OutputSize;
import response.data.MetaData;
import response.data.ResponseData;
import response.data.StockData;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StockTimeSeriesTest {
  private StockTimeSeries stockTimeSeries;

  @Test
  public void changeOfAPI() {
    String unexpectedJson = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"Information\": \"new api\"" +
            "        \"Symbol\": \"DUMMY\",\n" +
            "        \"New key\": \"new value\",\n" +
            "    },\n" +
            "    \"Time Series (1min)\": {\n" +
            "        \"2017-11-17 16:00:00\": {\n" +
            "            \"open\": \"82.3900\",\n" +
            "            \"high\": \"82.4200\",\n" +
            "            \"low\": \"82.3600\",\n" +
            "            \"close\": \"82.4000\",\n" +
            "            \"volume\": \"2285396\"\n" +
            "        },\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> unexpectedJson);

    Either<ResponseData, Exception> resp = stockTimeSeries.intraDay("DUMMY", Interval.ONE_MIN, OutputSize.COMPACT);
    assertThat(resp.isLeft(), is(equalTo(false)));
    assertThat(resp.getRight(), is(instanceOf(JsonSyntaxException.class)));
    assertThat(resp.getRight().getCause(), is(instanceOf(MalformedJsonException.class)));
  }

  @Test
  public void nonExistingSymbol() {
    String json = "{\"Error Message\": \"Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for TIME_SERIES_INTRADAY.\"}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.intraDay("NONEXISTING", Interval.ONE_MIN, OutputSize.COMPACT);
    assertThat(resp.isLeft(), is(equalTo(false)));
    assertThat(resp.getRight(), is(instanceOf(RuntimeException.class)));
    assertThat(resp.getRight().getMessage(), containsString("Invalid API call"));
  }

  @Test
  public void intraDay() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"IntraDayModel (1min) prices and volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-17 16:00:00\",\n" +
            "        \"4. Interval\": \"1min\",\n" +
            "        \"5. Output Size\": \"Compact\",\n" +
            "        \"6. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (1min)\": {\n" +
            "        \"2017-11-17 16:00:00\": {\n" +
            "            \"1. open\": \"82.3900\",\n" +
            "            \"2. high\": \"82.4200\",\n" +
            "            \"3. low\": \"82.3600\",\n" +
            "            \"4. close\": \"82.4000\",\n" +
            "            \"5. volume\": \"2285396\"\n" +
            "        },\n" +
            "        \"2017-11-17 15:59:00\": {\n" +
            "            \"1. open\": \"82.4400\",\n" +
            "            \"2. high\": \"82.4400\",\n" +
            "            \"3. low\": \"82.3900\",\n" +
            "            \"4. close\": \"82.3900\",\n" +
            "            \"5. volume\": \"299116\"\n" +
            "        },\n" +
            "        \"2017-11-17 15:58:00\": {\n" +
            "            \"1. open\": \"82.4500\",\n" +
            "            \"2. high\": \"82.4550\",\n" +
            "            \"3. low\": \"82.4400\",\n" +
            "            \"4. close\": \"82.4500\",\n" +
            "            \"5. volume\": \"52945\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.intraDay("DUMMY", Interval.ONE_MIN, OutputSize.COMPACT);
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("IntraDayModel (1min) prices and volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-17 16:00:00")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("1min")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("Compact")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 17, 16, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(82.39)));
    assertThat(stock.getHigh(), is(equalTo(82.42)));
    assertThat(stock.getLow(), is(equalTo(82.36)));
    assertThat(stock.getClose(), is(equalTo(82.40)));
    assertThat(stock.getVolume(), is(equalTo(2285396L)));
  }

  @Test
  public void daily() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Daily Prices (open, high, low, close) and Volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24 12:47:32\",\n" +
            "        \"4. Output Size\": \"Compact\",\n" +
            "        \"5. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (Daily)\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"83.0100\",\n" +
            "            \"2. high\": \"83.3700\",\n" +
            "            \"3. low\": \"82.7800\",\n" +
            "            \"4. close\": \"83.3200\",\n" +
            "            \"5. volume\": \"4612054\"\n" +
            "        },\n" +
            "        \"2017-11-22\": {\n" +
            "            \"1. open\": \"83.8300\",\n" +
            "            \"2. high\": \"83.9000\",\n" +
            "            \"3. low\": \"83.0400\",\n" +
            "            \"4. close\": \"83.1100\",\n" +
            "            \"5. volume\": \"20213704\"\n" +
            "        },\n" +
            "        \"2017-07-06\": {\n" +
            "            \"1. open\": \"68.2700\",\n" +
            "            \"2. high\": \"68.7800\",\n" +
            "            \"3. low\": \"68.1200\",\n" +
            "            \"4. close\": \"68.5700\",\n" +
            "            \"5. volume\": \"20776555\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.daily("DUMMY", OutputSize.COMPACT);
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Daily Prices (open, high, low, close) and Volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-24 12:47:32")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("Compact")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 24, 0, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(83.01)));
    assertThat(stock.getHigh(), is(equalTo(83.37)));
    assertThat(stock.getLow(), is(equalTo(82.78)));
    assertThat(stock.getClose(), is(equalTo(83.32)));
    assertThat(stock.getVolume(), is(equalTo(4612054L)));
  }
}
