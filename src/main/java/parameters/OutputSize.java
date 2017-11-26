package parameters;

/**
 * Outputsize parameter, i.e outputsize=compact
 */
public enum OutputSize implements ApiParameter {
  COMPACT("compact"),
  FULL("full");

  private final String urlParameter;

  OutputSize(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getKey() {
    return "outputsize";
  }

  @Override
  public String getValue() {
    return urlParameter;
  }
}
