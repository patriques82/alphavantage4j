package org.patriques.input.exchange;

import org.patriques.input.ApiParameter;

/**
 * The currency which is the base of the conversion in ForeignExchange functionality
 */
public class FromCurrency implements ApiParameter {
  private static final long serialVersionUID = -2777189941468459871L;
  String fromCurrency;

  public FromCurrency(String fromCurrency) {
    this.fromCurrency = fromCurrency;
  }

  @Override
  public String getKey() {
    return "from_currency";
  }

  @Override
  public String getValue() {
    return fromCurrency;
  }

}
