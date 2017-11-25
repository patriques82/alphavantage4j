package parameters;

public enum OutputSize implements ApiParameter {
  COMPACT("compact"),
  FULL("full");

  private final String urlParameter;

  OutputSize(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getApiParameterKey() {
    return "outputsize";
  }

  @Override
  public String getApiParameterValue() {
    return urlParameter;
  }
}
