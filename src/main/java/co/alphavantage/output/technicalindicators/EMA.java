package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.EMAData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of exponential moving average (EMA) response from api.
 */
public class EMA {
  private final Map<String, String> metaData;
  private final List<EMAData> indicatorData;

  private EMA(final Map<String, String> metaData, final List<EMAData> indicatorData) {
    this.metaData = metaData;
    this.indicatorData = indicatorData;
  }

  /**
   * Meta data for EMA data
   *
   * @return map of keys and values in json representation of metadata.
   */
  public Map<String, String> getMetaData() {
    return metaData;
  }

  /**
   * List of EMAData
   *
   * @return list of {@link EMAData}.
   */
  public List<EMAData> getData() {
    return indicatorData;
  }

  /**
   * Creates {@code EMA} instance from json
   * @param json string to parse
   * @return EMA instance
   * @throws AlphaVantageException
   */
  public static EMA from(String json) throws AlphaVantageException {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code EMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<EMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: EMA";
    }

    @Override
    EMA resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<EMAData> indicators = new ArrayList<>();
      try {
        indicatorData.forEach((key, values) -> indicators.add(new EMAData(
                DateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
                Double.parseDouble(values.get("EMA"))
        )));
      } catch (Exception e) {
        throw new AlphaVantageException("EMA adjusted api change", e);
      }
      return new EMA(metaData, indicators);
    }

  }
}