package output;

public class AlphaVantageException extends Exception {
  public AlphaVantageException(String message, Exception e) {
    super(message, e);
  }

  public AlphaVantageException(String message) {
    super(message);
  }
}
