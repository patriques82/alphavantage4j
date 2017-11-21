package common;

public abstract class Try<V> {

  private Try() {
  }

  public abstract boolean isSuccess();

  public abstract boolean isFailure();

  public abstract String getError();

  public abstract V getValue();

  public static <V> Try<V> failure(String message) {
    return new Failure<>(message);
  }

  public static <V> Try<V> success(V value) {
    return new Success<>(value);
  }

  private static class Failure<V> extends Try<V> {
    private String errorMessage;

    Failure(String message) {
      super();
      this.errorMessage = message;
    }

    @Override
    public boolean isSuccess() {
      return false;
    }

    @Override
    public boolean isFailure() {
      return true;
    }

    @Override
    public String getError() {
      return this.errorMessage;
    }

    @Override
    public V getValue() {
      return null;
    }

  }

  private static class Success<V> extends Try<V> {
    private V value;

    Success(V value) {
      super();
      this.value = value;
    }

    @Override
    public boolean isSuccess() {
      return true;
    }

    @Override
    public boolean isFailure() {
      return false;
    }

    @Override
    public String getError() {
      return null;
    }

    @Override
    public V getValue() {
      return value;
    }

  }
}

