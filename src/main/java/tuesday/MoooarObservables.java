package tuesday;

import java.util.stream.Stream;

public class MoooarObservables {
  public static void main(String[] args) {
    Observer<Object> observer = new Observer<>(
        o -> System.out.println(o),
        throwable -> System.out.println(throwable),
        () -> System.out.println("done")
    );

    giveMeSomeData(observer);
  }

  static void giveMeSomeData(Observer<Object> observer) {
    Stream.of(10, 20, 30)
        .forEach(i -> observer.next.nextCallback(i));
  }
}

class Observer<T> {
  NextCallback<T> next;
  ErrorCallback error;
  CompleteCallback complete;

  public Observer(NextCallback<T> next, ErrorCallback error, CompleteCallback complete) {
    this.next = next;
    this.error = error;
    this.complete = complete;
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
