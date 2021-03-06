package com.example.rpcum.studentdirectory.Surveys;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.MainScreens.Homepage;
import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.MyDBHandler;
import com.example.rpcum.studentdirectory.Utils.Settings;

public class GeneralUserSurvey extends AppCompatActivity {

    private EditText username, pwd, confirmPwd, firstName, age, phoneNumber, email;
    private Spinner genderSpinner;
    private Button submitButton;
    private ActionBar actionBar;
    private Intent intent;
    SharedPreferences sp;
    private Intent lastActivity;
    private int itemID;
    private Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_user_survey);
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemID = item.getItemId();

        if(itemID == android.R.id.home){
            intent1 = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent1);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                lastActivity = getIntent();

                //if logged in as user
                if(sp.getBoolean("loggedIn",false)) {
                    //usernameCheckOriginal();
                    if (confirmPwd()) {
                        updateStudentG();
                        intent = new Intent(getApplicationContext(), Homepage.class);
                        startActivity(intent);
                    }
                }
                //redirect back to settings
                else if(lastActivity.getBooleanExtra("FROMSETTINGS", false)){
                    updateStudentG();
                    intent = new Intent(getApplicationContext(), Settings.class);
                    startActivity(intent);
                }
                //registering
                else {
                        addStudentG();
                        intent = new Intent(getApplicationContext(), PersonalProfileSurvey.class);
                        intent.putExtra("FROMGENSURVEY", true);
                        startActivity(intent);
                }

            }
        });
    }


    public void addStudentG() {
        //sets up db handler
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

//        String passwordToHash = String.valueOf(pwd.getText());
//        String generatedPwd = null;
//
//        byte[] bytes = passwordToHash.getBytes();
        String pwdHash = dbHandler.hashPwd(String.valueOf(pwd.getText()));

        //creating a new student with general info from the values in the fields
        StudentGeneral studentG = new StudentGeneral(String.valueOf(username.getText()), pwdHash,
                String.valueOf(firstName.getText()), genderSpinner.getSelectedItem().toString(),
                String.valueOf(age.getText()), String.valueOf(phoneNumber.getText()),
                String.valueOf(email.getText()));


        //sql injection protection for drop table
        StringBuffer sb = new StringBuffer("drop");

        if(studentG.getFirstName().contentEquals(sb)){
            Toast.makeText(getApplicationContext(),"First Name is not valid. Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        if(studentG.getUsername().contentEquals(sb)){
            Toast.makeText(getApplicationContext(),"Username is not valid. Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        if(studentG.getAge().contentEquals(sb)){
            Toast.makeText(getApplicationContext(),"Get Age is not valid. Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        if(studentG.getPhoneNumber().contentEquals(sb)){
            Toast.makeText(getApplicationContext(),"Phone Number is not valid. Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        if(studentG.getEmail().contentEquals(sb)){
            Toast.makeText(getApplicationContext(),"Email is not valid. Try again.", Toast.LENGTH_LONG).show();
            return;
        }


        //adds the student info to the db
        dbHandler.addNewUserHandler(studentG);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn",true);
        editor.putString("username",studentG.getUsername());
        editor.apply();

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

    public void updateStudentG() {
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

        //updating a student with general info from the values in the
        String pwdHash = dbHandler.hashPwd(String.valueOf(pwd.getText()));
        StudentGeneral studentG = new StudentGeneral(String.valueOf(username.getText()),pwdHash,
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
        else if(!(String.valueOf(pwd.getText()).equals(String.valueOf(confirmPwd.getText())))) {

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

    public void usernameCheckOriginal() {

        String checkName = String.valueOf(username.getText());

        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

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
