package co.alphavantage.output.digitalcurrencies;

import co.alphavantage.output.digitalcurrencies.data.DigitalCurrencyData;

import java.util.List;
import java.util.Map;

public class DigitalCurrencyResponse {

  private final Map<String, String> metaData;
  private final List<DigitalCurrencyData> digitalData;

  public DigitalCurrencyResponse(Map<String, String> metaData,
                                 List<DigitalCurrencyData> digitalData) {
    this.metaData = metaData;
    this.digitalData = digitalData;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<DigitalCurrencyData> getDigitalData() {
    return digitalData;
  }
}
