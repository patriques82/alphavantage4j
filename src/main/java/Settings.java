public class Settings {
  private final String apiKey;
  private final int timeout;

  public Settings(String apiKey) {
    this.apiKey = apiKey;
    this.timeout = 30000;
  }

  public Settings(String apiKey, int timeout) {
    this.apiKey = apiKey;
    this.timeout = timeout;
  }

  String getApiKey() {
    return apiKey;
  }

  int getTimeout() {
    return timeout;
  }

}
