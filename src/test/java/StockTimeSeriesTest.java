import common.Try;
import parameters.Interval;
import parameters.OutputSize;
import response.ResponseData;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StockTimeSeriesTest {
  private static Settings DUMMY_SETTINGS = new Settings("key");
  private StockTimeSeries stockTimeSeries;

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
    stockTimeSeries = new StockTimeSeries(DUMMY_SETTINGS, (params, timeout) -> json);
    Try<ResponseData> resp = stockTimeSeries.intraDay("DUMMY", Interval.ONE_MIN, OutputSize.COMPACT);
    assertThat(resp.isSuccess(), is(equalTo(true)));

  }


}
