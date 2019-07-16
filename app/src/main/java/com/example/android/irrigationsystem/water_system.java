package com.example.android.irrigationsystem;
//this class represent our system since we have temp,level....,
// we need to create it so when the api convert from gson object to java object
// we can do actions on each object
public class water_system {
    private Integer id; // the name of the attributes must match the columns names in te database
    private String level;
    private String temp;
       private String humidity;
    private String pump;
    private String user_msg;

    public String getUser_msg() {
        return user_msg;
    }

    private String reg_date;
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
