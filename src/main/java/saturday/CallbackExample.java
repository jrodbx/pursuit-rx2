package saturday;

public class CallbackExample {
  public static void main(String[] args) {
    Fetcher fetcher = new Fetcher();

    Fetcher.Callback callback = new Fetcher.Callback() {
      @Override public void returnString(String s) {
        System.out.println("yo");

        new Thread() {
          @Override public void run() {
            System.out.println(s);
          }
        }.start();

        System.out.println("sup");
      }
    };

    fetcher.fetchString(callback);
    System.out.println("hi");

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Fetcher {
  void fetchString(Callback callback) {
    // open HTTP connection

    // write some data to the HTTP connection

    // wait

    // read from data from the HTTP connection

    // deserialize data into a string

    callback.returnString("TheDataYouWant");
  }

  interface Callback {
    void returnString(String s);
  }
}