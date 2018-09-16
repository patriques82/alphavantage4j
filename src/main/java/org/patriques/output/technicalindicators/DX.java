package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the directional movement index (DX)  response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class DX extends TechnicalIndicatorResponse<IndicatorData> {

  private DX(final Map<String, String> metaData,
             final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code DX} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return DX instance
   */
  public static DX from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code DX}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<DX> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: DX";
    }

    @Override
    DX resolve(Map<String, String> metaData,
                   Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("DX"))
      )));
      return new DX(metaData, indicators);
    }
  }
}

