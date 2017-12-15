package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the money flow index (MFI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MFI extends TechnicalIndicatorResponse<IndicatorData> {

  private MFI(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code MFI} instance from json.
   *
   * @param json string to parse
   * @return MFI instance
   */
  public static MFI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code MFI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<MFI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: MFI";
    }

    @Override
    MFI resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("MFI"))
      )));
      return new MFI(metaData, indicators);
    }
  }
}
