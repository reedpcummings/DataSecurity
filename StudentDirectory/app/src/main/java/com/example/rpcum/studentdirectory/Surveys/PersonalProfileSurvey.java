package com.example.rpcum.studentdirectory.Surveys;

//Created By Janai Williams: 11/2/17

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;

public class PersonalProfileSurvey extends AppCompatActivity {

    private Spinner readSpinner, moviesSpinner, hookupSpinner, sportsSpinner, workoutSpinner,
            hikingSpinner, religiousSpinner, socialSpinner, drinkSpinner, smokeSpinner, musicSpinner;
    private Button submitButton;
    private ActionBar actionBar;

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
                /** METHOD NEEDS TO SEND SAVED DATA TO DB **/

                /** IMPLEMENT: Disable submit button until all forms are filled **/

                /**NEEDS TO RETURN TO SETTINGS PAGE
                 *  UPON COMPLETION **/

                /**TESTING**/

                //addStudentP(username);



                /*Toast.makeText(getApplicationContext(),
                        "Read Spinner: " + String.valueOf(readSpinner.getSelectedItem()) +
                                "\nMovies Spinner: " + String.valueOf(moviesSpinner.getSelectedItem()) +
                                "\nHookup Spinner: " + String.valueOf(hookupSpinner.getSelectedItem()) +
                                "\nSports Spinner: " + String.valueOf(sportsSpinner.getSelectedItem()) +
                                "\nWorkout Spinner: " + String.valueOf(workoutSpinner.getSelectedItem()) +
                                "\nHiking Spinner: " + String.valueOf(hikingSpinner.getSelectedItem()) +
                                "\nReligious Spinner: " + String.valueOf(religiousSpinner.getSelectedItem()) +
                                "\nSocial Spinner: " + String.valueOf(socialSpinner.getSelectedItem()) +
                                "\nDrink Spinner: " + String.valueOf(drinkSpinner.getSelectedItem()) +
                                "\nSmoke Spinner: " + String.valueOf(smokeSpinner.getSelectedItem()) +
                                "\nMusic Spinner: " + String.valueOf(musicSpinner.getSelectedItem()),
                                Toast.LENGTH_LONG).show();*/
            }
        });
    }


    public void addStudentP(String username) {
        //sets up db handler
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);


        //creating a new student with general info from the values in the fields
        StudentPersonal studentP = new StudentPersonal();
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

        //resets spinners, i think
        setupSpinners();
    }


}
