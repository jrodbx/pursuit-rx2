package saturday;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Java8Exercises {
}

/**
 * 1) define a callback that accepts a saturday.Person object and return a list of phone numbers
 * 2) define a method that takes an instance of this callback and executes it
 * 3) call the method and pass an anonymous class as the instance argument
 * 4) call the method again and manually write the lambda as the instance argument
 * 5) verify that you wrote the lambda correctly by automatically converting the anonymous class
 * instance in #3 using the Option-Enter key shortcut in IntelliJ.
 */

class MainClass {
  public static void main(String[] args) {
    someMethod(new Callback() {
      @Override public List<String> something(Person person) {
        return Arrays.asList("646-123-4567", "not a real phone number");
      }
    });
    someMethod(person -> Arrays.asList("646-123-4567", "not a real phone number"));
    someMethod(person -> Arrays.asList("646-123-4567", "not a real phone number"));
  }

  static void someMethod(Callback callback) {
    List<String> something = callback.something(new Person());
  }
}

interface Callback {
  List<String> something(Person person);
}

class Person {
}

/**
 * Write the anonymous class alternatives of the following lambdas.  You may need to write
 * additional
 * code to satisfy compilation.
 *
 * 1) s -> 27
 * 2) t -> t.length()
 * 3) u -> { System.out.println(u); return 4; }
 * 4) v -> v.name
 */

class Lambdasssss {
  public static void main(String[] args) {
    WhateverClass whateverClass = v -> v.name;
  }
}

interface WhateverClass {
  String whateverMethod(Foo v);
}

class Foo {
  String name;
}

/**
 * Write the lambda alternatives of the following anonymous classes.  You may assume all instances
 * are functional interfaces, and therefore, have only a single abstract method.
 *
 * 1)
 * new CarHorn() {
 *
 * @Override public void doHonk(Sound sound) {
 * playSound(sound);
 * }
 * }
 *
 * 2)
 * new Callable<String>() {
 * @Override public String call() {
 * return fakeRetrofit.getStuff();
 * }
 * }
 *
 * 3)
 * new saturday.Query() {
 * @Override public String onExecute(String firstName) {
 * String lastName = phoneBook.get(firstName);
 * return lastName;
 * }
 * }
 */

class Lambdasssss2 {
  static Map<String, String> phoneBook;

  public static void main(String[] args) {
    Query query = (firstName, age) -> {
      return phoneBook.get(firstName);
    };

    Object s = "";

    Query query1 = new Query() {
      @Override public String onExecute(String firstName, int age) {
        return "John";
      }
    };
  }
}

interface Query {
  String onExecute(String firstName, int age);
}



