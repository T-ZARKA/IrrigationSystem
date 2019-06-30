package com.example.android.irrigationsystem;
//we creat this api to communicate with our server
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
    String BASE_URL = "http://obadahnawafleh.ipage.com/water_system/";//the path where the gson file exists "base address"


    @GET("json_get.php") //get request is to get data from the server and we give it the gson file name "relative address"
 // Method return Call object which is executed to send the network request and return the response.
    //we want list of water system objects to be returned from the response
    Call<List<water_system>> getInfo();



  //to be used later
    // /*@Query("id") int ID*/
   /* @GET("json_get{id}.php")
    Call<List<water_system>> getInfo1(@Path("id") int ID);

    @POST("json_get.php")
   Call<water_system> createWaterSystem (@Body water_system w);
    @PUT("json_get{id}.php")
    Call<water_system> Update (@Path("id") int id,@Body water_system w);
    @PATCH("json_get{id}.php")
    Call<water_system> UpdatePatch (@Path("id") int id,@Body water_system w);*/


}
