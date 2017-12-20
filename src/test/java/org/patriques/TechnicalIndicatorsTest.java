package org.patriques;

import org.junit.Test;
import org.patriques.input.technicalindicators.*;
import org.patriques.output.technicalindicators.*;
import org.patriques.output.technicalindicators.data.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TechnicalIndicatorsTest {

  private TechnicalIndicators technicalIndicators;

  @Test
  public void dema() {
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

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void ema() {
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

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void kama() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Kaufman Adaptive Moving Average (KAMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: KAMA\": {\n" +
            "        \"2017-12-14 16:00\": {\n" +
            "            \"KAMA\": \"84.8376\"\n" +
            "        },\n" +
            "        \"2017-12-14 15:45\": {\n" +
            "            \"KAMA\": \"84.8378\"\n" +
            "        },\n" +
            "        \"2017-11-17 11:45\": {\n" +
            "            \"KAMA\": \"82.6005\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    KAMA resp = technicalIndicators.kama("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Kaufman Adaptive Moving Average (KAMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern Time")));

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 14, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.8376d)));
  }

  @Test
  public void macd() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Moving Average Convergence/Divergence (MACD)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: Fast Period\": 10,\n" +
            "        \"5.2: Slow Period\": 26,\n" +
            "        \"5.3: Signal Period\": 9,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MACD\": {\n" +
            "        \"2017-12-14 16:00\": {\n" +
            "            \"MACD_Hist\": \"0.0197\",\n" +
            "            \"MACD_Signal\": \"-0.1967\",\n" +
            "            \"MACD\": \"-0.1770\"\n" +
            "        },\n" +
            "        \"2017-12-14 15:45\": {\n" +
            "            \"MACD_Hist\": \"0.0225\",\n" +
            "            \"MACD_Signal\": \"-0.2016\",\n" +
            "            \"MACD\": \"-0.1791\"\n" +
            "        },\n" +
            "        \"2017-12-04 11:00\": {\n" +
            "            \"MACD_Hist\": \"-0.2515\",\n" +
            "            \"MACD_Signal\": \"-0.1084\",\n" +
            "            \"MACD\": \"-0.3599\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MACD resp = technicalIndicators.macd("DUMMY",
            Interval.FIFTEEN_MIN,
            TimePeriod.of(10),
            SeriesType.CLOSE,
            FastPeriod.of(10),
            SlowPeriod.of(26),
            null);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Moving Average Convergence/Divergence (MACD)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5.1: Fast Period"), is(equalTo("10")));
    assertThat(metaData.get("5.2: Slow Period"), is(equalTo("26")));
    assertThat(metaData.get("5.3: Signal Period"), is(equalTo("9")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern")));

    List<MACDData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(3)));

    MACDData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 14, 16, 0))));
    assertThat(data.getHist(), is(equalTo(0.0197d)));
    assertThat(data.getSignal(), is(equalTo(-0.1967d)));
    assertThat(data.getMacd(), is(equalTo(-0.1770d)));
  }

  @Test
  public void macdext() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"MACD with Controllable MA Type (MACDEXT)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: Fast Period\": 12,\n" +
            "        \"5.2: Slow Period\": 26,\n" +
            "        \"5.3: Signal Period\": 9,\n" +
            "        \"5.4: Fast MA Type\": 1,\n" +
            "        \"5.5: Slow MA Type\": 0,\n" +
            "        \"5.6: Signal MA Type\": 0,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MACDEXT\": {\n" +
            "        \"2017-12-04 11:00\": {\n" +
            "            \"MACD\": \"-0.3448\",\n" +
            "            \"MACD_Hist\": \"-0.2407\",\n" +
            "            \"MACD_Signal\": \"-0.1041\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MACDEXT resp = technicalIndicators.macdext("DUMMY",
            Interval.FIFTEEN_MIN,
            TimePeriod.of(10),
            null,
            null,
            null,
            null,
            null,
            null);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("MACD with Controllable MA Type (MACDEXT)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5.1: Fast Period"), is(equalTo("12")));
    assertThat(metaData.get("5.2: Slow Period"), is(equalTo("26")));
    assertThat(metaData.get("5.3: Signal Period"), is(equalTo("9")));
    assertThat(metaData.get("5.4: Fast MA Type"), is(equalTo("1")));
    assertThat(metaData.get("5.5: Slow MA Type"), is(equalTo("0")));
    assertThat(metaData.get("5.6: Signal MA Type"), is(equalTo("0")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern Time")));

    List<MACDData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    MACDData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 4, 11, 0))));
    assertThat(data.getMacd(), is(equalTo(-0.3448d)));
    assertThat(data.getHist(), is(equalTo(-0.2407d)));
    assertThat(data.getSignal(), is(equalTo(-0.1041d)));

  }

  @Test
  public void mama() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"MESA Adaptive Moving Average (MAMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: Fast Limit\": 0.02,\n" +
            "        \"5.2: Slow Limit\": 0.01,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MAMA\": {\n" +
            "        \"2017-12-14 16:00\": {\n" +
            "            \"MAMA\": \"81.2088\",\n" +
            "            \"FAMA\": \"53.0511\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MAMA resp = technicalIndicators.mama("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), null, null, null);
    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("MESA Adaptive Moving Average (MAMA)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5.1: Fast Limit"), is(equalTo("0.02")));
    assertThat(metaData.get("5.2: Slow Limit"), is(equalTo("0.01")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern Time")));

    List<MAMAData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    MAMAData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 14, 16, 0))));
    assertThat(data.getMama(), is(equalTo(81.2088d)));
    assertThat(data.getFama(), is(equalTo(53.0511d)));
  }

  @Test
  public void rsi() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Relative Strength Index (RSI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: RSI\": {\n" +
            "        \"2017-12-01 12:00\": {\n" +
            "            \"RSI\": \"57.2543\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    RSI resp = technicalIndicators.rsi("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);
    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Relative Strength Index (RSI)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5: Time Period"), is(equalTo("10")));
    assertThat(metaData.get("6: Series Type"), is(equalTo("close")));
    assertThat(metaData.get("7: Time Zone"), is(equalTo("US/Eastern Time")));

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 12, 0))));
    assertThat(data.getData(), is(equalTo(57.2543d)));
  }

  @Test
  public void sma() {
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

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void stoch() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Stochastic (STOCH)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: FastK Period\": 5,\n" +
            "        \"5.2: SlowK Period\": 3,\n" +
            "        \"5.3: SlowK MA Type\": 1,\n" +
            "        \"5.4: SlowD Period\": 3,\n" +
            "        \"5.5: SlowD MA Type\": 1,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: STOCH\": {\n" +
            "        \"2017-12-01 11:30\": {\n" +
            "            \"SlowK\": \"27.9031\",\n" +
            "            \"SlowD\": \"41.7739\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    STOCH resp = technicalIndicators.stoch("DUMMY", Interval.FIFTEEN_MIN, null, null, null, null, null);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Stochastic (STOCH)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5.1: FastK Period"), is(equalTo("5")));
    assertThat(metaData.get("5.2: SlowK Period"), is(equalTo("3")));
    assertThat(metaData.get("5.3: SlowK MA Type"), is(equalTo("1")));
    assertThat(metaData.get("5.4: SlowD Period"), is(equalTo("3")));
    assertThat(metaData.get("5.5: SlowD MA Type"), is(equalTo("1")));
    assertThat(metaData.get("6: Time Zone"), is(equalTo("US/Eastern Time")));

    List<STOCHDataSlow> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    STOCHDataSlow data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 11, 30))));
    assertThat(data.getSlowK(), is(equalTo(27.9031d)));
    assertThat(data.getSlowD(), is(equalTo(41.7739d)));
  }

  @Test
  public void stochf() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Stochastic Fast (STOCHF)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: FastK Period\": 6,\n" +
            "        \"5.2: FastD Period\": 3,\n" +
            "        \"5.3: FastD MA Type\": 1,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: STOCHF\": {\n" +
            "        \"2017-12-01 11:15\": {\n" +
            "            \"FastK\": \"33.3333\",\n" +
            "            \"FastD\": \"51.8195\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    STOCHF resp = technicalIndicators.stochf("DUMMY", Interval.FIFTEEN_MIN, null, null, null);

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1: Symbol"), is(equalTo("DUMMY")));
    assertThat(metaData.get("2: Indicator"), is(equalTo("Stochastic Fast (STOCHF)")));
    assertThat(metaData.get("3: Last Refreshed"), is(equalTo("2017-12-14 16:00:00")));
    assertThat(metaData.get("4: Interval"), is(equalTo("15min")));
    assertThat(metaData.get("5.1: FastK Period"), is(equalTo("6")));
    assertThat(metaData.get("5.2: FastD Period"), is(equalTo("3")));
    assertThat(metaData.get("5.3: FastD MA Type"), is(equalTo("1")));
    assertThat(metaData.get("6: Time Zone"), is(equalTo("US/Eastern Time")));

    List<STOCHDataFast> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    STOCHDataFast data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 11, 15))));
    assertThat(data.getFastK(), is(equalTo(33.3333d)));
    assertThat(data.getFastD(), is(equalTo(51.8195d)));
  }

  @Test
  public void stochrsi() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Stochastic Relative Strength Index (STOCHRSI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6.1: FastK Period\": 6,\n" +
            "        \"6.2: FastD Period\": 3,\n" +
            "        \"6.3: FastD MA Type\": 1,\n" +
            "        \"7: Series Type\": \"close\",\n" +
            "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: STOCHRSI\": {\n" +
            "        \"2017-12-01 13:45\": {\n" +
            "            \"FastK\": \"75.1311\",\n" +
            "            \"FastD\": \"81.4649\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    STOCHRSI resp = technicalIndicators.stochrsi("DUMMY",
            Interval.FIFTEEN_MIN,
            TimePeriod.of(10),
            SeriesType.CLOSE,
            null,
            null,
            null);

    List<STOCHDataFast> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    STOCHDataFast data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 13, 45))));
    assertThat(data.getFastK(), is(equalTo(75.1311d)));
    assertThat(data.getFastD(), is(equalTo(81.4649d)));
  }

  @Test
  public void t3() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Triple Exponential Moving Average (T3)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Volume Factor (vFactor)\": 0.7,\n" +
            "        \"7: Series Type\": \"close\",\n" +
            "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: T3\": {\n" +
            "        \"2017-12-05 09:30\": {\n" +
            "            \"T3\": \"81.2514\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    T3 resp = technicalIndicators.t3("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 5, 9, 30))));
    assertThat(data.getData(), is(equalTo(81.2514d)));
  }

  @Test
  public void tema() {
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

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void trima() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Triangular Exponential Moving Average (TRIMA)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: TRIMA\": {\n" +
            "        \"2017-12-01 11:45\": {\n" +
            "            \"TRIMA\": \"84.2648\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    TRIMA resp = technicalIndicators.trima("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 11, 45))));
    assertThat(data.getData(), is(equalTo(84.2648d)));
  }

  @Test
  public void willr() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Williams' %R (WILLR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: WILLR\": {\n" +
            "        \"2017-12-01 11:45\": {\n" +
            "            \"WILLR\": \"-48.2075\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    WILLR resp = technicalIndicators.willr("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = resp.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 11, 45))));
    assertThat(data.getData(), is(equalTo(-48.2075d)));
  }

  @Test
  public void wma() {
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

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 16, 0))));
    assertThat(data.getData(), is(equalTo(84.0203d)));
  }

  @Test
  public void adx() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Average Directional Movement Index (ADX)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ADX\": {\n" +
            "        \"2017-12-01 14:15\": {\n" +
            "            \"ADX\": \"13.6308\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ADX response = technicalIndicators.adx("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 15))));
    assertThat(data.getData(), is(equalTo(13.6308d)));
  }

  @Test
  public void adxr() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Average Directional Movement Index Rating (ADXR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ADXR\": {\n" +
            "        \"2017-12-01 14:15\": {\n" +
            "            \"ADXR\": \"13.6308\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ADXR response = technicalIndicators.adxr("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 15))));
    assertThat(data.getData(), is(equalTo(13.6308d)));
  }

  @Test
  public void apo() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Absolute Price Oscillator (APO)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: Fast Period\": 10,\n" +
            "        \"5.2: Slow Period\": 26,\n" +
            "        \"5.3: MA Type\": 1,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: APO\": {\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"APO\": \"0.0216\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    APO response = technicalIndicators.apo("DUMMY", Interval.FIFTEEN_MIN, SeriesType.CLOSE, null, null, null);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 15, 45))));
    assertThat(data.getData(), is(equalTo(0.0216d)));
  }

  @Test
  public void ppo() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Percentage Price Oscillator (PPO)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5.1: Fast Period\": 10,\n" +
            "        \"5.2: Slow Period\": 26,\n" +
            "        \"5.3: MA Type\": 1,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: PPO\": {\n" +
            "        \"2017-12-01 15:45\": {\n" +
            "            \"PPO\": \"0.0257\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    PPO response = technicalIndicators.ppo("DUMMY", Interval.FIFTEEN_MIN, SeriesType.CLOSE, null, null, null);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 15, 45))));
    assertThat(data.getData(), is(equalTo(0.0257d)));
  }

  @Test
  public void mom() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Momentum (MOM)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MOM\": {\n" +
            "        \"2017-12-01 12:00\": {\n" +
            "            \"MOM\": \"0.4000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MOM response = technicalIndicators.mom("DUMMY", Interval.FIFTEEN_MIN, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 12, 0))));
    assertThat(data.getData(), is(equalTo(0.4000d)));
  }

  @Test
  public void bop() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Balance Of Power (BOP)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"15min\",\n" +
            "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: BOP\": {\n" +
            "        \"2017-12-01 09:30\": {\n" +
            "            \"BOP\": \"-0.1250\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    BOP response = technicalIndicators.bop("DUMMY", Interval.FIFTEEN_MIN);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 9, 30))));
    assertThat(data.getData(), is(equalTo(-0.1250d)));
  }

  @Test
  public void cci() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Commodity Channel Index (CCI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: CCI\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"CCI\": \"-11.0151\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    CCI response = technicalIndicators.cci("DUMMY", Interval.DAILY, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(-11.0151d)));
  }

  @Test
  public void cmo() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Chande Momentum Oscillator (CMO)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: CMO\": {\n" +
            "        \"2000-03-24\": {\n" +
            "            \"CMO\": \"-0.9150\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    CMO response = technicalIndicators.cmo("DUMMY", Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 3, 24, 0, 0))));
    assertThat(data.getData(), is(equalTo(-0.9150d)));
  }

  @Test
  public void roc() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Rate of change : ((price/prevPrice)-1)*100\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ROC\": {\n" +
            "        \"2000-03-24\": {\n" +
            "            \"ROC\": \"-0.4989\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ROC response = technicalIndicators.roc("DUMMY", Interval.WEEKLY, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 3, 24, 0, 0))));
    assertThat(data.getData(), is(equalTo(-0.4989d)));
  }

  @Test
  public void rocr() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Rate of change ratio: (price/prevPrice)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ROCR\": {\n" +
            "        \"2000-01-18\": {\n" +
            "            \"ROCR\": \"0.9893\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ROCR response = technicalIndicators.rocr("DUMMY", Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 18, 0, 0))));
    assertThat(data.getData(), is(equalTo(0.9893d)));
  }

  @Test
  public void aroon() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Aroon (AROON)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 14,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: AROON\": {\n" +
            "        \"2000-01-24\": {\n" +
            "            \"Aroon Up\": \"0.0000\",\n" +
            "            \"Aroon Down\": \"100.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    AROON response = technicalIndicators.aroon("DUMMY", Interval.DAILY, TimePeriod.of(14));

    List<AROONData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    AROONData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 24, 0, 0))));
    assertThat(data.getAroonUp(), is(equalTo(0.0000d)));
    assertThat(data.getAroonDown(), is(equalTo(100.0000d)));
  }

  @Test
  public void aroonosc() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Aroon Oscillator (AROONOSC)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: AROONOSC\": {\n" +
            "        \"2017-12-01 14:30\": {\n" +
            "            \"AROONOSC\": \"-30.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    AROONOSC response = technicalIndicators.aroonosc("DUMMY", Interval.THIRTY_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 30))));
    assertThat(data.getData(), is(equalTo(-30.0000d)));
  }

  @Test
  public void mfi() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Money Flow Index (MFI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MFI\": {\n" +
            "        \"2000-03-24\": {\n" +
            "            \"MFI\": \"47.5642\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MFI response = technicalIndicators.mfi("DUMMY", Interval.WEEKLY, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 3, 24, 0, 0))));
    assertThat(data.getData(), is(equalTo(47.5642d)));
  }

  @Test
  public void trix() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"1-day Rate-Of-Change (ROC) of a Triple Smooth EMA (TRIX)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: TRIX\": {\n" +
            "        \"2000-02-11\": {\n" +
            "            \"TRIX\": \"-0.1527\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    TRIX response = technicalIndicators.trix("DUMMY", Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 2, 11, 0, 0))));
    assertThat(data.getData(), is(equalTo(-0.1527d)));
  }

  @Test
  public void ultosc() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Ultimate Oscillator (ULTOSC)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5.1: Time Period 1\": 8,\n" +
            "        \"5.2: Time Period 2\": 14,\n" +
            "        \"5.3: Time Period 3\": 28,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ULTOSC\": {\n" +
            "        \"2017-12-05 09:30\": {\n" +
            "            \"ULTOSC\": \"44.5000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ULTOSC response = technicalIndicators.ultosc("DUMMY", Interval.THIRTY_MIN, null, null, null);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 5, 9, 30))));
    assertThat(data.getData(), is(equalTo(44.5000d)));
  }

  @Test
  public void dx() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Directional Movement Index (DX)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: DX\": {\n" +
            "        \"2017-12-01 14:30\": {\n" +
            "            \"DX\": \"1.5590\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    DX response = technicalIndicators.dx("DUMMY", Interval.THIRTY_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 30))));
    assertThat(data.getData(), is(equalTo(1.5590d)));
  }

  @Test
  public void minus_di() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Minus Directional Indicator (MINUS_DI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MINUS_DI\": {\n" +
            "        \"2000-03-24\": {\n" +
            "            \"MINUS_DI\": \"17.5170\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MINUS_DI response = technicalIndicators.minus_di("DUMMY", Interval.WEEKLY, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 3, 24, 0, 0))));
    assertThat(data.getData(), is(equalTo(17.5170d)));
  }

  @Test
  public void plus_di() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Plus Directional Indicator (PLUS_DI)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: PLUS_DI\": {\n" +
            "        \"2017-12-01 14:30\": {\n" +
            "            \"PLUS_DI\": \"18.6114\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    PLUS_DI response = technicalIndicators.plus_di("DUMMY", Interval.THIRTY_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 30))));
    assertThat(data.getData(), is(equalTo(18.6114d)));
  }

  @Test
  public void minus_dm() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Minus Directional Movement (MINUS_DM)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MINUS_DM\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"MINUS_DM\": \"14.8100\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MINUS_DM response = technicalIndicators.minus_dm("DUMMY", Interval.DAILY, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(14.8100d)));
  }

  @Test
  public void plus_dm() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Plus Directional Movement (PLUS_DM)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: PLUS_DM\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"PLUS_DM\": \"6.7600\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    PLUS_DM response = technicalIndicators.plus_dm("DUMMY", Interval.DAILY, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(6.7600d)));
  }

  @Test
  public void bbands() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Bollinger Bands (BBANDS)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 5,\n" +
            "        \"6.1: Deviation multiplier for upper band\": 3,\n" +
            "        \"6.2: Deviation multiplier for lower band\": 3,\n" +
            "        \"6.3: MA Type\": 0,\n" +
            "        \"7: Series Type\": \"close\",\n" +
            "        \"8: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: BBANDS\": {\n" +
            "        \"2000-02-11\": {\n" +
            "            \"Real Lower Band\": \"89.2034\",\n" +
            "            \"Real Upper Band\": \"119.0966\",\n" +
            "            \"Real Middle Band\": \"104.1500\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    BBANDS response = technicalIndicators.bbands("DUMMY",
            Interval.WEEKLY,
            TimePeriod.of(5),
            SeriesType.CLOSE,
            null,
            null,
            null);

    List<BBANDSData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    BBANDSData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 2, 11, 0, 0))));
    assertThat(data.getLowerBand(), is(equalTo(89.2034d)));
    assertThat(data.getUpperBand(), is(equalTo(119.0966d)));
    assertThat(data.getMidBand(), is(equalTo(104.1500d)));
  }

  @Test
  public void midpoint() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"MidPoint over period (MIDPOINT)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Series Type\": \"close\",\n" +
            "        \"7: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MIDPOINT\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"MIDPOINT\": \"111.1850\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MIDPOINT response = technicalIndicators.midpoint("DUMMY", Interval.DAILY, TimePeriod.of(10), SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(111.1850d)));
  }

  @Test
  public void midprice() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Midpoint Price over period (MIDPRICE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Time Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: MIDPRICE\": {\n" +
            "        \"2017-12-01 14:00\": {\n" +
            "            \"MIDPRICE\": \"84.0150\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    MIDPRICE response = technicalIndicators.midprice("DUMMY", Interval.THIRTY_MIN, TimePeriod.of(10));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 0))));
    assertThat(data.getData(), is(equalTo(84.0150d)));
  }

  @Test
  public void sar() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Parabolic SAR (SAR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5.1: Acceleration\": 0.05,\n" +
            "        \"5.2: Maximum\": 0.25,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: SAR\": {\n" +
            "        \"2000-01-21\": {\n" +
            "            \"SAR\": \"101.5000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    SAR response = technicalIndicators.sar("DUMMY", Interval.WEEKLY, null, null);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 21, 0, 0))));
    assertThat(data.getData(), is(equalTo(101.5000d)));
  }

  @Test
  public void trange() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"True Range (TRANGE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: TRANGE\": {\n" +
            "        \"2000-01-04\": {\n" +
            "            \"TRANGE\": \"4.8700\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    TRANGE response = technicalIndicators.trange("DUMMY", Interval.DAILY);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 4, 0, 0))));
    assertThat(data.getData(), is(equalTo(4.8700d)));
  }

  @Test
  public void atr() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Average True Range (ATR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Time Period\": 14,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ATR\": {\n" +
            "        \"2017-12-04 09:30\": {\n" +
            "            \"ATR\": \"0.4977\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ATR response = technicalIndicators.atr("DUMMY", Interval.THIRTY_MIN, TimePeriod.of(14));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 4, 9, 30))));
    assertThat(data.getData(), is(equalTo(0.4977d)));
  }

  @Test
  public void natr() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Normalized Average True Range (NATR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Period\": 14,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: NATR\": {\n" +
            "        \"2000-04-20\": {\n" +
            "            \"NATR\": \"14.8359\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    NATR response = technicalIndicators.natr("DUMMY", Interval.WEEKLY, TimePeriod.of(14));

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 4, 20, 0, 0))));
    assertThat(data.getData(), is(equalTo(14.8359d)));
  }

  @Test
  public void ad() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Chaikin A/D Line\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: Chaikin A/D\": {\n" +
            "        \"2000-01-03\": {\n" +
            "            \"Chaikin A/D\": \"10050679.7583\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    AD response = technicalIndicators.ad("DUMMY", Interval.DAILY);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 3, 0, 0))));
    assertThat(data.getData(), is(equalTo(10050679.7583d)));
  }

  @Test
  public void adosc() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Chaikin A/D Oscillator (ADOSC)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5.1: FastK Period\": 5,\n" +
            "        \"5.2: SlowK Period\": 10,\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: ADOSC\": {\n" +
            "        \"2017-12-01 14:00\": {\n" +
            "            \"ADOSC\": \"-126632.7402\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    ADOSC response = technicalIndicators.adosc("DUMMY", Interval.THIRTY_MIN, null, null);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 1, 14, 0))));
    assertThat(data.getData(), is(equalTo(-126632.7402d)));
  }

  @Test
  public void obv() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"On Balance Volume (OBV)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: OBV\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"OBV\": \"157400000.0000\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    OBV response = technicalIndicators.obv("DUMMY", Interval.WEEKLY);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(157400000.0000d)));
  }

  @Test
  public void ht_trendline() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - Instantaneous Trendline (HT_TRENDLINE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_TRENDLINE\": {\n" +
            "        \"2000-04-03\": {\n" +
            "            \"HT_TRENDLINE\": \"99.7075\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_TRENDLINE response = technicalIndicators.ht_trendline("DUMMY", Interval.DAILY, SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 4, 3, 0, 0))));
    assertThat(data.getData(), is(equalTo(99.7075d)));
  }

  @Test
  public void ht_sine() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - SineWave (HT_SINE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_SINE\": {\n" +
            "        \"2017-12-08 09:30\": {\n" +
            "            \"LEAD SINE\": \"-0.8497\",\n" +
            "            \"SINE\": \"-0.9737\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_SINE response = technicalIndicators.ht_sine("DUMMY", Interval.THIRTY_MIN, SeriesType.CLOSE);

    List<HT_SINEData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    HT_SINEData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 8, 9, 30))));
    assertThat(data.getLeadSine(), is(equalTo(-0.8497d)));
    assertThat(data.getSine(), is(equalTo(-0.9737d)));
  }

  @Test
  public void ht_trendmode() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - Trend vs Cycle Mode (HT_TRENDMODE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_TRENDMODE\": {\n" +
            "        \"2000-01-14\": {\n" +
            "            \"TRENDMODE\": \"0\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_TRENDMODE response = technicalIndicators.ht_trendmode("DUMMY", Interval.WEEKLY, SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 1, 14, 0, 0))));
    assertThat(data.getData(), is(equalTo(0d)));
  }

  @Test
  public void ht_dcperiod() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - Dominant Cycle Period (HT_DCPERIOD)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"daily\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_DCPERIOD\": {\n" +
            "        \"2000-02-17\": {\n" +
            "            \"DCPERIOD\": \"15.6467\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_DCPERIOD response = technicalIndicators.ht_dcperiod("DUMMY", Interval.DAILY, SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 2, 17, 0, 0))));
    assertThat(data.getData(), is(equalTo(15.6467d)));
  }

  @Test
  public void ht_dcphase() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - Dominant Cycle Phase (HT_DCPHASE)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14 16:00:00\",\n" +
            "        \"4: Interval\": \"30min\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_DCPHASE\": {\n" +
            "        \"2017-12-08 09:30\": {\n" +
            "            \"HT_DCPHASE\": \"256.8257\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_DCPHASE response = technicalIndicators.ht_dcphase("DUMMY", Interval.THIRTY_MIN, SeriesType.CLOSE);

    List<IndicatorData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    IndicatorData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2017, 12, 8, 9, 30))));
    assertThat(data.getData(), is(equalTo(256.8257d)));
  }

  @Test
  public void ht_phasor() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1: Symbol\": \"DUMMY\",\n" +
            "        \"2: Indicator\": \"Hilbert Transform - Phasor Components (HT_PHASOR)\",\n" +
            "        \"3: Last Refreshed\": \"2017-12-14\",\n" +
            "        \"4: Interval\": \"weekly\",\n" +
            "        \"5: Series Type\": \"close\",\n" +
            "        \"6: Time Zone\": \"US/Eastern Time\"\n" +
            "    },\n" +
            "    \"Technical Analysis: HT_PHASOR\": {\n" +
            "        \"2000-08-25\": {\n" +
            "            \"PHASE\": \"-2.5430\",\n" +
            "            \"QUADRATURE\": \"-10.8250\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    technicalIndicators = new TechnicalIndicators(apiParameters -> json);
    HT_PHASOR response = technicalIndicators.ht_phasor("DUMMY", Interval.WEEKLY, SeriesType.CLOSE);

    List<HT_PHASORData> indicatorData = response.getData();
    assertThat(indicatorData.size(), is(equalTo(1)));

    HT_PHASORData data = indicatorData.get(0);
    assertThat(data.getDateTime(), is(equalTo(LocalDateTime.of(2000, 8, 25, 0, 0))));
    assertThat(data.getPhase(), is(equalTo(-2.5430d)));
    assertThat(data.getQuadrature(), is(equalTo(-10.8250d)));
  }
}
