package com.example.rpcum.studentdirectory.Utils;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.MainScreens.Homepage;
import com.example.rpcum.studentdirectory.Surveys.PersonalProfileSurvey;
import com.example.rpcum.studentdirectory.Surveys.GeneralUserSurvey;

public class Settings extends AppCompatActivity {

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
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), GeneralUserSurvey.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**IMPLEMENT METHOD TO LOGOUT OF APPLICATION **/

                /**NEEDS TO RETURN TO LOGIN PAGE(LOGINACTIVITY) UPON COMPLETION **/

                /**TESTING**/
                Toast.makeText(getApplicationContext(), "It Works!", Toast.LENGTH_LONG).show();

            }
        });
    }
}
