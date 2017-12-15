package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.HT_SINEData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, sine wave (HT_SINE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_SINE extends TechnicalIndicatorResponse<HT_SINEData> {

  private HT_SINE(final Map<String, String> metaData,
                  final List<HT_SINEData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_SINE} instance from json.
   *
   * @param json string to parse
   * @return HT_SINE instance
   */
  public static HT_SINE from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_SINE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_SINE> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_SINE";
    }

    @Override
    HT_SINE resolve(Map<String, String> metaData,
                         Map<String, Map<String, String>> indicatorData) {
      List<HT_SINEData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new HT_SINEData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("LEAD SINE")),
              Double.parseDouble(values.get("SINE"))
      )));
      return new HT_SINE(metaData, indicators);
    }
  }
}
