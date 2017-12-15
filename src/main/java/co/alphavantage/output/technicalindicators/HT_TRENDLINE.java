package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, instantaneous trendline (HT_TRENDLINE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_TRENDLINE extends TechnicalIndicatorResponse<IndicatorData> {

  private HT_TRENDLINE(final Map<String, String> metaData,
                       final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_TRENDLINE} instance from json.
   *
   * @param json string to parse
   * @return HT_TRENDLINE instance
   */
  public static HT_TRENDLINE from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_TRENDLINE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_TRENDLINE> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_TRENDLINE";
    }

    @Override
    HT_TRENDLINE resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("HT_TRENDLINE"))
      )));
      return new HT_TRENDLINE(metaData, indicators);
    }
  }
}
