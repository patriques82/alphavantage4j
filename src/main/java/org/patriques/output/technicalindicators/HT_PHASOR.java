package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.HT_PHASORData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, phasor components (HT_PHASOR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_PHASOR extends TechnicalIndicatorResponse<HT_PHASORData> {

  private HT_PHASOR(final Map<String, String> metaData,
                    final List<HT_PHASORData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_PHASOR} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return HT_PHASOR instance
   */
  public static HT_PHASOR from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_PHASOR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_PHASOR> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_PHASOR";
    }

    @Override
    HT_PHASOR resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData) {
      List<HT_PHASORData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new HT_PHASORData(
              resolveDate(key),
              Double.parseDouble(values.get("PHASE")),
              Double.parseDouble(values.get("QUADRATURE"))
      )));
      return new HT_PHASOR(metaData, indicators);
    }
  }
}
