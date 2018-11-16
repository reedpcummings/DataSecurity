package com.example.rpcum.studentdirectory.Surveys;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.MainScreens.Homepage;
import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.Settings;

import java.util.ArrayList;

public class PersonalProfileSurvey extends AppCompatActivity {



    private Spinner readSpinner, moviesSpinner, hookupSpinner, sportsSpinner, workoutSpinner,
            hikingSpinner, religiousSpinner, socialSpinner, drinkSpinner, smokeSpinner, musicSpinner;
    private Button submitButton;
    private ActionBar actionBar;
    private Intent intent;
    SharedPreferences sp;
    public String[] wants = new String[11];
    public int[] wantsValues = new int[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_survey);
        init();
    }

    private void init() {
        setupActionBar();
        setupSpinners();
        setupSubmitButton();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }

    private void setupSpinners() {
        readSpinner = findViewById(R.id.read_spinner);
        moviesSpinner = findViewById(R.id.movies_spinner);
        hookupSpinner = findViewById(R.id.hookup_spinner);
        sportsSpinner = findViewById(R.id.sports_spinner);
        workoutSpinner = findViewById(R.id.workout_spinner);
        hikingSpinner = findViewById(R.id.hiking_spinner);
        religiousSpinner = findViewById(R.id.religious_spinner);
        socialSpinner = findViewById(R.id.social_spinner);
        drinkSpinner = findViewById(R.id.drink_spinner);
        smokeSpinner = findViewById(R.id.smoke_spinner);
        musicSpinner = findViewById(R.id.music_spinner);
    }

    private void setupSubmitButton(){
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            sp = getSharedPreferences("loggedIn",MODE_PRIVATE);

            if(!(sp.getBoolean("search",false))) {
                addStudentP();
                intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
            else {
                runSearch();
                intent = new Intent(getApplicationContext(), SearchResults.class);
                intent.putExtra("wants",wants);
                intent.putExtra("wantValues", wantsValues);
                intent.putExtra("username",sp.getString("username",""));
                startActivity(intent);

            }
            }
        });
    }


    public void addStudentP() {
        //sets up db handler
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);
        SharedPreferences sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
        //creating a new student with general info from the values in the fields
        StudentPersonal studentP = new StudentPersonal();
        studentP.setUsername(sp.getString("username",""));
        studentP.setRead(readSpinner.getSelectedItem().toString());
        studentP.setMovies(moviesSpinner.getSelectedItem().toString());
        studentP.setHookup(hookupSpinner.getSelectedItem().toString());
        studentP.setSports(sportsSpinner.getSelectedItem().toString());
        studentP.setWorkout(workoutSpinner.getSelectedItem().toString());
        studentP.setHiking(hikingSpinner.getSelectedItem().toString());
        studentP.setReligious(religiousSpinner.getSelectedItem().toString());
        studentP.setSocialMedia(socialSpinner.getSelectedItem().toString());
        studentP.setDrink(drinkSpinner.getSelectedItem().toString());
        studentP.setSmoke(smokeSpinner.getSelectedItem().toString());
        studentP.setMusic(musicSpinner.getSelectedItem().toString());

        //adds the student info to the db
        dbHandler.updatePersonalSurvey(studentP);

    }

    public void runSearch(){

//        String[] wants = {readSpinner.getSelectedItem().toString(),moviesSpinner.getSelectedItem().toString(),
//            hookupSpinner.getSelectedItem().toString(),sportsSpinner.getSelectedItem().toString(),
//            workoutSpinner.getSelectedItem().toString(),hikingSpinner.getSelectedItem().toString(),
//            religiousSpinner.getSelectedItem().toString(),socialSpinner.getSelectedItem().toString(),
//            drinkSpinner.getSelectedItem().toString(),smokeSpinner.getSelectedItem().toString(),
//            musicSpinner.getSelectedItem().toString()};

        wants[0] = readSpinner.getSelectedItem().toString();
        wants[1] = moviesSpinner.getSelectedItem().toString();
        wants[2] = hookupSpinner.getSelectedItem().toString();
        wants[3] = sportsSpinner.getSelectedItem().toString();
        wants[4] = workoutSpinner.getSelectedItem().toString();
        wants[5] = hikingSpinner.getSelectedItem().toString();
        wants[6] = religiousSpinner.getSelectedItem().toString();
        wants[7] = socialSpinner.getSelectedItem().toString();
        wants[8] = drinkSpinner.getSelectedItem().toString();
        wants[9] = smokeSpinner.getSelectedItem().toString();
        wants[10] = musicSpinner.getSelectedItem().toString();


        wantsValues = new int[wants.length];
        for(int i = 0; i < wants.length; i++) {
            if(wants[i].equals("Regularly")) {
                wantsValues[i] = 4;
            }
            else if(wants[i].equals("Sometimes")) {
                wantsValues[i] = 3;
            }
            else if(wants[i].equals("Rarely")) {
                wantsValues[i] = 2;
            }
            else if(wants[i].equals("Never")) {
                wantsValues[i] = 1;
            }
        }


//        ArrayList<String[]> results;
//        results = dbHandler.searchForBestMatch(wants, wantsValues,sp.getString("username",""));
//        return results;
    }

}
