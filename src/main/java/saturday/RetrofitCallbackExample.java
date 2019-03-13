package saturday;

public class RetrofitCallbackExample {
  public static void main(String[] args) throws Exception {
    DogService dogService = new DogService();
    Call call = dogService.getDogs();
    call.enqueue(new Retrofit.Callback() {
      @Override public void onResponse() {
        System.out.println("onResponse called");
      }

      @Override public void onFailure() {
        System.out.println("onFailure fired");
      }
    });

    System.out.println("after calling enqueue");
    Thread.sleep(5000);
  }
}

class Retrofit {
  public String getStuff() {
    return null;
  }

  interface Callback {
    void onResponse();

    void onFailure();
  }
}

class DogService {
  Call getDogs() {
    return new Call();
  }
}

class DogResponse {
}

class Call {
  interface OnClickListener {
    void onClick(DogService v);
  }

  void enqueue(Retrofit.Callback callback) {
    new Thread() {
      @Override public void run() {
        boolean gotResponse = connectToTheInternet();
        if (gotResponse) {
          callback.onResponse();
        } else {
          callback.onFailure();
        }
      }
    }.start();
  }

  private boolean connectToTheInternet() {
    try {
      Thread.sleep(5000);
    } catch (Exception ignored) {
    }
    return true;
  }
}