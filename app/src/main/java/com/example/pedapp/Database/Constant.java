package com.example.pedapp.Database;

public class Constant {
    /**
     * Basic setting, what's the database name,and how many table has => that is the version
     */
    public static final String DATABASE_NAME = "MovieDB";
    public static final int DATABASE_VERSION = 3;

    /**
     * Pofile table, which has 5 column
     */
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_COLUMN_ID = "id";
    public static final String PROFILE_COLUMN_USERNAME = "username";
    public static final String PROFILE_COLUMN_EMAIL = "email";
    public static final String PROFILE_COLUMN_STATUS = "status";
    public static final String PROFILE_COLUMN_PASSWORD = "password";

    /**
     * SQL query how to create profile table and columns
     */
    public static final String CREATE_TABLE_PROFILE = "CREATE TABLE "
            + PROFILE_TABLE_NAME + "(" + PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PROFILE_COLUMN_USERNAME + " TEXT,"
            + PROFILE_COLUMN_EMAIL + " TEXT,"
            + PROFILE_COLUMN_STATUS + " TEXT,"
            + PROFILE_COLUMN_PASSWORD + " TEXT );";


}
