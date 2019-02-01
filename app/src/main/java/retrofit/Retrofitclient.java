package retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofitclient {
    public static Retrofit getmInstance() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        if (mInstance == null) {
            mInstance = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").client(okHttpClient).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mInstance;
    }

    private static Retrofit mInstance;
}
