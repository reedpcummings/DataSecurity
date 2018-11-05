package com.example.janai.contactmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Janai on 4/5/2018.
 */

//SQLiteOpenHelper Class
 class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactsManager.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_FIRST_NAME = "first";
    public static final String CONTACTS_COLUMN_MIDDLE_NAME = "middle";
    public static final String CONTACTS_COLUMN_LAST_NAME = "last";
    public static final String CONTACTS_COLUMN_NUMBER = "number";
    public static final String CONTACTS_COLUMN_BIRTH_DATE = "birth";
    public static final String CONTACTS_COLUMN_FIRST_CONTACT = "contact";
    public static final String CONTACTS_COLUMN_ADDRESS_LINE1 = "address1";
    public static final String CONTACTS_COLUMN_ADDRESS_LINE2 = "address2";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_STATE = "state";
    public static final String CONTACTS_COLUMN_ZIPCODE = "zip";


    //Constructor Calls Super
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override       //Method Creates the Database
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
                 CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY," +
                 CONTACTS_COLUMN_FIRST_NAME + " TEXT," +
                 CONTACTS_COLUMN_MIDDLE_NAME + " TEXT," +
                 CONTACTS_COLUMN_LAST_NAME + " TEXT," +
                 CONTACTS_COLUMN_NUMBER + " TEXT," +
                 CONTACTS_COLUMN_BIRTH_DATE + " TEXT," +
                 CONTACTS_COLUMN_FIRST_CONTACT + " TEXT, " +
                 CONTACTS_COLUMN_ADDRESS_LINE1 + " TEXT, " +
                 CONTACTS_COLUMN_ADDRESS_LINE2 + " TEXT, " +
                 CONTACTS_COLUMN_CITY + " TEXT, " +
                 CONTACTS_COLUMN_STATE + " TEXT, " +
                 CONTACTS_COLUMN_ZIPCODE + " TEXT)"
        );
    }

    //Discards Database and Creates Another
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    //Method Adds a Contact to Database
    public long addContact(String first, String middle, String last, String number, String birth,
                           String contact, String ad1, String ad2, String city, String state, String zip){
        SQLiteDatabase db = this.getWritableDatabase();     //Getting DB in write mode

        //Creating a set of values
        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_FIRST_NAME, first);
        values.put(CONTACTS_COLUMN_MIDDLE_NAME, middle);
        values.put(CONTACTS_COLUMN_LAST_NAME, last);
        values.put(CONTACTS_COLUMN_NUMBER, number);
        values.put(CONTACTS_COLUMN_BIRTH_DATE, birth);
        values.put(CONTACTS_COLUMN_FIRST_CONTACT, contact);
        values.put(CONTACTS_COLUMN_ADDRESS_LINE1, ad1);
        values.put(CONTACTS_COLUMN_ADDRESS_LINE2, ad2);
        values.put(CONTACTS_COLUMN_CITY, city);
        values.put(CONTACTS_COLUMN_STATE, state);
        values.put(CONTACTS_COLUMN_ZIPCODE, zip);

        //Insert row
        long rowID = db.insert(CONTACTS_TABLE_NAME, null, values);
        return rowID;
    }

    //Method Updates Contact in DB
    public void updateContact(Integer id, String first, String middle, String last, String number, String birth,
                              String contact, String ad1, String ad2, String city, String state, String zip){
        SQLiteDatabase db = this.getWritableDatabase();     //Getting DB in write mode

        //Creating a set of values
        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_FIRST_NAME, first);
        values.put(CONTACTS_COLUMN_MIDDLE_NAME, middle);
        values.put(CONTACTS_COLUMN_LAST_NAME, last);
        values.put(CONTACTS_COLUMN_NUMBER, number);
        values.put(CONTACTS_COLUMN_BIRTH_DATE, birth);
        values.put(CONTACTS_COLUMN_FIRST_CONTACT, contact);
        values.put(CONTACTS_COLUMN_ADDRESS_LINE1, ad1);
        values.put(CONTACTS_COLUMN_ADDRESS_LINE2, ad2);
        values.put(CONTACTS_COLUMN_CITY, city);
        values.put(CONTACTS_COLUMN_STATE, state);
        values.put(CONTACTS_COLUMN_ZIPCODE, zip);

        //Updating Contact According to ID
        db.update(CONTACTS_TABLE_NAME, values, "id = ? ", new String[] {Integer.toString(id)});
    }

    //Method Deletes Contact in DB
    public int deleteContact(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();     //Getting DB in write mode

        //Delete Contact According to Contact ID, Returns Int Value for Confirmation
        return db.delete(CONTACTS_TABLE_NAME, "id = ?", new String [] {Integer.toString(id)});

    }

    //Method Returns ArrayList of All Contacts in DB
    public ArrayList<ContactDetails> getContactSet(){
        SQLiteDatabase db = this.getReadableDatabase();        //Getting DB in read mode
        ArrayList<ContactDetails> contacts = new ArrayList<ContactDetails>();

        //Navigating To The Table
        Cursor c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);

        //Iterate Until After Last Result
        while(c.moveToNext()) {

            //Add Each Contacts Values to ArrayList
            contacts.add(new ContactDetails(c.getString(c.getColumnIndex(CONTACTS_COLUMN_FIRST_NAME)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_MIDDLE_NAME)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_LAST_NAME)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_NUMBER)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_BIRTH_DATE)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_FIRST_CONTACT)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_ADDRESS_LINE1)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_ADDRESS_LINE2)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_CITY)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_STATE)),
                    c.getString(c.getColumnIndex(CONTACTS_COLUMN_ZIPCODE)),
                    Integer.parseInt(c.getString(c.getColumnIndex(CONTACTS_COLUMN_ID)))));
        }

        c.close();      //release cursor resources
        return contacts;    //return arraylist of contacts
    }



}
