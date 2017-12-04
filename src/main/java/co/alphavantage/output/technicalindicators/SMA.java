package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.AlphaVantageException;
import co.alphavantage.output.technicalindicators.data.SMAData;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SMA {
  private final Map<String, String> metaData;
  private final List<SMAData> indicatorData;

  private SMA(final Map<String, String> metaData, final List<SMAData> indicatorData) {
    this.metaData = metaData;
    this.indicatorData = indicatorData;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<SMAData> getData() {
    return indicatorData;
  }

  public static SMA from(String json) throws AlphaVantageException {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  private static class Parser extends TechnicalIndicatorParser<SMA> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: SMA";
    }

    @Override
    SMA resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) throws AlphaVantageException {
      List<SMAData> indicators = new ArrayList<>();
      try {
        indicatorData.forEach((key, values) -> indicators.add(new SMAData(
                DateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
                Double.parseDouble(values.get("SMA"))
        )));
      } catch (Exception e) {
        throw new AlphaVantageException("SMA adjusted api change", e);
      }
      return new SMA(metaData, indicators);
    }

  }
}
