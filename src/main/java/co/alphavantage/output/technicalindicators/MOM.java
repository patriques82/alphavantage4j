package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the momentum (MOM) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MOM extends TechnicalIndicatorResponse<IndicatorData> {

  private MOM(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MOM} instance from json.
   *
   * @param json string to parse
   * @return MOM instance
   */
  public static MOM from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MOM}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MOM> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MOM";
    }

    @Override
    MOM resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MOM"))
      )));
      return new MOM(metaData, indicators);
    }
  }
}
