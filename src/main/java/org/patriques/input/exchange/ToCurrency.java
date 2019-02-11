package org.patriques.input.exchange;

import org.patriques.input.ApiParameter;

/**
 * The currency to be converted in the ForeignExchange converter
 */
public class ToCurrency implements ApiParameter {
  private static final long serialVersionUID = -1094789488420564054L;
  private String toCurrency;

  public ToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }

  @Override
  public String getKey() {
    return "to_currency";
  }

  @Override
  public String getValue() {
    return toCurrency;
  }

}
