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

    private EditText username, pwd, confirmPwd, firstName, age, phoneNumber, email;
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
        username = findViewById(R.id.username_edit);
        pwd = findViewById(R.id.pwd_edit);
        confirmPwd = findViewById(R.id.pwdConfirm_edit);
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

                //usernameCheckOriginal();
                //confirmPwd();
                //addStudentG();




               /* Toast.makeText(getApplicationContext(),
                        "First Name: " + String.valueOf(firstName.getText()) +
                                "\nGender: " + String.valueOf(genderSpinner.getSelectedItem()) +
                                "\nAge: " + String.valueOf(age.getText()) +
                                "\nPhone Number: " + String.valueOf(phoneNumber.getText()) +
                                "\nEmail: " + String.valueOf(email.getText()), Toast.LENGTH_LONG).show();*/
            }
        });
    }


    public void addStudentG() {
        //sets up db handler
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);

        //creating a new student with general info from the values in the fields
        StudentGeneral studentG = new StudentGeneral(String.valueOf(username.getText()), String.valueOf(pwd.getText().hashCode()),
                String.valueOf(firstName.getText()), genderSpinner.getSelectedItem().toString(),
                String.valueOf(age.getText()), String.valueOf(phoneNumber.getText()),
                String.valueOf(email.getText()));

        //adds the student info to the db
        dbHandler.addNewUserHandler(studentG);

        //load homepage here

//        resets fields
//        username.setText("");
//        pwd.setText("");
//        confirmPwd.setText("");
//        firstName.setText("");
//        setupSpinner();
//        age.setText("");
//        phoneNumber.setText("");
//        email.setText("");
    }

    public void confirmPwd() {

        if(String.valueOf(pwd.getText()).length() < 4) {

            pwd.setText("");
            confirmPwd.setText("");
            Toast.makeText(getApplicationContext(),"Password is not long enough. Try again", Toast.LENGTH_LONG).show();
        }

        //this is not working for some reason right now. the other one works.
        else if(String.valueOf(pwd.getText()) != String.valueOf(confirmPwd.getText())) {

            pwd.setText("");
            confirmPwd.setText("");
            Toast.makeText(getApplicationContext(),"Passwords do not match. Try again.", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(getApplicationContext(),"Passwords match", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void usernameCheckOriginal() {

        String checkName = String.valueOf(username.getText());

        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);

        if(dbHandler.checkUsernameValid(checkName)) {
            return;
        }
        else {
            username.setText("");
            pwd.setText("");
            confirmPwd.setText("");
            Toast.makeText(getApplicationContext(),"Username already exists. Try again.", Toast.LENGTH_LONG).show();
        }
    }


}
