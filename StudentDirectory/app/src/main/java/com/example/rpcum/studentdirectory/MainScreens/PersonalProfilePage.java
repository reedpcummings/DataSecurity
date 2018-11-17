package com.example.rpcum.studentdirectory.MainScreens;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.MyDBHandler;

public class PersonalProfilePage extends AppCompatActivity {

    SharedPreferences sp;
    private ActionBar actionBar;
    private int itemID;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        TextView firstName = findViewById(R.id.profile_firstName);
        TextView gender = findViewById(R.id.profile_gender);
        TextView age = findViewById(R.id.profile_age);
        TextView phoneNumber = findViewById(R.id.profile_phoneNumber);
        TextView email = findViewById(R.id.profile_email);
        TextView username = findViewById(R.id.profile_username);

        sp = getSharedPreferences("loggedIn",MODE_PRIVATE);

        if(sp.getBoolean("loggedIn",false)) {
            MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

            String[] profileInfo;
            profileInfo = dbHandler.loadPersonalProfile(sp.getString("username", ""));

            firstName.setText("First Name:  " + profileInfo[0]);
            gender.setText("Gender:  " + profileInfo[1]);
            age.setText("Age:  " + profileInfo[2]);
            phoneNumber.setText("Phone Number:  " + profileInfo[3]);
            email.setText("Email:  " + profileInfo[4]);
            username.setText("Username:  " + profileInfo[5]);

        }

        else {
            firstName.setText("not logged in");
        }

        //firstName.setText("not logged in");
        init();
    }

    private void init() {
        setupActionBar();
    }


    private void setupActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemID = item.getItemId();

        if(itemID == android.R.id.home){
            intent = new Intent(getApplicationContext(), Homepage.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
