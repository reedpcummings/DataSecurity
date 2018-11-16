package com.example.rpcum.studentdirectory.Surveys;

import android.content.SharedPreferences;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/*
This class is our DBHandler class. It access the database and creates and updates tables, as well as
pulls data for screen loading.
*/

public class MyDBHandler extends SQLiteOpenHelper {

    //information about DB
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "datingApp3.db";
    private static final String TABLE_NAME1 = "generalInfo";
    private SharedPreferences sp;


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
                "    age         STRING,\n" +
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

    //this function will load the info to the screen for the personal profile activity
    public String[] loadPersonalProfile(String username) {

        return new String[] {getFirstName(username),getGender(username),getAge(username),
                getPhone(username), getEmail(username),getUsername(username) };

    }

    //this function will add the new user to the existing table above(public table) and then create a new entry
    //for the new user's survey responses. it adds just the username so we can update later based on username
    public void addNewUserHandler(StudentGeneral studentG) {


        String ADD_ROW = "INSERT INTO " + TABLE_NAME1 + " VALUES (\"" +
                studentG.getUsername() + "\",\"" + studentG.getPwd() + "\",\"" + studentG.getFirstName() + "\",\"" +
                studentG.getGender() + "\",\"" + studentG.getAge() + "\",\"" +
                studentG.getPhoneNumber() + "\",\"" + studentG.getEmail() + "\");";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(ADD_ROW);
        addPersonalRow(db, studentG.getUsername());

        db.close();

    }

    //this function will update the information in the row of the general info table for the given student
    public void updateGeneralSurvey(StudentGeneral studentG) {
        SQLiteDatabase db = this.getWritableDatabase();

        //insert survey info into db
        String UPDATE_SURVEY = "Update generalInfo set username = \"" + studentG.getUsername() +
                "\", firstName = \"" + studentG.getFirstName() + "\", pwd = \"" + studentG.getPwd() +
                "\",  gender = \"" + studentG.getGender() + "\", age = \"" + studentG.getAge() +
                "\", phoneNumber = \"" + studentG.getPhoneNumber() + "\", email = \"" + studentG.getEmail() +
                "\" where username =  \"" + studentG.getUsername() + "\"";

        db.execSQL(UPDATE_SURVEY);

        db.close();
    }

    //this function will update the personal survey table
    public void updatePersonalSurvey(StudentPersonal studentP) {

        SQLiteDatabase db = this.getWritableDatabase();

        //insert survey info into db
        String UPDATE_SURVEY = "Update surveyInfo set read = \"" +studentP.getRead() +
                "\", movies = \"" + studentP.getMovies() + "\", hookup = \"" + studentP.getHookup() +
                "\", sports = \"" + studentP.getSports() + "\", workout = \"" + studentP.getWorkout() +
                "\", hiking = \"" + studentP.getHiking() + "\", religious = \"" + studentP.getReligious() +
                "\", socialMedia = \"" + studentP.getSocialMedia() + "\", drink = \"" + studentP.getDrink() +
                "\", smoke = \"" + studentP.getSmoke() + "\", music = \"" + studentP.getMusic() +
                "\" where username =  \"" + studentP.getUsername() + "\"";

        db.execSQL(UPDATE_SURVEY);

        db.close();
    }

    //search for a username
    public boolean checkUsernameValid(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "Select count(*) from generalInfo where username = \"" + name + "\";";
        Cursor cursor = db.rawQuery(QUERY,null);
        int count = cursor.getCount();
        cursor.close();

        if (count > 0) {
            db.close();
            return false;
        }
        else {
            db.close();
            return true;
        }
    }

    //adds the personal row upon account creation
    public void addPersonalRow(SQLiteDatabase db, String username) {
        String FILL_TABLE_WITH_USERNAME = "Insert into surveyInfo (username) VALUES(\"" + username
                + "\")"; /*+ ",null,null,null,null,null,null,null,null,null,null,null";*/

        db.execSQL(FILL_TABLE_WITH_USERNAME);

    }

    //attempt login function that pulls info from DB and tests what the user
    //entered against what is in the DB. We store the hash of the password for added security
    public boolean DBattemptLogin(String username, String pwd) {

        SQLiteDatabase db = this.getReadableDatabase();

        //creates the query
        String QUERY = "Select username from generalInfo where username = \"" + username + "\" and pwd = \"" + pwd + "\";";

        //creates a cursor for result manipulation
        Cursor cursor = db.rawQuery(QUERY,null);

        //moves cursor to beginning so we can manipulate the data how we would like
        cursor.moveToFirst();

        int count = cursor.getCount();
        cursor.close();

        //if the query returns anything, then it is a match. thats why we use count.
        if (count > 0) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }


