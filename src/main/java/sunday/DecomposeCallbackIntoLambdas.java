package sunday;

public class DecomposeCallbackIntoLambdas {
  public static void main(String[] args) {
    makeNetworkRequest(
        () -> {
        },
        () -> {
        }
    );
  }

  static void makeNetworkRequest(MyRetrofitCallback.OnResponseCallback onResponseCallback,
      MyRetrofitCallback.OnFailureCallback onFailureCallback) {
    new MyRetrofitCallback(onResponseCallback, onFailureCallback);
  }
}

class MyRetrofitCallback implements RetrofitCallback {
  private final OnResponseCallback or;
  private final OnFailureCallback of;

  public MyRetrofitCallback(OnResponseCallback or, OnFailureCallback of) {
    this.or = or;
    this.of = of;
  }

  @Override public void onResponse() {
    or.onResponse();
  }

  @Override public void onFailure() {
    of.onFailure();
  }

  interface OnResponseCallback {
    void onResponse();
  }

  interface OnFailureCallback {
    void onFailure();
  }
}

interface RetrofitCallback {
  void onResponse();

  void onFailure();
}


