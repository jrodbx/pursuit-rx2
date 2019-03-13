package sunday;

public class CallbackHellExample {
  public static void main(String[] args) {
    DogService dogService = new DogService();
    Call call = dogService.getDogs();

    call.enqueue(new Retrofit.Callback() {
      @Override public void onResponse() {
        Call secondCall = dogService.getDog();

        secondCall.enqueue(new Retrofit.Callback() {
          @Override void onResponse() {
            Call thirdCall = dogService.getLeash();

            thirdCall.enqueue(new Retrofit.Callback() {
              @Override void onResponse() {
                //room.execute(new Callback())
              }

              @Override void onFailure() {

              }
            });
          }

          @Override void onFailure() {
          }
        });
      }

      @Override public void onFailure() {
      }
    });
  }
}

class DogService {
  Call getDogs() {
    return new Call();
  }

  Call getDog() {
    return new Call();
  }

  Call getLeash() {
    return new Call();
  }
}

class Call {
  private Retrofit.Callback callback;

  public void enqueue(Retrofit.Callback callback) {
    this.callback = callback;
  }
}

class Retrofit {
  abstract static class Callback {
    abstract void onResponse();

    abstract void onFailure();
  }
}

