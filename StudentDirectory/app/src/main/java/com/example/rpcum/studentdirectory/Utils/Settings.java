package com.example.rpcum.studentdirectory.Utils;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.MainScreens.LoginPage;
import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.MainScreens.Homepage;
import com.example.rpcum.studentdirectory.Surveys.PersonalProfileSurvey;
import com.example.rpcum.studentdirectory.Surveys.GeneralUserSurvey;
import com.example.rpcum.studentdirectory.Surveys.UpdateGeneralSurvey;

public class Settings extends AppCompatActivity {

    SharedPreferences sp;

    private Button updateProfileButton, updateGeneralButton, logoutButton;
    private Intent intent;
    private ActionBar actionBar;
    private int itemID;
    private View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
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

    private void init() {
        setupActionBar();
        setupButtons();
        sp  = getSharedPreferences("loggedIn", MODE_PRIVATE);
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle("");
    }

    private void setupButtons() {
        updateProfileButton = getWindow().getDecorView().findViewById(R.id.update_personal_button);
        updateGeneralButton =  getWindow().getDecorView().findViewById(R.id.update_general_button);
        logoutButton =  getWindow().getDecorView().findViewById(R.id.logout_button);

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), PersonalProfileSurvey.class);
                startActivity(intent);
            }
        });

        updateGeneralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //UpdateGeneralSurvey
                intent = new Intent(getApplicationContext(), GeneralUserSurvey.class);
                intent.putExtra("FROMSETTINGS", true);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("loggedIn", false);
                editor.putString("username", "");
                editor.apply();

                intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);

            }
        });
    }
}
