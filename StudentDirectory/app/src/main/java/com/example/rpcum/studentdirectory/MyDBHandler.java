package com.example.rpcum.studentdirectory;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {

    //information about DB
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "datingApp.db";
    private static final String TABLE_NAME1 = "generalInfo";
    private static final String TABLE_NAME2 = "surveyInfo";
    //table1
    private static final String T1_COLUMN_USERNAME = "username";
    private static final String T1_COLUMN_PWD = "pwd";
    private static final String T1_COLUMN_FIRSTNAME = "firstName";
    private static final String T1_COLUMN_GENDER = "gender";
    private static final String T1_COLUMN_AGE = "age";
    private static final String T1_COLUMN_PHONENUMNER = "phoneNumber";
    private static final String T1_COLUMN_EMAIL = "email";
    //table2
    private static final String T2_COLUMN_USERNAME  = "username";
    private static final String T2_COLUMN_READ = "read";
    private static final String T2_COLUMN_MOVIES = "movies";
    private static final String T2_COLUMN_HOOKUP = "hookup";
    private static final String T2_COLUMN_SPORTS = "sports";
    private static final String T2_COLUMN_HIKING = "hiking";
    private static final String T2_COLUMN_RELIGIOUS = "religious";
    private static final String T2_COLUMN_SOCIALMEDIA = "socialMedia";
    private static final String T2_COLUMN_DRINK = "drink";
    private static final String T2_COLUMN_SMOKE = "smoke";
    private static final String T2_COLUMN_MUSIC = "music";

    public MyDBHandler (Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = "CREATE TABLE generalInfo (\n" +
                "    username    STRING  PRIMARY KEY ON CONFLICT FAIL\n" +
                "                        UNIQUE,\n" +
                "    pwd         STRING  UNIQUE,\n" +
                "    firstName   STRING,\n" +
                "    gender      STRING,\n" +
                "    age         INTEGER,\n" +
                "    phoneNumber STRING,\n" +
                "    email       STRING\n" +
                ");\n";

        db.execSQL(CREATE_TABLE1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    //this function will create the new personal table filled with the data gotten from the survey
    //new user took when he/she registered
    public void createPersonalTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE surveyInfo (\n" +
                "    username    STRING REFERENCES generalInfo (username) ON DELETE CASCADE\n" +
                "                                                         ON UPDATE CASCADE,\n" +
                "    read        STRING,\n" +
                "    movies      STRING,\n" +
                "    hookup      STRING,\n" +
                "    sports      STRING,\n" +
                "    hiking      STRING,\n" +
                "    religious   STRING,\n" +
                "    socialMedia STRING,\n" +
                "    drink       STRING,\n" +
                "    smoke       STRING,\n" +
                "    music       STRING\n" +
                ");\n";
        db.execSQL(CREATE_TABLE);
    }

    //this function will load the info to the screen
    public String loadHandler() {return "loadHandler";}

    //this function will add the new user to the existing table above(public table) and then create a new table
    //for the new user's survey responses
    public void addNewUserHandler(StudentGeneral studentG) {}

    public void addNewPersonalSurvey(StudentPersonal studentP) {}

    //this function will update the information in the row of the general info table for the given student
    public void updateGeneralSurvey(StudentGeneral studentG) {}

    //this function will update the personal survey table
    public void updatePersonalSurvey(StudentPersonal studentP) {}

    {}





}