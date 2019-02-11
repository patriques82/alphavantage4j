package org.patriques.input;

/**
 * The symbol parameter for the technical indicators/time series api call.
 */
public class Symbol implements ApiParameter {
  private static final long serialVersionUID = -2049945286166317315L;
  private String symbol;

  public Symbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String getKey() {
    return "symbol";
  }

  @Override
  public String getValue() {
    return symbol;
  }
}
