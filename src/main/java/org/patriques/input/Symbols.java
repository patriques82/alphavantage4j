package org.patriques.input;

/**
 * The symbols parameter for the batch stock quotes api call.
 */
public class Symbols implements ApiParameter {
  private static final long serialVersionUID = 6585859613296530932L;
  private String[] symbols;

  public Symbols(String... symbols) {
    this.symbols = symbols;
  }

  @Override
  public String getKey() {
    return "symbols";
  }

  @Override
  public String getValue() {
    return String.join(",", symbols);
  }
}
