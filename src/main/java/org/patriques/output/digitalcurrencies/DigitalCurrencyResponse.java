package org.patriques.output.digitalcurrencies;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DigitalCurrencyResponse<Data extends Serializable> implements Serializable {
  private static final long serialVersionUID = 6530825128585084114L;

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
