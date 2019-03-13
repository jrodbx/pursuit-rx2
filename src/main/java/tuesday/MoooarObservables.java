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

    arrayObservable
        .map(x -> x / 10)
        .filter(x -> x != 2)
        .subscribe(observer);
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

  public <R> Observable<R> map(Transform<T, R> transform) {
    Observable<T> inputObservable = this;
    Observable<R> outputObservable = Observable.create(
        outputObserver ->
            inputObservable.subscribe(
                new Observer<>(
                    t -> outputObserver.next.nextCallback(transform.transform(t)),
                    throwable -> outputObserver.error.errorCallback(throwable),
                    () -> outputObserver.complete.completeCallback()
                )
            ));
    return outputObservable;
  }

  public Observable<T> filter(Condition<T> condition) {
    Observable<T> inputObservable = this;
    Observable<T> outputObservable = Observable.create(
        outputObserver ->
            inputObservable.subscribe(
                new Observer<>(
                    t -> {
                      if (condition.pass(t)) {
                        outputObserver.next.nextCallback(t);
                      }
                    },
                    throwable -> outputObserver.error.errorCallback(throwable),
                    () -> outputObserver.complete.completeCallback()
                )
            ));
    return outputObservable;
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

interface Transform<INPUT, OUTPUT> {
  OUTPUT transform(INPUT input);
}

interface Condition<ITEM> {
  boolean pass(ITEM item);
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
