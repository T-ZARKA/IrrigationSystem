package com.example.android.irrigationsystem;
//we implemented retrofit library and gson converter library in the gradle
//we set user permission for the internet in the manifest
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  //  we need to connect our layout with out java code so declare variables of the same type in the layout
    TextView l, t, h, p, d,m;
    Button on, off;
    EditText usermsg;
private JsonPlaceHolderAPI jsonPlaceHolderAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState)// here where it all starts
   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //to connect with the layout we give
       // each variable the id of what we want to connect with from the layout
        l = findViewById(R.id.l_tv);
        t = findViewById(R.id.t_tv);
        h = findViewById(R.id.h_tv);
        p = findViewById(R.id.pumb_tv);
        d = findViewById(R.id.d_tv);
        on = findViewById(R.id.on);
        off = findViewById(R.id.off);
        usermsg = findViewById(R.id.usermsgET);
        m=findViewById(R.id.message);
on.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        updatePump("1");

    }
});
off.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        updatePump("0");
    }
});
       //this is to convert from gson object to java object
         Gson gson = new GsonBuilder()
                .setLenient()
                .create();

         //to execute our get request we need an instance of the retrofit
        Retrofit retrofit = new Retrofit.Builder() /*method in retrofit to build the request*/
                .baseUrl(JsonPlaceHolderAPI.BASE_URL)  /*we give it the base url that we declared in the API interface*/
                .addConverterFactory(GsonConverterFactory.create(gson))/*here we define we ant to use gson to pass the get response*/
                .build();

         jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);//here so we can use the methods in our interface
       // no need for implementation retrofit take cares of it
        getInfo(); // function call


            }
    private void getInfo(){
        // to execute our get request we use the call object defined earlier
        Call<List<water_system>> call = jsonPlaceHolderAPI.getInfo();

        call.enqueue(new Callback<List<water_system>>() { /* the execution!!*/
            @Override
            public void onResponse(Call<List<water_system>> call, Response<List<water_system>> response) {
                //this method os when we get something back from the server but necessarily successful response
                if (!response.isSuccessful()) {
                     d.setText(response.code());//the code that well be returned from the server "http address"
                    return;
                }



                    List<water_system> sysInfo = response.body();
                // body contains list of water system object
                //this is the data that we want
                //this well return a list of water system objects the list now is sysInfo

                    for (water_system i : sysInfo) {/* for each i "water system object" in the sysInfo*/

                        l.setText(i.getLevel());//methods declared in our water_system java class
                        t.setText(i.getTemp());//set text is to show data on the layout
                        h.setText(i.getHumidity());
                        p.setText(i.getPump());
                        d.setText(i.getDate());
                        m.setText(i.getUser_msg());

                    }

            }

            @Override
            public void onFailure(Call<List<water_system>> call, Throwable t) {
              //this function in case there is error when we communicate it with the server
                /* is a super class of exception and errors*/
                Log.e("tag", t.getMessage());
                //print the error message in the logcat in android studio "programmer purposes"

            }
        });
    }

   private void getInfo1(){
     /*   Call<List<water_system>> call= jsonPlaceHolderAPI.getInfo1(1);
        call.enqueue(new Callback<List<water_system>>() {
            @Override
            public void onResponse(Call<List<water_system>> call, Response<List<water_system>> response) {
                List<water_system> sysInfo = response.body();
                if (!response.isSuccessful()) {
                    d.setText("Code: "+ response.code());
                    return;
                }
                else {
                    for (water_system i : sysInfo) {
                        //  "ID"+i.getId() + "\n"+
                        l.setText(i.getLevel());
                        t.setText(i.getTemp());
                        h.setText(i.getHumidity());
                        p.setText(i.getPump());
                        d.setText(i.getDate());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<water_system>> call, Throwable t) {
                Log.e("tag", t.getMessage());

            }
        });*/
    }
    private void createWaterSystem(){
      /*  water_system w= new water_system("32","32","2","0");
        Call<water_system> call = jsonPlaceHolderAPI.createWaterSystem(w);
        call.enqueue(new Callback<water_system>() {
            @Override
            public void onResponse(Call<water_system> call, Response<water_system> response) {
                if (!response.isSuccessful()) {
                    d.setText("Code: "+ response.code());
                    return;
                }
                else {
                    water_system sysInfo = response.body();


                        //  "ID"+i.getId() + "\n"+
                        l.setText(sysInfo.getLevel());
                        t.setText(sysInfo.getTemp());
                        h.setText(sysInfo.getHumidity());
                        p.setText(sysInfo.getPump());
                        d.setText("Cod: "+response.code());
                       // d.setText(sysInfo.getDate());

                }
            }

            @Override
            public void onFailure(Call<water_system> call, Throwable t) {
                Log.e("tag", t.getMessage());

            }
        });*/
    }
    private void updatePump(final String p1){
        String msg=usermsg.toString();
        water_system w =new water_system("15","18","45","1",msg);
        Call<water_system> call =jsonPlaceHolderAPI.patchPump(1,w);
        call.enqueue(new Callback<water_system>() {
            @Override
            public void onResponse(Call<water_system> call, Response<water_system> response) {
                water_system sysInfo = response.body();

                    l.setText(sysInfo.getLevel());//methods declared in our water_system java class
                    t.setText(sysInfo.getTemp());//set text is to show data on the layout
                    h.setText(sysInfo.getHumidity());
                    p.setText(sysInfo.getPump());
                    d.setText(sysInfo.getDate());
                    m.setText(sysInfo.getUser_msg());

            }

            @Override
            public void onFailure(Call<water_system> call, Throwable t) {
                Log.e("tag", t.getMessage());

            }
        });

    }

}
