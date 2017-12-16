package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.AROONData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Aroon (AROON) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class AROON extends TechnicalIndicatorResponse<AROONData> {

  private AROON(final Map<String, String> metaData,
                final List<AROONData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code AROON} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return AROON instance
   */
  public static AROON from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code AROON}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<AROON> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: AROON";
    }

    @Override
    AROON resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<AROONData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new AROONData(
              resolveDate(key),
              Double.parseDouble(values.get("Aroon Up")),
              Double.parseDouble(values.get("Aroon Down"))
      )));
      return new AROON(metaData, indicators);
    }
  }
}