    //the next few functions are methods to get the general user info needed for profile loading
    //as well as match loading

    public String getFirstName(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String fname = cursor.getString(0);
        cursor.close();
        return fname;
    }

    public String getGender(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String gender = cursor.getString(1);
        cursor.close();
        return gender;

    }

    public String getAge(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String age = cursor.getString(2);
        cursor.close();
        return age;
    }

    public String getPhone(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String phone = cursor.getString(3);
        cursor.close();
        return phone;
    }

    public String getEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String email = cursor.getString(4);
        cursor.close();
        return email;
    }

    public String getUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String GET_USERNAME = "Select firstName, gender, age, phoneNumber, " +
                "email, username from generalInfo where username = \""
                + username + "\"";
        Cursor cursor = db.rawQuery(GET_USERNAME,null);
        cursor.moveToFirst();

        String uname = cursor.getString(5);
        cursor.close();
        return uname;
    }

    //debugging functions

//    public void deleteContents() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String delete = "Drop Table generalInfo";
//        db.execSQL(delete);
//    }
//
//    public void createTable() {
//        String CREATE_TABLE1 = "CREATE TABLE generalInfo (\n" +
//                "    username    STRING  PRIMARY KEY ON CONFLICT FAIL\n" +
//                "                        UNIQUE,\n" +
//                "    pwd         STRING  UNIQUE,\n" +
//                "    firstName   STRING,\n" +
//                "    gender      STRING,\n" +
//                "    age         STRING,\n" +
//                "    phoneNumber STRING,\n" +
//                "    email       STRING\n" +
//                ");\n";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        createPersonalTable(db);
//        db.execSQL(CREATE_TABLE1);
//    }


    //seaerches for matches based on the following algorithm:

    /*
    We take each answer and assign a value to it. Regularly = 4 and Never = 1. We then find
    the difference between each entry in the table and compare the values. We do this by finding the
    difference between the two numbers. If searcher wants some that reads = 4, and entry read = 2,
    the difference 2 will be added to a running total. We use this running total to determine who
    is a better match, with the lower total being a better match.
     */

    public ArrayList<String[]> searchForBestMatch(String[] wants, int[] wantsValues, String username){
        //do search
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String[]> studentPersonalArrayList = new ArrayList<>();
        int index;

        //setting up query
        String getAll = "select * from surveyInfo except select * from surveyInfo where username = \"" + username + "\"";

        //setting up cursor and positioning
        Cursor cursor = db.rawQuery(getAll, null);
        cursor.moveToFirst();


        //this is added each row, as a string array, into an array list for use. the first if statement
        //is a test to see if the member has filled out there survey yet. if not, we skip them.
        do{
            if(cursor.getString(2) == null){continue;}
            String[] temp = new String[cursor.getColumnCount()+1];
            for(index = 0; index < cursor.getColumnCount(); index++) {
                temp[index] = cursor.getString(index);
            }
            studentPersonalArrayList.add(temp);
        }
        while(cursor.moveToNext());


        //this is substituting the words for the values for finding the rating
        for(String[] results: studentPersonalArrayList){
            for(index = 0; index < results.length-1; index++) {
                if(results[index] == null) {
                    continue;
                }
                else if(results[index].equals("Regularly")) {
                    results[index] = "4";
                }
                else if(results[index].equals("Sometimes")) {
                    results[index] = "3";
                }
                else if(results[index].equals("Rarely")) {
                    results[index] = "2";
                }
                else if(results[index].equals("Never")) {
                    results[index] = "1";
                }
            }

        }


        //this does the actual comparing
        int compareIndex;
        int difference = 0;
        for(String[] compare: studentPersonalArrayList){
            if(compare[2] == null) {
                continue;
            }
            else {
                for (compareIndex = 1; compareIndex < compare.length -2; compareIndex++) {
                    difference = difference + Math.abs(wantsValues[compareIndex] - Integer.parseInt(compare[compareIndex]));

                }
                String rating = String.valueOf(difference);
                compare[compare.length - 1] = rating;
                difference = 0;
            }
        }

        return studentPersonalArrayList;  //returns the array list so we can load what we need

        //studentPersonalArrayList = sort(studentPersonalArrayList);

    }

    //our home made hashing function
    public String hashPwd(String arg) {

        int hash = 43;
        for (int i = 0; i < arg.length(); i++) {
            hash = hash * 179 + arg.charAt(i);
        }
        String hashPwd = String.valueOf(hash);
        return hashPwd;
    }
}