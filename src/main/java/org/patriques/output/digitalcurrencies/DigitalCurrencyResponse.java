package org.patriques.output.digitalcurrencies;

import java.util.List;
import java.util.Map;

public class DigitalCurrencyResponse<Data> {

  private final Map<String, String> metaData;
  private final List<Data> digitalData;

  public DigitalCurrencyResponse(Map<String, String> metaData,
                                 List<Data> digitalData) {
    this.metaData = metaData;
    this.digitalData = digitalData;
  }

  public Map<String, String> getMetaData() {
    return metaData;
  }

  public List<Data> getDigitalData() {
    return digitalData;
  }
}
