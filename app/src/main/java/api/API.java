package api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static Retrofit getClient()
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://172.16.20.131:8087")       //https://10.177.69.49
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;

    }
}
