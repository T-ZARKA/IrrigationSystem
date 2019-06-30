package com.example.android.irrigationsystem;

public class water_system {
    private int id;
    private String temp;
    private String level;
    private String humidity;
    private String pump;
    private String date;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public String getPump() {
        return pump;
    }

    public void setPump(String pump) {
        this.pump = pump;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public water_system(String level, String temp, String humidity, String pump,String date) {
        this.level = level;
        this.temp = temp;
        this.humidity = humidity;
        this.pump = pump;
        this.date = date;
    }


}
