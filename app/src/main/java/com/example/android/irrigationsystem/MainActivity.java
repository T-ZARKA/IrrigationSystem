package com.example.android.irrigationsystem;
//we implemented retrofit library and gson converter library in the gradle
//we set user permission for the internet in the manifest

import android.app.Dialog;
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
    TextView l, t, h, p, d, m;
    Button on, off, enter;
    EditText usermsg;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState)// here where it all starts
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //to connect with the layout we give
        // each variable the id of what we want to connect with from the layout
        final Dialog dialog; //to show the waning window if user didn't fill the user message
        l = findViewById(R.id.l_tv);//connect the layout with my coding
        t = findViewById(R.id.t_tv);
        h = findViewById(R.id.h_tv);
        p = findViewById(R.id.pumb_tv);
        d = findViewById(R.id.d_tv);
        on = findViewById(R.id.on);
        off = findViewById(R.id.off);
        usermsg = findViewById(R.id.usermsgET);
        m = findViewById(R.id.message);
        enter = findViewById(R.id.enter);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//activate when user click on  "ON Button"
                updatePump("1"); //send one to the function to turn the pump on

            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//activate when user click on  "OFF Button"
                updatePump("0");//send 0 to the function to turn the pump off
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//activate when user click on  "ENTER Button" to enter the msg
                String msg;
                msg = usermsg.getText().toString();//get the message from the edit text
                if (!msg.matches("")) { //in case the edit text is not empty
                    updateMsg(msg);//send the msg to updated in the database

                } else{//in case is empty show warning dialog
                    DialogMsg m = new DialogMsg();
                    m.show(getSupportFragmentManager(), "Warning");


                }

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
    //display data
    private void getInfo() {
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

    //update all attributes for a specified id
    private void update(final String p1) {
        String msg;
        if (!usermsg.toString().isEmpty()) {
            msg = usermsg.toString();
        } else
            msg = null;
        Call<water_system> call = jsonPlaceHolderAPI.uP(1, "test", "test", "test", "0", msg);
        call.enqueue(new Callback<water_system>() {
            @Override
            public void onResponse(Call<water_system> call, Response<water_system> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getInfo();
                }

            }

            @Override
            public void onFailure(Call<water_system> call, Throwable t) {
                Log.e("TAG", t.getMessage());


            }
        });


    }

    //update the pump only for a specified id
    private void updatePump(final String p1){
        Call<water_system> call=jsonPlaceHolderAPI.updatePump(1,p1);//call the function in the API and send the pump value
        call.enqueue(new Callback<water_system>() {//execute
            @Override
            public void onResponse(Call<water_system> call, Response<water_system> response) {
                if (response.isSuccessful() && response.body() != null) {//if execution is successful
                    getInfo();//display updated the data
                }
            }

            @Override
            public void onFailure(Call<water_system> call, Throwable t) {//if execution is not successful
                Log.e("TAG",t.getMessage());//show the error

            }
        });


    }

    //update the user_msg only for a specified id
    private void updateMsg(String msg){
        Call<water_system> call=jsonPlaceHolderAPI.updateMsg(1,msg);//call the function in the API and send the pump value
        call.enqueue(new Callback<water_system>() {//execute

            @Override
            public void onResponse(Call<water_system> call, Response<water_system> response) {
                if (response.isSuccessful() && response.body() != null) {//if execution is successful
                    getInfo();//display updated data
                }
            }

            @Override
            public void onFailure(Call<water_system> call, Throwable t) {//if execution is not successful
                Log.e("TAG",t.getMessage());//show the error

            }
        });

    }

}
