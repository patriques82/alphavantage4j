package co.alphavantage.output.technicalindicators;

import co.alphavantage.input.technicalindicators.Interval;
import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of triple exponential moving average (TEMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class TEMA extends TechnicalIndicatorResponse<IndicatorData> {

  private TEMA(final Map<String, String> metaData,
               final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code TEMA} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return TEMA instance
   */
  public static TEMA from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code TEMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<TEMA> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: TEMA";
    }

    @Override
    TEMA resolve(Map<String, String> metaData,
                 Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("TEMA"))
      )));
      return new TEMA(metaData, indicators);
    }
  }
}
