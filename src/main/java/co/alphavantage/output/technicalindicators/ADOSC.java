package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Chaikin A/D oscillator (ADOSC) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ADOSC extends TechnicalIndicatorResponse<IndicatorData> {

  private ADOSC(final Map<String, String> metaData,
                final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ADOSC} instance from json.
   *
   * @param json string to parse
   * @return ADOSC instance
   */
  public static ADOSC from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ADOSC}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ADOSC> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ADOSC";
    }

    @Override
    ADOSC resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ADOSC"))
      )));
      return new ADOSC(metaData, indicators);
    }
  }
}
