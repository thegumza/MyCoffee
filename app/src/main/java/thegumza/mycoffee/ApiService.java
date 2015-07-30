package thegumza.mycoffee;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Wiwat on 7/30/2015.
 */
public interface ApiService {

    @GET("/coffee.php")
    void getCoffeeWithCallBack(Callback<List<Coffee>> callback);

}
