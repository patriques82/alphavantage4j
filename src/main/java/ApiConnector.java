import java.io.IOException;

@FunctionalInterface
interface ApiConnector {
  String sendRequest(String params, int timeout) throws IOException;
}
