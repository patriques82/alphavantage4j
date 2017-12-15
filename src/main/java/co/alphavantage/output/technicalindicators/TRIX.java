package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the 1-day rate of change of a triple smooth exponential moving average (TRIX) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class TRIX extends TechnicalIndicatorResponse<IndicatorData> {

  private TRIX(final Map<String, String> metaData,
               final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code TRIX} instance from json.
   *
   * @param json string to parse
   * @return TRIX instance
   */
  public static TRIX from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code TRIX}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<TRIX> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: TRIX";
    }

    @Override
    TRIX resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("TRIX"))
      )));
      return new TRIX(metaData, indicators);
    }
  }
}
