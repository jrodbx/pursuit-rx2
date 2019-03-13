package tuesday;

import java.util.stream.Stream;

public class MoooarObservables {
  public static void main(String[] args) {
    Observable<Integer> arrayObservable =
        Observable.create(observer -> {
          Stream.of(10, 20, 30)
              .forEach(i -> observer.next.nextCallback(i));
          observer.complete.completeCallback();
        });

    Observer<Integer> observer = new Observer<>(
        o -> System.out.println(o),
        throwable -> System.out.println(throwable),
        () -> System.out.println("done")
    );

    arrayObservable.subscribe(observer);
  }
}

class Observable<T> {
  Subscribe<T> innerSubscribe;

  public Observable(Subscribe<T> subscribe) {
    this.innerSubscribe = subscribe;
  }

  void subscribe(Observer<T> observer) {
    innerSubscribe.subscribe(observer);
  }

  static <T> Observable<T> create(Subscribe<T> subscribe) {
    return new Observable<>(subscribe);
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

interface Subscribe<T> {
  void subscribe(Observer<T> observer);
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
