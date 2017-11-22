import java.io.IOException;

@FunctionalInterface
public interface ApiConnector {
  String sendRequest(String params, int timeout) throws IOException;
}
