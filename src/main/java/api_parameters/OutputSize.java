package api_parameters;

public enum OutputSize {
  COMPACT("compact"),
  FULL("full");

  private final String urlParameter;

  OutputSize(String urlParameter) {
    this.urlParameter = urlParameter;
  }

  String getUrlParameter() {
    return urlParameter;
  }

}
