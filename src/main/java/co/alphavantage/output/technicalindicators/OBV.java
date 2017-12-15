package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the on balance volume (OBV) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class OBV extends TechnicalIndicatorResponse<IndicatorData> {

  private OBV(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code OBV} instance from json.
   *
   * @param json string to parse
   * @return OBV instance
   */
  public static OBV from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code OBV}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<OBV> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: OBV";
    }

    @Override
    OBV resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("OBV"))
      )));
      return new OBV(metaData, indicators);
    }
  }
}

