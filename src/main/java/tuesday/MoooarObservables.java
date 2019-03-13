package tuesday;

public class MoooarObservables {
  public static void main(String[] args) {
    NextCallback<Object> nextCallback = o -> { };

    ErrorCallback errorCallback = throwable -> { };

    CompleteCallback completeCallback = () -> { };

    giveMeSomeData(
      nextCallback,
      errorCallback,
      completeCallback
    );
  }

  static void giveMeSomeData(
      NextCallback<Object> next, ErrorCallback error, CompleteCallback complete
  ) {
  }
}

interface NextCallback<T> {
  void nextCallback(T t);
}

interface ErrorCallback {
  void errorCallback(Throwable throwable);
}

interface CompleteCallback {
  void completeCallback();
}
