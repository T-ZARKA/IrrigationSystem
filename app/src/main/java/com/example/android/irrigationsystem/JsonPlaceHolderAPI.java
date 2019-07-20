package com.example.android.irrigationsystem;
//we creat this api to communicate with our server

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
    String BASE_URL = "http://obadahnawafleh.ipage.com/towa_irr_sys/";//the path where the gson file exists "base address"

    //get request is to get data from the server and we give it the gson file name "relative address"
    // Method return Call object which is executed to send the network request and return the response.
    //we want list of water system objects to be returned from the response
    @GET("json_get.php")
    Call<List<water_system>> getInfo();

    @FormUrlEncoded
    @POST("json_updateAll.php")//this file contains sql query update all where id= id
    Call<water_system> uP  //to update all attributes we send all parameters
            (@Field("id") int id,
             @Field("level") String level,
             @Field("temp") String temp,
             @Field("humidity") String humidity,
             @Field("pump") String pump,
             @Field("user_msg") String user_msg

            );

    @FormUrlEncoded
    @POST("json_updatePump.php")//this file contains sql query update pump where id= id
        //to update the pump only we send the new pump value and the id of it
        // ,it well read th file json_updatePump.php
    Call<water_system> updatePump
            (@Field("id") int id, //it well be send to the file
             @Field("pump") String pump    /*it well be send to the file */        );

    @FormUrlEncoded
    @POST("json_updateMsg.php")//this file contains sql query update user_msg where id= id
    Call<water_system> updateMsg
            (@Field("id") int id,//it well be send to the file
             @Field("user_msg") String user_msg  /*it well be send to the file */ );


}
