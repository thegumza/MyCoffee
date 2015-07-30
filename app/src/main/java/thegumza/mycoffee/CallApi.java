package thegumza.mycoffee;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Wiwat on 7/30/2015.
 */
public class CallApi {
    private static CallApi mInstance = null;
    String URL;
    GsonBuilder builder;
    RestAdapter restAdapter;
    ApiService retrofit;

    public  CallApi(){
        URL = "http://developer-thegumza.rhcloud.com";
        builder = new GsonBuilder();
        restAdapter  = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(URL)
                .setConverter(new GsonConverter(builder.create()))
                .build();
        retrofit = restAdapter.create(ApiService.class);
    }
    public static CallApi getInstance(){
        if(mInstance == null)
        {
            mInstance = new CallApi();
        }
        return mInstance;
    }

    public GsonBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(GsonBuilder builder) {
        this.builder = builder;
    }

    public static CallApi getmInstance() {
        return mInstance;
    }

    public static void setmInstance(CallApi mInstance) {
        CallApi.mInstance = mInstance;
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public void setRestAdapter(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    public ApiService getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(ApiService retrofit) {
        this.retrofit = retrofit;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
