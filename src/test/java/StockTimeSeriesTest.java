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
            "        \"1. Information\": \"Intraday (1min) prices and volumes\",\n" +
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
    assertThat(metaData.getInfo(), is(equalTo("Intraday (1min) prices and volumes")));
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

  @Test
  public void dailyAdjusted() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Daily Time Series with Splits and Dividend Events\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24 12:47:32\",\n" +
            "        \"4. Output Size\": \"Compact\",\n" +
            "        \"5. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (Daily)\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"83.0100\",\n" +
            "            \"2. high\": \"83.4300\",\n" +
            "            \"3. low\": \"82.7800\",\n" +
            "            \"4. close\": \"83.2600\",\n" +
            "            \"5. adjusted close\": \"83.2600\",\n" +
            "            \"6. volume\": \"7425503\",\n" +
            "            \"7. dividend amount\": \"0.0000\",\n" +
            "            \"8. split coefficient\": \"1.0000\"\n" +
            "        },\n" +
            "        \"2017-11-22\": {\n" +
            "            \"1. open\": \"83.8300\",\n" +
            "            \"2. high\": \"83.9000\",\n" +
            "            \"3. low\": \"83.0400\",\n" +
            "            \"4. close\": \"83.1100\",\n" +
            "            \"5. adjusted close\": \"83.1100\",\n" +
            "            \"6. volume\": \"20213704\",\n" +
            "            \"7. dividend amount\": \"0.0000\",\n" +
            "            \"8. split coefficient\": \"1.0000\"\n" +
            "        }," +
            "       \"2017-07-06\": {\n" +
            "            \"1. open\": \"68.2700\",\n" +
            "            \"2. high\": \"68.7800\",\n" +
            "            \"3. low\": \"68.1200\",\n" +
            "            \"4. close\": \"68.5700\",\n" +
            "            \"5. adjusted close\": \"67.8632\",\n" +
            "            \"6. volume\": \"20776555\",\n" +
            "            \"7. dividend amount\": \"0.0000\",\n" +
            "            \"8. split coefficient\": \"1.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.dailyAdjusted("DUMMY", OutputSize.COMPACT);
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Daily Time Series with Splits and Dividend Events")));
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
    assertThat(stock.getHigh(), is(equalTo(83.43)));
    assertThat(stock.getLow(), is(equalTo(82.78)));
    assertThat(stock.getClose(), is(equalTo(83.26)));
    assertThat(stock.getAdjustedClose(), is(equalTo(83.26)));
    assertThat(stock.getVolume(), is(equalTo(7425503L)));
    assertThat(stock.getDividendAmount(), is(equalTo(0.0)));
    assertThat(stock.getSplitCoefficient(), is(equalTo(1.0)));
  }

  @Test
  public void weekly() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Weekly Prices (open, high, low, close) and Volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24\",\n" +
            "        \"4. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Weekly Time Series\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"82.4000\",\n" +
            "            \"2. high\": \"83.9000\",\n" +
            "            \"3. low\": \"82.2500\",\n" +
            "            \"4. close\": \"83.2600\",\n" +
            "            \"5. volume\": \"64949156\"\n" +
            "        },\n" +
            "        \"2017-11-17\": {\n" +
            "            \"1. open\": \"83.6600\",\n" +
            "            \"2. high\": \"84.1000\",\n" +
            "            \"3. low\": \"82.2400\",\n" +
            "            \"4. close\": \"82.4000\",\n" +
            "            \"5. volume\": \"94156894\"\n" +
            "        },\n" +
            "        \"2000-01-14\": {\n" +
            "            \"1. open\": \"113.4400\",\n" +
            "            \"2. high\": \"114.2500\",\n" +
            "            \"3. low\": \"101.5000\",\n" +
            "            \"4. close\": \"112.2500\",\n" +
            "            \"5. volume\": \"157400000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.weekly("DUMMY");
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Weekly Prices (open, high, low, close) and Volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-24")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 24, 0, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(82.40)));
    assertThat(stock.getHigh(), is(equalTo(83.90)));
    assertThat(stock.getLow(), is(equalTo(82.25)));
    assertThat(stock.getClose(), is(equalTo(83.26)));
    assertThat(stock.getVolume(), is(equalTo(64949156L)));
  }

  @Test
  public void weeklyAdjusted() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Weekly Adjusted Prices and Volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24\",\n" +
            "        \"4. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Weekly Adjusted Time Series\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"82.4000\",\n" +
            "            \"2. high\": \"83.9000\",\n" +
            "            \"3. low\": \"82.2500\",\n" +
            "            \"4. close\": \"83.2600\",\n" +
            "            \"5. adjusted close\": \"83.2600\",\n" +
            "            \"6. volume\": \"64949156\",\n" +
            "            \"7. dividend amount\": \"0.4200\"\n" +
            "        },\n" +
            "        \"2017-11-17\": {\n" +
            "            \"1. open\": \"83.6600\",\n" +
            "            \"2. high\": \"84.1000\",\n" +
            "            \"3. low\": \"82.2400\",\n" +
            "            \"4. close\": \"82.4000\",\n" +
            "            \"5. adjusted close\": \"82.4000\",\n" +
            "            \"6. volume\": \"94156894\",\n" +
            "            \"7. dividend amount\": \"0.4200\"\n" +
            "        },\n" +
            "        \"2000-01-14\": {\n" +
            "            \"1. open\": \"113.4400\",\n" +
            "            \"2. high\": \"114.2500\",\n" +
            "            \"3. low\": \"101.5000\",\n" +
            "            \"4. close\": \"112.2500\",\n" +
            "            \"5. adjusted close\": \"37.3619\",\n" +
            "            \"6. volume\": \"157400000\",\n" +
            "            \"7. dividend amount\": \"0.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.weeklyAdjusted("DUMMY");
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Weekly Adjusted Prices and Volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-24")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 24, 0, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(82.40)));
    assertThat(stock.getHigh(), is(equalTo(83.90)));
    assertThat(stock.getLow(), is(equalTo(82.25)));
    assertThat(stock.getClose(), is(equalTo(83.26)));
    assertThat(stock.getAdjustedClose(), is(equalTo(83.26)));
    assertThat(stock.getVolume(), is(equalTo(64949156L)));
    assertThat(stock.getDividendAmount(), is(equalTo(0.42)));
  }

  @Test
  public void monthly() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Monthly Prices (open, high, low, close) and Volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24\",\n" +
            "        \"4. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Monthly Time Series\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"83.6800\",\n" +
            "            \"2. high\": \"84.9000\",\n" +
            "            \"3. low\": \"84.9000\",\n" +
            "            \"4. close\": \"84.9000\",\n" +
            "            \"5. volume\": \"317028976\"\n" +
            "        },\n" +
            "        \"2017-10-31\": {\n" +
            "            \"1. open\": \"74.7100\",\n" +
            "            \"2. high\": \"86.2000\",\n" +
            "            \"3. low\": \"73.7100\",\n" +
            "            \"4. close\": \"83.1800\",\n" +
            "            \"5. volume\": \"440510118\"\n" +
            "        },\n" +
            "        \"2000-02-29\": {\n" +
            "            \"1. open\": \"98.5000\",\n" +
            "            \"2. high\": \"110.0000\",\n" +
            "            \"3. low\": \"88.1200\",\n" +
            "            \"4. close\": \"89.3700\",\n" +
            "            \"5. volume\": \"667243800\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.monthly("DUMMY");
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Monthly Prices (open, high, low, close) and Volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-24")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 24, 0, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(83.68)));
    assertThat(stock.getHigh(), is(equalTo(84.90)));
    assertThat(stock.getLow(), is(equalTo(84.90)));
    assertThat(stock.getClose(), is(equalTo(84.90)));
    assertThat(stock.getVolume(), is(equalTo(317028976L)));
  }

  @Test
  public void monthlyAdjusted() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Monthly Adjusted Prices and Volumes\",\n" +
            "        \"2. Symbol\": \"DUMMY\",\n" +
            "        \"3. Last Refreshed\": \"2017-11-24\",\n" +
            "        \"4. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Monthly Adjusted Time Series\": {\n" +
            "        \"2017-11-24\": {\n" +
            "            \"1. open\": \"83.6800\",\n" +
            "            \"2. high\": \"84.9000\",\n" +
            "            \"3. low\": \"82.2400\",\n" +
            "            \"4. close\": \"83.2600\",\n" +
            "            \"5. adjusted close\": \"83.2600\",\n" +
            "            \"6. volume\": \"317028976\",\n" +
            "            \"7. dividend amount\": \"0.4200\"\n" +
            "        },\n" +
            "        \"2017-10-31\": {\n" +
            "            \"1. open\": \"74.7100\",\n" +
            "            \"2. high\": \"86.2000\",\n" +
            "            \"3. low\": \"73.7100\",\n" +
            "            \"4. close\": \"83.1800\",\n" +
            "            \"5. adjusted close\": \"82.7611\",\n" +
            "            \"6. volume\": \"440510118\",\n" +
            "            \"7. dividend amount\": \"0.0000\"\n" +
            "        },\n" +
            "        \"2000-02-29\": {\n" +
            "            \"1. open\": \"98.5000\",\n" +
            "            \"2. high\": \"110.0000\",\n" +
            "            \"3. low\": \"88.1200\",\n" +
            "            \"4. close\": \"89.3700\",\n" +
            "            \"5. adjusted close\": \"29.7464\",\n" +
            "            \"6. volume\": \"667243800\",\n" +
            "            \"7. dividend amount\": \"0.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    stockTimeSeries = new StockTimeSeries((symbol, parameters) -> json);

    Either<ResponseData, Exception> resp = stockTimeSeries.monthlyAdjusted("DUMMY");
    assertThat(resp.isLeft(), is(equalTo(true)));

    MetaData metaData = resp.getLeft().getMetaData();
    assertThat(metaData.getInfo(), is(equalTo("Monthly Adjusted Prices and Volumes")));
    assertThat(metaData.getSymbol(), is(equalTo("DUMMY")));
    assertThat(metaData.getLastRefresh(), is(equalTo("2017-11-24")));
    assertThat(metaData.getInterval().orElse(""), is(equalTo("")));
    assertThat(metaData.getOutputSize().orElse(""), is(equalTo("")));
    assertThat(metaData.getTimeZone(), is(equalTo("US/Eastern")));

    List<StockData> stockData = resp.getLeft().getStockData();
    assertThat(stockData.size(), is(equalTo(3)));

    StockData stock = stockData.get(0);
    assertThat(stock.getDateTime(), is(equalTo(new DateTime(2017, 11, 24, 0, 0, 0))));
    assertThat(stock.getOpen(), is(equalTo(83.68)));
    assertThat(stock.getHigh(), is(equalTo(84.90)));
    assertThat(stock.getLow(), is(equalTo(82.24)));
    assertThat(stock.getClose(), is(equalTo(83.26)));
    assertThat(stock.getVolume(), is(equalTo(317028976L)));
    assertThat(stock.getDividendAmount(), is(equalTo(0.42)));
  }
}
