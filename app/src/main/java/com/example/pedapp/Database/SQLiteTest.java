package com.example.pedapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pedapp.Classes.Test;

import java.sql.Struct;
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

    public ArrayList<Test> getTestOneGroup(String groupname){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res = db.query(Constant.TEST_TABLE_NAME,
                new String[] {Constant.TEST_COLUMN_QUESTION , Constant.TEST_COLUMN_ANSWEAR1, Constant.TEST_COLUMN_ANSWEAR2, Constant.TEST_COLUMN_ANSWEAR3, Constant.TEST_COLUMN_ANSWEAR4, Constant.TEST_COLUMN_CORRECTANSWEAR},
                "groupname=?", new String[] {groupname}, null,null,null);
        res.moveToFirst();
        ArrayList<Test> tests = new ArrayList<>();
        while(res.isAfterLast() == false) {
            String question =res.getString(res.getColumnIndex("question"));
            String answear1 = res.getString(res.getColumnIndex("answear1"));
            String answear2 = res.getString(res.getColumnIndex("answear2"));
            String answear3 = res.getString(res.getColumnIndex("answear3"));
            String answear4 = res.getString(res.getColumnIndex("answear4"));
            String correctAnswear = res.getString(res.getColumnIndex("correctanswear"));
            Test test = new Test(question, answear1,answear2,answear3,answear4, correctAnswear);
            tests.add(test);
            res.moveToNext();
        }
        return tests;
    }

}
