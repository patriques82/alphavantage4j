package parameters;

public class UrlParameterBuilder {
  private StringBuilder urlBuilder;

  public UrlParameterBuilder() {
    this.urlBuilder = new StringBuilder();
  }

  public UrlParameterBuilder append(UrlParameter urlParameter) {
    return this.append(urlParameter.getUrlParameterKey(), urlParameter.getUrlParameterValue());
  }

  public UrlParameterBuilder append(String key, String value) {
    String parameter = "&" + key + "=" + value;
    this.urlBuilder.append(parameter);
    return this;
  }

  public String getUrl() {
    return this.urlBuilder.toString();
  }
}
