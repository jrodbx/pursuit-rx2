package wednesday;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class SubjectsExample {
  public static void main(String[] args) {
    Observer<String> observer = new Observer<String>() {
      @Override public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe: " + d);
      }

      @Override public void onNext(String s) {
        System.out.println("onNext: " + s);
      }

      @Override public void onError(Throwable e) {
        System.out.println("onError: " + e);
      }

      @Override public void onComplete() {
        System.out.println("onComplete!");
      }
    };

    // observer will receive all 4 events (including "default").
    BehaviorSubject<String> subject = BehaviorSubject.createDefault("default");
    subject.subscribe(observer);
    subject.onNext("one");
    subject.onNext("two");
    subject.onNext("three");

    // observer will receive the "one", "two" and "three" events, but not "zero"
    BehaviorSubject<String> subject2 = BehaviorSubject.create();
    subject2.onNext("zero");
    subject2.onNext("one");
    subject2.subscribe(observer);
    subject2.onNext("two");
    subject2.onNext("three");

    // observer will receive only onComplete
    BehaviorSubject<String> subject3 = BehaviorSubject.create();
    subject3.subscribe(observer);
    subject3.onNext("zero");
    subject3.onNext("one");
    subject3.onComplete();

    // observer will receive only onError
    BehaviorSubject<String> subject4 = BehaviorSubject.create();
    subject4.onNext("zero");
    subject4.onNext("one");
    subject4.onError(new RuntimeException("error"));
    subject4.subscribe(observer);
  }
}
