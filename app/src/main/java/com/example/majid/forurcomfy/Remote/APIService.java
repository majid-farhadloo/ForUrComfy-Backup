package com.example.majid.forurcomfy.Remote;

import com.example.majid.forurcomfy.Data.model.FoodMenu;
import com.example.majid.forurcomfy.Data.model.OrderProcess;
import com.example.majid.forurcomfy.model.Post;

import org.json.JSONObject;

import java.util.List;

import LayOutReturn.DeliveryItem;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by farha on 4/2/2018.
 */
public interface APIService {

    @POST("/login")
    @FormUrlEncoded
    Call<Post> savePost(@Field("email") String email,
                        @Field("password") String pass);

    @GET("/food_menu")
    Call<List<FoodMenu>> reqData();

        @POST("/order")
        @FormUrlEncoded
        Call<OrderProcess> request(@Field("food") JSONObject foods,
                                   @Field("name") String current,
                                   @Field("cell") String cell,
                                   @Field("location") String location);

    @GET("/delivery/request")
    Call<List<DeliveryItem>> reqDelivery();

    @POST("/delivery/claim")
    Call<String> claimed(@Field("_id") String id);

}
