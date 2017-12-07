package co.alphavantage;

import co.alphavantage.input.technicalindicator.Interval;
import co.alphavantage.input.technicalindicator.SeriesType;
import co.alphavantage.input.technicalindicator.TimePeriod;
import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.technicalindicators.EMA;
import co.alphavantage.output.technicalindicators.MACD;
import co.alphavantage.output.technicalindicators.SMA;
import co.alphavantage.output.technicalindicators.data.EMAData;
import co.alphavantage.output.technicalindicators.data.MACDData;
import co.alphavantage.output.technicalindicators.data.SMAData;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TechnicalIndicatorsTest {
  private TechnicalIndicators technicalIndicators;

  @Test
  public void sma() throws AlphaVantageException {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Simple Moving Average (SMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: SMA\": {\n" +
            "        \"2017-12-01 16:00\": {\n" +
            "            \"SMA\": \"84.0203\"\n" +
            "        },\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"SMA\": \"83.9763\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"SMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    SMA resp = technicalIndicators.sma("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Simple Moving Average (SMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<SMAData> smaData = resp.getData();
    assertThat(smaData.size(), is(equalTo(3)));

    SMAData sma = smaData.get(0);
    assertThat(sma.getDateTime(), is(equalTo(new DateTime(2017, 12, 1, 16, 0))));
    assertThat(sma.getSma(), is(equalTo(84.0203d)));
  }

  @Test
  public void ema() throws AlphaVantageException {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Exponential Moving Average (EMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: EMA\": {\n" +
            "        \"2017-12-01 16:00\": {\n" +
            "            \"EMA\": \"84.0203\"\n" +
            "        },\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"EMA\": \"83.9763\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"EMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    EMA resp = technicalIndicators.ema("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Exponential Moving Average (EMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<EMAData> emaData = resp.getData();
    assertThat(emaData.size(), is(equalTo(3)));

    EMAData ema = emaData.get(0);
    assertThat(ema.getDateTime(), is(equalTo(new DateTime(2017, 12, 1, 16, 0))));
    assertThat(ema.getEma(), is(equalTo(84.0203d)));
  }
  
  @Test
  public void macd() throws AlphaVantageException {
	  String json = "" +
	            "{\n" +
	            "    \"Meta Data\": {\n" +
	            "        \"1: Symbol\": \"DUMMY\",\n" +
	            "        \"2: Indicator\": \"Moving Average Convergence/Divergence (MACD)\",\n" +
	            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
	            "        \"4: Interval\": \"15min\",\n" +
	            "        \"5: Time Period\": 10,\n" +
	            "        \"6: Series Type\": \"close\",\n" +
	            "        \"7: Time Zone\": \"US/Eastern\"\n" +
	            "    },\n" +
	            "    \"Technical Analysis: MACD\": {\n" +
	            "        \"2017-12-01 16:00\": {\n" +
	            "            \"MACD\": \"84.0203\"\n" +
	            "        },\n" +
	            "        \"2017-12-01 15:45\": {\n" +
	            "            \"MACD\": \"83.9763\"\n" +
	            "        },\n" +
	            "        \"2017-11-17 11:45\": {\n" +
	            "            \"MACD\": \"82.6005\"\n" +
	            "        }\n" +
	            "    }\n" +
	            "}";
	  technicalIndicators = new TechnicalIndicators(apiParameters -> json);
	  MACD response = technicalIndicators.macd("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);
	  
	  Map<String, String> metaData = response.getMetaData();
	  assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
	  assertThat(metaData.get("2: Indicator"), is(equalTo("Moving Average Convergence/Divergence (MACD)")));
	  assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
	  assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
	  assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
	  assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
	  assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));
	  
	  List<MACDData> macdData = response.getData();
	  assertThat(macdData.size(), is(equalTo(3)));

	  MACDData macd = macdData.get(0);
	  assertThat(macd.getDateTime(), is(equalTo(new DateTime(2017, 12, 1, 16, 0))));
	  assertThat(macd.getMacd(), is(equalTo(84.0203d)));
  }

}
