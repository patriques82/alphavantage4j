package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.MACDData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of MACD with Controllable MA Type (MACDEXT) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MACDEXT extends TechnicalIndicatorResponse<MACDData> {

  private MACDEXT(final Map<String, String> metaData,
                  final List<MACDData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code MACDEXT} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return MACDEXT instance
   */
  public static MACDEXT from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MACDEXT}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MACDEXT> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MACDEXT";
    }

    @Override
    MACDEXT resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
      List<MACDData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new MACDData(
              resolveDate(key),
              Double.parseDouble(values.get("MACD_Signal")),
              Double.parseDouble(values.get("MACD_Hist")),
              Double.parseDouble(values.get("MACD"))
      )));
      return new MACDEXT(metaData, indicators);
    }
  }
}
