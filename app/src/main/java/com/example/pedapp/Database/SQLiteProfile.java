package com.example.pedapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteProfile {

    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase database;
    private static final String TAG = "MovieDBSQLiteDatabaseProfile";

    public SQLiteProfile(Context context) {
        this.context = context;
        dbHelper = new MyDBHelper(context);
    }

    /**
     * when someone register, then we insert into the database
     * @param username
     * @param email
     * @param status
     * @param password
     * @return if the return value is true, then tha insertation was successful
     */
    @SuppressLint("LongLogTag")
    public boolean insertProfile(String username, String email,
                                 String status, String password){
        try{
            database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.PROFILE_COLUMN_USERNAME, username);
            contentValues.put(Constant.PROFILE_COLUMN_EMAIL, email);
            contentValues.put(Constant.PROFILE_COLUMN_STATUS, status);
            contentValues.put(Constant.PROFILE_COLUMN_PASSWORD, password);
            long result = database.insert(Constant.PROFILE_TABLE_NAME,
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

    /**
     * you can change your username
     * @param mUsername
     * @param email
     */
    public void updateUsername(String mUsername, String email){
        database = dbHelper.getWritableDatabase();
        database.execSQL("Update " + Constant.PROFILE_TABLE_NAME + " set username =" + "'" + mUsername + "' where email = '" + email + "'" );
    }

    public void updateStatus(String mStatus, String email){
        database = dbHelper.getWritableDatabase();
        database.execSQL("Update " + Constant.PROFILE_TABLE_NAME + " set status =" + "'" + mStatus + "' where email = '" + email + "'" );
    }

    public void updatePassword(String mPassword, String email){
        database = dbHelper.getWritableDatabase();
        database.execSQL("Update " + Constant.PROFILE_TABLE_NAME + " set password =" + "'" + mPassword + "' where email = '" + email + "'" );
    }

    /**
     * loads the number of characters to your password
     * @param mEmail
     * @return - number of character with stars
     */
    @SuppressLint("LongLogTag")
    public String getPasswordLength(String mEmail){
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(Constant.PROFILE_TABLE_NAME, new String[] {Constant.PROFILE_COLUMN_PASSWORD},
                "email=?", new String[] {mEmail},
                null,null,null);
        cursor.moveToFirst();
        String returnPassword = "" ;
        while(cursor.isAfterLast() == false) {
            Log.d(TAG, cursor.getString(cursor.getColumnIndex("password")));
            returnPassword = cursor.getString(cursor.getColumnIndex("password"));
            cursor.moveToNext();
        }
        String result = "";
        for (int i = 0; i< returnPassword.length(); ++i){
            result = result + "*";
        }
        return result;
    }

    @SuppressLint("LongLogTag")
    public String getPassword(String mEmail){
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constant.PROFILE_TABLE_NAME, new String[] {Constant.PROFILE_COLUMN_PASSWORD},
                "email=?", new String[] {mEmail},
                null,null,null);
        String result = "";
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            Log.d(TAG, cursor.getString(cursor.getColumnIndex("password")));
            result = cursor.getString(cursor.getColumnIndex("password"));
            cursor.moveToNext();
        }
        return result;
    }

    public String getUsername(String value, String columnName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "select * from "+ Constant.PROFILE_TABLE_NAME,
                null );
        res.moveToFirst();
        String returnUsername = "null";
        while(res.isAfterLast() == false) {
            if (res.getString(res.getColumnIndex("email")).matches(value)){
                returnUsername = res.getString(res.getColumnIndex(columnName));
            }
            res.moveToNext();
        }
        return returnUsername;
    }

    @SuppressLint("LongLogTag")
    public void getAllProfile(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+ Constant.PROFILE_TABLE_NAME,
                null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            Log.d(TAG, res.getString(res.getColumnIndex("username")));
            Log.d(TAG, res.getString(res.getColumnIndex("email")));
            Log.d(TAG, res.getString(res.getColumnIndex("status")));
            res.moveToNext();
        }
    }

    public String getStatus(String email){
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constant.PROFILE_TABLE_NAME, new String[] {Constant.PROFILE_COLUMN_STATUS},
                "email=?", new String[] {email},
                null,null,null);
        String result = "";
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            result = cursor.getString(cursor.getColumnIndex("status"));
            cursor.moveToNext();
        }
        return result;
    }
}
