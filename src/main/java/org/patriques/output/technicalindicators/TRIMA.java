package org.patriques.output.technicalindicators;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;
import org.patriques.output.technicalindicators.data.IndicatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of triangular exponential moving average (TRIMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class TRIMA extends TechnicalIndicatorResponse<IndicatorData> {

  private static final long serialVersionUID = -2741344821467886628L;

  private TRIMA(final Map<String, String> metaData,
                final List<IndicatorData> indicatorData) {
    super(metaData, indicatorData);
  }

  /**
   * Creates {@code TRIMA} instance from json.
   *
   * @param interval specifies how to interpret the date key to the data json object
   * @param json string to parse
   * @return TRIMA instance
   */
  public static TRIMA from(Interval interval, String json) {
    Parser parser = new Parser(interval);
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code TRIMA}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<TRIMA> {

    public Parser(Interval interval) {
      super(interval);
    }

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: TRIMA";
    }

    @Override
    TRIMA resolve(Map<String, String> metaData,
                  Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              resolveDate(key),
              Double.parseDouble(values.get("TRIMA"))
      )));
      return new TRIMA(metaData, indicators);
    }

  }
}