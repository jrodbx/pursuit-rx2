package saturday;

public class TargetTyping {
  public static void main(String[] args) {
    String s = "";
    String t = s;
    String u = getString();

    t.indexOf('s');
    u.indexOf('s');
    SomeClass someClass = new SomeClass();

    someClass.doSomething(() -> "done");
    //someClass.doSomething(() -> {});

    //saturday.Finder foo = String::indexOf;
    //foo.find("apple", "a");
  }

  public static String getString() {
    return "";
  }
}

interface RunMyCode {
  void run();
}

interface ExecuteMyCode {
  void execute();
}

interface GiveMeString {
  String giveString();
}

class SomeClass {
  void doSomething(RunMyCode r) {
    r.run();
  }

  void doSomething(ExecuteMyCode e) {
    e.execute();
  }

  String doSomething(GiveMeString g) {
    return g.giveString();
  }
}

interface Finder {
  public int find(String s1, String s2);
}