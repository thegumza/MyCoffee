package thegumza.mycoffee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.drivemode.android.typeface.TypefaceHelper;
import com.google.gson.GsonBuilder;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    SearchBox searchbox;
    CallApi callApi;
    String URL;
    GsonBuilder builder;
    RestAdapter restAdapter;
    ApiService retrofit;
    List<Coffee> coffeeList;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceHelper.initialize(getApplication());
        setContentView(R.layout.activity_main);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        searchbox = (SearchBox)findViewById(R.id.searchbox);
        searchbox.setLogoText("Search");
        searchbox.setBackgroundColor(Color.parseColor("#795548"));
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);


        callApi = CallApi.getInstance();
        URL = callApi.URL;
        builder = callApi.builder;
        restAdapter  = callApi.restAdapter;
        retrofit = restAdapter.create(ApiService.class);
        retrofit.getCoffeeWithCallBack(new Callback<List<Coffee>>() {
            @Override
            public void success(List<Coffee> coffees, Response response) {
                coffeeList = coffees;
                CoffeeListAdapter adapter = new CoffeeListAdapter(coffeeList, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

}
