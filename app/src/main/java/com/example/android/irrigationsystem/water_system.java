package com.example.android.irrigationsystem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this class represent our system since we have temp,level....,
// we need to create it so when the api convert from gson object to java object
// we can do actions on each object
public class water_system {
    @Expose
    @SerializedName("id")private int id; // the name of the attributes must match the columns names in te database
    @Expose
    @SerializedName("level")private String level;
    @Expose
    @SerializedName("temp")private String temp;
    @Expose
    @SerializedName("humidity")private String humidity;
    @Expose
    @SerializedName("pump")private String pump;
    @Expose
    @SerializedName("user_msg")private String user_msg;
    @Expose
    @SerializedName("reg_date")private String reg_date;

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setUser_msg(String user_msg) {
        this.user_msg = user_msg;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUser_msg() {
        return user_msg;
    }


    public int getId() {
        return id;
    } // to return the id

    public String getTemp() {
        return temp;
    }

    public String getLevel() {
        return level;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDate() { return reg_date;}

    public String getPump() {
        return pump;
    }

    public void setPump(String p) {
        pump = p;
    }

    public water_system(String l, String t, String h, String p,String um) {
        level = l;
        temp = t;
        humidity = h;
       pump = p;
       user_msg = um;
    }
}
