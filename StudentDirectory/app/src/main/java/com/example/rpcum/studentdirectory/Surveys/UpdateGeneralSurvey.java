package com.example.rpcum.studentdirectory.Surveys;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.MyDBHandler;

public class UpdateGeneralSurvey extends AppCompatActivity {

    private EditText username, pwd, confirmPwd, firstName, age, phoneNumber, email;
    private Spinner genderSpinner;
    private Button submitButton;
    private ActionBar actionBar;
    private Intent intent;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_general_survey);
        init();
    }

    private void init() {
        setupActionBar();
        setupEditTexts();
        setupSpinner();
        setupSubmitButton();
        sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }

    private void setupSpinner() {
        genderSpinner = findViewById(R.id.update_gender);
    }

    private void setupEditTexts() {
        username = findViewById(R.id.update_username);
        pwd = findViewById(R.id.update_password);
        confirmPwd = findViewById(R.id.update_pwd_confirm);
        firstName = findViewById(R.id.update_firstName);
        age = findViewById(R.id.update_age);
        phoneNumber = findViewById(R.id.update_phoneNumber);
        email = findViewById(R.id.update_email);

    }

    private void setupSubmitButton() {
        submitButton = getWindow().getDecorView().findViewById(R.id.update_submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //if(confirmPwd()) {
                        updateStudentG();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username",String.valueOf(username.getText()));
                        editor.putBoolean("loggedIn",true);
                        editor.apply();
                        intent = new Intent(getApplicationContext(), PersonalProfileSurvey.class);
                        startActivity(intent);
                    //}

            }
        });
    }


    public void updateStudentG() {
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

        //updating a student with general info from the values in the
        String pwdHash = dbHandler.hashPwd(String.valueOf(pwd.getText()));
        StudentGeneral studentG = new StudentGeneral(String.valueOf(username.getText()), pwdHash,
                String.valueOf(firstName.getText()), genderSpinner.getSelectedItem().toString(),
                String.valueOf(age.getText()), String.valueOf(phoneNumber.getText()),
                String.valueOf(email.getText()));


        //adds the student info to the db
        dbHandler.updateGeneralSurvey(studentG);

    }

    public boolean confirmPwd() {

        if(String.valueOf(pwd.getText()).length() < 4) {

            pwd.setText("");
            confirmPwd.setText("");
            Toast.makeText(getApplicationContext(),"Password is not long enough. Try again", Toast.LENGTH_LONG).show();
            return false;
        }

        //this is not working for some reason right now. the other one works.
        else if(String.valueOf(pwd.getText()) != String.valueOf(confirmPwd.getText())) {

            pwd.setText("");
            confirmPwd.setText("");
            Toast.makeText(getApplicationContext(),"Passwords do not match. Try again.", Toast.LENGTH_LONG).show();
            return false;
        }

        else {
            Toast.makeText(getApplicationContext(),"Passwords match", Toast.LENGTH_LONG).show();
            return true;
        }
    }

}
