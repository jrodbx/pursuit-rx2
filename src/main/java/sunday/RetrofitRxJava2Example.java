package sunday;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitRxJava2Example {

  private static YeService yeService;
  private static DogService2 dogService2;

  public static void main(String[] args) {
    setupRetrofitServices();

    dogService2.getRandomImage()
        .flatMap(
            image -> yeService.getYeQuote(),
            (image, yeQuote) -> new Pair(image, yeQuote))
        .subscribe(
            pair -> System.out.println(pair.image + "\n" + pair.yeQuote),
            throwable -> System.out.println("whoops, there is an error: " + throwable)
        );
  }

  private static void setupRetrofitServices() {
    Retrofit dogRetrofit = new Retrofit.Builder()
        .baseUrl("https://dog.ceo")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    Retrofit yeRetrofit = new Retrofit.Builder()
        .baseUrl("https://api.kanye.rest")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    dogService2 = dogRetrofit.create(DogService2.class);
    yeService = yeRetrofit.create(YeService.class);
  }
}

class Pair {
  Image image;
  YeQuote yeQuote;

  public Pair(Image image, YeQuote yeResponse) {
    this.image = image;
    this.yeQuote = yeResponse;
  }
}

interface DogService2 {
  @GET("api/breeds/image/random")
  Observable<Image> getRandomImage();
}

interface YeService {
  @GET("/")
  Observable<YeQuote> getYeQuote();
}

class Image {
  private final String status;
  private final String message;

  Image(String status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override public String toString() {
    return "this is my image: { status: " + status + ", message: " + message + "}";
  }
}

class YeQuote {
  private final String quote;
  private final String id;

  YeQuote(String quote, String id) {
    this.quote = quote;
    this.id = id;
  }

  @Override public String toString() {
    return "{ quote: " + quote + ", id: " + id + "}";
  }
}


