package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.SMAData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of simple moving average (SMA) response from api.
 */
public class SMA {
  private final Map<String, String> metaData;
  private final List<SMAData> indicatorData;

  private SMA(final Map<String, String> metaData, final List<SMAData> indicatorData) {
    this.metaData = metaData;
    this.indicatorData = indicatorData;
  }

  /**
   * Meta data for SMA data
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of SMAData
   *
   * @return list of {@link SMAData}.
   */
  public List<SMAData> getData() {
    return indicatorData;
  }

  /**
   * Creates {@code SMA} instance from json.
   *
   * @param json string to parse
   * @return SMA instance
   */
  public static SMA from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code SMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<SMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: SMA";
    }

    @Override
    SMA resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<SMAData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(getSMAData(key, values)));
      return new SMA(metaData, indicators);
    }

    private SMAData getSMAData(String key, Map<String, String> values) {
      try {
        return new SMAData(
                DateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
                Double.parseDouble(values.get("SMA"))
        );
      } catch (Exception e) {
        throw new AlphaVantageException("SMA adjusted api change", e);
      }
    }

  }
}
