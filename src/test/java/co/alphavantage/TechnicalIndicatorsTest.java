package co.alphavantage;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.input.technicalindicators.SeriesType;
import co.alphavantage.input.technicalindicators.TimePeriod;
import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.technicalindicators.*;
import co.alphavantage.output.technicalindicators.data.IndicatorData;
import org.junit.Test;

import java.time.LocalDateTime;
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

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData sma = indicatorData.get(0);
    assertThat(sma.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(sma.getData(), is(equalTo(84.0203d)));
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

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData ema = indicatorData.get(0);
    assertThat(ema.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(ema.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void dema() throws AlphaVantageException {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Double Exponential Moving Average (DEMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: DEMA\": {\n" +
            "        \"2017-12-01 16:00\": {\n" +
            "            \"DEMA\": \"84.0203\"\n" +
            "        },\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"DEMA\": \"83.9763\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"DEMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    DEMA response = technicalIndicators.dema("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = response.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Double Exponential Moving Average (DEMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData macd = indicatorData.get(0);
    assertThat(macd.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(macd.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void tema() throws AlphaVantageException {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Triple Exponential Moving Average (TEMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: TEMA\": {\n" +
            "        \"2017-12-01 16:00\": {\n" +
            "            \"TEMA\": \"84.0203\"\n" +
            "        },\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"TEMA\": \"83.9763\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"TEMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    TEMA response = technicalIndicators.tema("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = response.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Triple Exponential Moving Average (TEMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData macd = indicatorData.get(0);
    assertThat(macd.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(macd.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void wma() throws AlphaVantageException {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Weighted Moving Average (WMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-01 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: WMA\": {\n" +
            "        \"2017-12-01 16:00\": {\n" +
            "            \"WMA\": \"84.0203\"\n" +
            "        },\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"WMA\": \"83.9763\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"WMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    WMA response = technicalIndicators.wma("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = response.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Weighted Moving Average (WMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-01 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData macd = indicatorData.get(0);
    assertThat(macd.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(macd.getData(), is(equalTo(84.0203d)));
  }



}
