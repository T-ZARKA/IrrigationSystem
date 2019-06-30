package com.example.android.irrigationsystem;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOperations extends SQLiteOpenHelper{
    static final String DATABASE_NAME="WaterSys.db";
    static final int DATABASE_VERSION=1;
    static  final String TABLE_NAME="water_system";
    static  final String COLUMN_ID="id";
    static  final String COLUMN_LEVEL="level";
    static  final String COLUMN_TEMP="temp";
    static  final String COLUMN_HUMIDITY="humidity";
    static  final String COLUMN_PUMP="pump";
    static  final String COLUMN_DATE="red-date";
    /*we create the above finals to make changes easier,
    for example if we change filenumber to be fNumber.
     * this is done directly by changing the value of COLUMN_FILENUMBER without
     * make changes to queries  .
     * ( try to change a column name without using these finals and see how many queries you must modify!!!)*/
    SQLiteDatabase Rdb=getReadableDatabase();
    SQLiteDatabase Wdb=getWritableDatabase();
    /*
     * getWritableDatabase() used with queries that edit database like insert into, delete, update
     *getReadableDatabase() used with queries that DO NOT edit database like select
     * */



    public DBOperations( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String q="create table "+TABLE_NAME+"("+COLUMN_ID + " integer primary key autoincrement ,"  +

                COLUMN_LEVEL + " text,"+ COLUMN_TEMP + "text,"+ COLUMN_HUMIDITY+ " text" +
                COLUMN_PUMP + "text"+ COLUMN_DATE + "text" + ");";

        db.execSQL(q);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String q1="drop table "+TABLE_NAME+";";
        db.execSQL(q1);
        onCreate(db);




    }

    void addInfo(water_system w){
        String myQuery="insert into "+TABLE_NAME+
                "("+ COLUMN_LEVEL+","+ COLUMN_TEMP+","+COLUMN_HUMIDITY+ ","+ COLUMN_PUMP + "," + COLUMN_DATE + ")"+
                "values(" +"\""+w.getLevel()+"\""+ ","+ w.getTemp()+","+w.getHumidity()+ ","
                + w.getPump() +","+w.getDate() +");" ;
        Wdb.execSQL(myQuery);


    }
    water_system searchbyId(int i){
        String q="select * from " +TABLE_NAME+" where "+
                COLUMN_ID+"="+i+";";
        Cursor c=Rdb.rawQuery(q,null);
        water_system w1=null;
        if(c.moveToFirst())
        // if there is data, moveToFirst() moves cursor to first row  and returns true , if there is no data it returns false

        {
           // String n=c.getString(1);
            String l=c.getString(c.getColumnIndex(COLUMN_LEVEL));
            String t=c.getString(c.getColumnIndex(COLUMN_TEMP));
            String h=c.getString(c.getColumnIndex(COLUMN_HUMIDITY));
            String p=c.getString(c.getColumnIndex(COLUMN_PUMP));
            String d=c.getString(c.getColumnIndex(COLUMN_DATE));

           w1=new water_system(l,t,h,p,d);
        }
        return w1;

    }
    void update(water_system w){
        String myQuery="update "+TABLE_NAME+" set "+
                COLUMN_LEVEL+"="+"\""+w.getLevel()+"\""+","+
                COLUMN_TEMP+"="+w.getTemp()+","+
                COLUMN_HUMIDITY+"="+w.getHumidity()+
                COLUMN_PUMP+"="+w.getPump()+
                COLUMN_DATE+"="+w.getDate()+   " where "+COLUMN_ID + "=" + 1 + ";";

        Wdb.execSQL(myQuery);

    }

  /*  ArrayList<String> namesForInsurancegreaterthanorequal2() {
        String q="select "+COLUMN_NAME+ " FROM "+TABLE_NAME+
                " WHERE "+ COLUMN_INSURANCEDEGREE+">=2;";

        Cursor myCursor= Rdb.rawQuery(q,null);
        ArrayList<String>a=new ArrayList<String>();

        myCursor.moveToFirst();

        while(!myCursor.isAfterLast())
        {
            a.add(myCursor.getString(myCursor.getColumnIndex(COLUMN_NAME)));
            myCursor.moveToNext();
        }
        return a;
    }*/

    void deleteALL(){
        String query=" delete from "+TABLE_NAME +";";

        Wdb.execSQL(query);

    }
}
