package com.example.pedapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

}
