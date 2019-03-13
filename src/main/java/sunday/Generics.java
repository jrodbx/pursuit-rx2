package sunday;

import java.util.HashMap;
import java.util.Map;

public class Generics {
  public static void main(String[] args) {

    MyList<Integer> integerList = new MyList<>();
    integerList.add(5);

    MyList<String> stringList = new MyList<>();
    stringList.add("");

    Map<Integer, String> map = new HashMap<>();
    map.put(5, "");

    Map<String, Character> map2 = new HashMap<>();
    map2.put("", ' ');
  }
}

class MyList<T> {
  int counter = 0;
  Object[] array = new Object[10];

  void add(T i) {
    array[counter++] = i;
  }
  int size() {
    return counter;
  }
}
