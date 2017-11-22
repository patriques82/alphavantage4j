import parameters.UrlParameter;

import java.io.IOException;

@FunctionalInterface
public interface ApiConnector {
  String sendRequest(String symbol, UrlParameter ...urlParameters) throws IOException;
}
