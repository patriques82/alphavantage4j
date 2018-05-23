package org.patriques;

import org.junit.Test;
import org.patriques.output.quote.BatchStockQuotesResponse;
import org.patriques.output.quote.data.StockQuote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BatchStockQuotesTest {

  @Test
  public void singleStockQuote() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Batch Stock Market Quotes\",\n" +
            "        \"2. Notes\": \"IEX Real-Time Price provided for free by IEX (https://iextrading.com/developer/).\",\n" +
            "        \"3. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Stock Quotes\": [\n" +
            "        {\n" +
            "            \"1. symbol\": \"MSFT\",\n" +
            "            \"2. price\": \"96.3850\",\n" +
            "            \"3. volume\": \"987654321\",\n" +
            "            \"4. timestamp\": \"2018-05-18 15:59:48\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    BatchStockQuotes batchStockQuotes = new BatchStockQuotes(parameters -> json);
    BatchStockQuotesResponse resp = batchStockQuotes.quote("DUMMY");

    Map<String, String> metaData = resp.getMetaData();
    assertThat(metaData.get("1. Information"), is(equalTo("Batch Stock Market Quotes")));
    assertThat(metaData.get("2. Notes"), is(equalTo("IEX Real-Time Price provided for free by IEX (https://iextrading.com/developer/).")));
    assertThat(metaData.get("3. Time Zone"), is(equalTo("US/Eastern")));

    List<StockQuote> stockData = resp.getStockQuotes();
    assertThat(stockData.size(), is(equalTo(1)));

    StockQuote stockQuote = stockData.get(0);
    assertThat(stockQuote.getSymbol(), is(equalTo("MSFT")));
    assertThat(stockQuote.getPrice(), is(equalTo(96.3850)));
    assertThat(stockQuote.getVolume(), is(equalTo(987654321L)));
    assertThat(stockQuote.getTimestamp(), is(equalTo(LocalDateTime.of(2018, 05, 18, 15, 59, 48))));
  }

  @Test
  public void stockNoVolume() {
    String json = "" +
            "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Batch Stock Market Quotes\",\n" +
            "        \"2. Notes\": \"IEX Real-Time Price provided for free by IEX (https://iextrading.com/developer/).\",\n" +
            "        \"3. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Stock Quotes\": [\n" +
            "        {\n" +
            "            \"1. symbol\": \"MSFT\",\n" +
            "            \"2. price\": \"96.3850\",\n" +
            "            \"3. volume\": \"--\",\n" +
            "            \"4. timestamp\": \"2018-05-18 15:59:48\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    BatchStockQuotes batchStockQuotes = new BatchStockQuotes(parameters -> json);
    BatchStockQuotesResponse resp = batchStockQuotes.quote("DUMMY");

    List<StockQuote> stockData = resp.getStockQuotes();
    assertThat(stockData.size(), is(equalTo(1)));

    StockQuote stockQuote = stockData.get(0);
    assertThat(stockQuote.getVolume(), is(equalTo(0L)));
  }
}
