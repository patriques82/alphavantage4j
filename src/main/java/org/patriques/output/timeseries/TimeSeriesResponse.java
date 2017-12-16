package org.patriques.output.timeseries;

import org.patriques.output.timeseries.data.StockData;

import java.util.List;
import java.util.Map;

/**
 * Response from time series call. Each specific response, i.e IntraDay, Daily, etc, extends this class.
 * This class simply acts as a container of metadata and stockdata.
 */
public class TimeSeriesResponse {

  private final Map<String, String> metaData;
  private final List<StockData> stockData;

  TimeSeriesResponse(final Map<String, String> metaData, final List<StockData> stockData) {
    this.stockData = stockData;
    this.metaData = metaData;
  }

  /**
   * Meta data for response
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of StockData
   *
   * @return list of {@link StockData}.
   */
  public List<StockData> getStockData() {
    return stockData;
  }
}
