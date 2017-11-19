import java.io.IOException;

interface ApiConnector {
  String sendRequest(String params, int timeout) throws IOException;
}
