package com.example.pedapp.Database;

public class Constant {
    /**
     * Basic setting, what's the database name,and how many table has => that is the version
     */
    public static final String DATABASE_NAME = "MovieDB";
    public static final int DATABASE_VERSION = 4;

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

    /**
     * test table which contains 8 column
     */
    public static final String TEST_TABLE_NAME = "test";
    public static final String TEST_COLUMN_ID = "id";
    public static final String TEST_COLUMN_GROUPNAME = "groupname";
    public static final String TEST_COLUMN_QUESTION = "question";
    public static final String TEST_COLUMN_ANSWEAR1 = "answear1";
    public static final String TEST_COLUMN_ANSWEAR2 = "answear2";
    public static final String TEST_COLUMN_ANSWEAR3 = "answear3";
    public static final String TEST_COLUMN_ANSWEAR4 = "answear4";
    public static final String TEST_COLUMN_CORRECTANSWEAR = "correctanswear";

    public static final String CREATE_TABLE_TEST = "CREATE TABLE "
            + TEST_TABLE_NAME + "(" + TEST_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TEST_COLUMN_GROUPNAME + " TEXT,"
            + TEST_COLUMN_QUESTION + " TEXT,"
            + TEST_COLUMN_ANSWEAR1 + " TEXT,"
            + TEST_COLUMN_ANSWEAR2 + " TEXT,"
            + TEST_COLUMN_ANSWEAR3 + " TEXT,"
            + TEST_COLUMN_ANSWEAR4 + " TEXT,"
            + TEST_COLUMN_CORRECTANSWEAR + " TEXT );";
}
