package com.example.rpcum.studentdirectory.Surveys;

//Created By Janai Williams: 11/2/17

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;

public class GeneralUserSurvey extends AppCompatActivity {

    private EditText firstName, age, phoneNumber, email;
    private Spinner genderSpinner;
    private Button submitButton;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_user_survey);
        init();
    }

    private void init() {
        setupActionBar();
        setupEditTexts();
        setupSpinner();
        setupSubmitButton();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }

    private void setupSpinner() {
        genderSpinner = findViewById(R.id.gender_spinner);
    }

    private void setupEditTexts() {
        firstName = findViewById(R.id.first_name_edit);
        age = findViewById(R.id.age_edit);
        phoneNumber = findViewById(R.id.phone_number_edit);
        email = findViewById(R.id.email_edit);

    }

    private void setupSubmitButton() {
        submitButton = getWindow().getDecorView().findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** METHOD NEEDS TO SEND SAVED DATA TO DB **/

                /** IMPLEMENT: Disable submit button until all forms are filled **/

                /**NEEDS TO RETURN TO SETTINGS PAGE UPON COMPLETION **/

                /**TESTING**/
                Toast.makeText(getApplicationContext(),
                        "First Name: " + String.valueOf(firstName.getText()) +
                                "\nGender: " + String.valueOf(genderSpinner.getSelectedItem()) +
                                "\nAge: " + String.valueOf(age.getText()) +
                                "\nPhone Number: " + String.valueOf(phoneNumber.getText()) +
                                "\nEmail: " + String.valueOf(email.getText()), Toast.LENGTH_LONG).show();
            }
        });
    }
}
