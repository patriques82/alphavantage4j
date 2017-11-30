package input;

/**
 * Builder for api parameters
 */
public class ApiParameterBuilder {
  private StringBuilder urlBuilder;

  public ApiParameterBuilder() {
    this.urlBuilder = new StringBuilder();
  }

  public ApiParameterBuilder append(ApiParameter apiParameter) {
    return this.append(apiParameter.getKey(), apiParameter.getValue());
  }

  public ApiParameterBuilder append(String key, String value) {
    String parameter = "&" + key + "=" + value;
    this.urlBuilder.append(parameter);
    return this;
  }

  public String getUrl() {
    return this.urlBuilder.toString();
  }
}
