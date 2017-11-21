package parameters;

public enum OutputSize implements UrlParameter {
  COMPACT("compact"),
  FULL("full");

  private final String urlParameter;

  OutputSize(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  @Override
  public String getUrlParameterKey() {
    return "outputsize";
  }

  @Override
  public String getUrlParameterValue() {
    return urlParameter;
  }
}
