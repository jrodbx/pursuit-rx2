package sunday;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersExample {
  public static void main(String[] args) throws InterruptedException {
    String[] fruit = { "apple", "banana", "grapefruit" };

    Observable.fromArray(fruit)
        .flatMap(s -> Observable
            .create(
                outputObserver -> {
                  for (int i = 1; i <= s.length(); i++) {
                    outputObserver.onNext(i);
                  }
                })
            .subscribeOn(Schedulers.computation())
        )
        .subscribe(integer -> System.out.print(integer + " "));

    Thread.sleep(1000);
  }
}
