package tuesday;

import java.util.stream.Stream;

public class MoooarObservables {
  public static void main(String[] args) {
    NextCallback<Object> nextCallback = o -> System.out.println(o);

    ErrorCallback errorCallback = throwable -> System.out.println(throwable);

    CompleteCallback completeCallback = () -> System.out.println("done");

    giveMeSomeData(
      nextCallback,
      errorCallback,
      completeCallback
    );
  }

  static void giveMeSomeData(
      NextCallback<Object> next, ErrorCallback error, CompleteCallback complete
  ) {
    Stream.of(10,20,30)
        .forEach(i -> next.nextCallback(i));
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
