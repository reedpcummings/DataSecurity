package com.example.rpcum.studentdirectory.MainScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Surveys.MyDBHandler;
import com.example.rpcum.studentdirectory.Surveys.PersonalProfileSurvey;


public class LoginPage extends AppCompatActivity {

    private Button loginButton, newUserButton;
    private Intent intent;
    private EditText username, pwd;
    private ActionBar actionBar;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        init();
    }

    private void init() {
        setupActionBar();
        setupEditTexts();
        setupLoginButton();
        setupNewUserButton();

    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }

    private void setupNewUserButton() {
        newUserButton = getWindow().getDecorView().findViewById(R.id.register_button);

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createNewUser();
                Toast.makeText(getApplicationContext(),"It Works!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupLoginButton() {
        loginButton = getWindow().getDecorView().findViewById(R.id.loginSubmit_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // attemptLogin();
                //createNewUser();
                intent = new Intent(getApplicationContext(), Homepage.class);
                startActivity(intent);


            }
        });
    }

    private void setupEditTexts() {
        username = findViewById(R.id.loginUsername_edit);
        pwd = findViewById(R.id.loginPwd_edit);


    }


    public void attemptLogin() {
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);

        String pwdHash = (String)String.valueOf(pwd.getText().hashCode());
        String usernameText = String.valueOf(username.getText());
        if(dbHandler.DBattemptLogin(usernameText,pwdHash)) {

            LoggedInTuple.loggedInInner user = new LoggedInTuple.loggedInInner();
            user.logIn(usernameText);
            //go to homepage

        }
        else
            pwd.setText("");
            Toast.makeText(getApplicationContext(),"Wrong Username or Password.", Toast.LENGTH_LONG).show();
    }


    public void createNewUser() {
        //load general survey page
    }
}
