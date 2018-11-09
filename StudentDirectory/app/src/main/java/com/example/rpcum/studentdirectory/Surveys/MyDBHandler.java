package com.example.rpcum.studentdirectory.Surveys;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class MyDBHandler extends SQLiteOpenHelper {

    //information about DB
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "datingApp.db";
    private static final String TABLE_NAME1 = "generalInfo";
    private static final String TABLE_NAME2 = "surveyInfo";


    public MyDBHandler (Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        SQLiteDatabase.create(factory);
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

        createPersonalTable(db);
        db.execSQL(CREATE_TABLE1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    //this function will create the new personal table filled with the data gotten from the survey
    //new user took when he/she registered
    public void createPersonalTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE  surveyInfo (\n" +
                "    username    STRING REFERENCES generalInfo (username) ON DELETE CASCADE\n" +
                "                                                         ON UPDATE CASCADE,\n" +
                "    read        STRING,\n" +
                "    movies      STRING,\n" +
                "    hookup      STRING,\n" +
                "    sports      STRING,\n" +
                "    workout     STRING,\n" +
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
    public void addNewUserHandler(StudentGeneral studentG) {


        String ADD_ROW = "INSERT INTO " + TABLE_NAME1 + " VALUES (" +
                null + "," + null + "," + studentG.getFirstName() + "," +
                studentG.getGender() + "," + studentG.getAge() + "," +
                studentG.getPhoneNumber() + "," + studentG.getEmail() + ");";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(ADD_ROW);
        addPersonalRow(db, studentG.getUsername());

        db.close();

    }

    //this function will update the information in the row of the general info table for the given student
    public void updateGeneralSurvey(StudentGeneral studentG) {}

    //this function will update the personal survey table
    public void updatePersonalSurvey(StudentPersonal studentP) {

        SQLiteDatabase db = this.getWritableDatabase();

        //insert survey info into db
        String UPDATE_SURVEY = "Update surveyInfo set read = " +studentP.getRead() + "";



    }

    //search for a username
    public boolean checkUsernameValid(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "Select count(*) from generalInfo where username = " + name + ";";
        Cursor cursor = db.rawQuery(QUERY,null);
        int count = cursor.getCount();

        if (count > 0) {
            db.close();
            return false;
        }
        else {
            db.close();
            return true;
        }
    }

    public void addPersonalRow(SQLiteDatabase db, String username) {
        String FILL_TABLE_WITH_USERNAME = "Insert into surveyInfo VALUES(" + username
                + ",null,null,null,null,null,null,null,null,null,null,null";

        db.execSQL(FILL_TABLE_WITH_USERNAME);

    }


    public boolean DBattemptLogin(String username, String pwd) {

        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "Select count(*) from generalInfo where username = " + username + " and pwd = " + pwd + ";";
        Cursor cursor = db.rawQuery(QUERY,null);
        int count = cursor.getCount();

        if (count > 0) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }





}