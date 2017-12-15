package co.alphavantage.output.technicalindicators;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the ultimate oscillator (ULTOSC) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ULTOSC extends TechnicalIndicatorResponse<IndicatorData> {

  private ULTOSC(final Map<String, String> metaData,
                 final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code ULTOSC} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return ULTOSC instance
   */
  public static ULTOSC from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code ULTOSC}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<ULTOSC> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: ULTOSC";
    }

    @Override
    ULTOSC resolve(Map<String, String> metaData,
                   Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("MFI"))
      )));
      return new ULTOSC(metaData, indicators);
    }
  }
}

