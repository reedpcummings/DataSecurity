package com.example.rpcum.studentdirectory.MainScreens;


import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Surveys.MyDBHandler;

public class PersonalProfilePage extends AppCompatActivity {

    SharedPreferences sp;

    private ActionBar actionBar;
    TextView firstName = (TextView)findViewById(R.id.profile_firstName);
    TextView gender = (TextView)findViewById(R.id.profile_gender);
    TextView age = (TextView)findViewById(R.id.profile_age);
    TextView phoneNumber = (TextView)findViewById(R.id.profile_phoneNumber);
    TextView email = (TextView)findViewById(R.id.profile_email);
    TextView username = (TextView)findViewById(R.id.profile_username);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        init();
    }

    private void init() {
        setupActionBar();
        setupTextViews();
    }

    private void setupTextViews() {

        if(sp.getBoolean("loggedIn",false)) {
            MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);

            String[] profileInfo;
            profileInfo = dbHandler.loadPersonalProfile(sp.getString("username", ""));

            firstName.setText(profileInfo[0]);
            gender.setText(profileInfo[1]);
            age.setText(profileInfo[2]);
            phoneNumber.setText(profileInfo[3]);
            email.setText(profileInfo[4]);
            username.setText(profileInfo[5]);

        }

        else {
            firstName.setText("not logged in");
        }

    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }
}