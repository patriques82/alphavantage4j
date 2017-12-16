package org.patriques.output.technicalindicators;

import java.util.List;
import java.util.Map;

/**
 * Response from technical indicator call. Each specific response, i.e EMA, MACD, etc, extends this class.
 * This class simply acts as a container of metadata and indicator data.
 *
 * @param <Data> the data contained in the response
 */
public class TechnicalIndicatorResponse<Data> {

  private final Map<String, String> metaData;
  private final List<Data> indicatorData;

  TechnicalIndicatorResponse(final Map<String, String> metaData, final List<Data> indicatorData) {
    this.metaData = metaData;
    this.indicatorData = indicatorData;
  }

  /**
   * Meta data for response.
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of generic Data
   *
   * @return list of Data
   */
  public List<Data> getData() {
    return indicatorData;
  }
}
