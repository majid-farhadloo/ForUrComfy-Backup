package com.example.majid.forurcomfy.Remote;

import LayOutReturn.DeliveryItem;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by farha on 5/7/2018.
 */

public interface  MyWebService {
    String URL = "https://node-practice0208.herokuapp.com";
    String FEED = "/delivery/request";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

    @GET("/delivery/request")
     Call<DeliveryItem> reqDelivery();

}
