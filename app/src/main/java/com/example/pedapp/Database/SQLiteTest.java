package com.example.pedapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteTest {

    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase database;
    private static final String TAG = "MovieDBSQLiteDatabaseTest";

    public SQLiteTest(Context context) {
        this.context = context;
        dbHelper = new MyDBHelper(context);
    }

    @SuppressLint("LongLogTag")
    public boolean insertTest(String groupname, String question,
                              String answear1, String answear2,
                              String answear3, String answear4,
                              String correctAnswear){
        try{
            database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.TEST_COLUMN_GROUPNAME, groupname);
            contentValues.put(Constant.TEST_COLUMN_QUESTION, question);
            contentValues.put(Constant.TEST_COLUMN_ANSWEAR1, answear1);
            contentValues.put(Constant.TEST_COLUMN_ANSWEAR2, answear2);
            contentValues.put(Constant.TEST_COLUMN_ANSWEAR3, answear3);
            contentValues.put(Constant.TEST_COLUMN_ANSWEAR4, answear4);
            contentValues.put(Constant.TEST_COLUMN_CORRECTANSWEAR, correctAnswear);
            long result = database.insert(Constant.TEST_TABLE_NAME,
                    null, contentValues);
            if (result > 0){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
        return false;
    }

    @SuppressLint("LongLogTag")
    public void getAllTest(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+ Constant.TEST_TABLE_NAME,
                null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            Log.d(TAG, res.getString(res.getColumnIndex("groupname")));
            Log.d(TAG, res.getString(res.getColumnIndex("question")));
            Log.d(TAG, res.getString(res.getColumnIndex("correctanswear")));
            res.moveToNext();
        }
    }

    public ArrayList<String> getAllGroupName(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res = db.rawQuery( "select distinct groupname from "+ Constant.TEST_TABLE_NAME,
                null );

        res.moveToFirst();
        ArrayList<String> groupNames = new ArrayList<>();
        while(res.isAfterLast() == false) {
            Log.d(TAG, res.getString(res.getColumnIndex("groupname")));
            groupNames.add(res.getString(res.getColumnIndex("groupname")));
            res.moveToNext();
        }
        return groupNames;
    }

}
