package com.example.rpcum.studentdirectory.MainScreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Surveys.GeneralUserSurvey;
import com.example.rpcum.studentdirectory.Surveys.MyDBHandler;
import com.example.rpcum.studentdirectory.Surveys.PersonalProfileSurvey;


public class LoginPage extends AppCompatActivity {

    SharedPreferences sp;


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

        sp = getSharedPreferences("loggedIn",MODE_PRIVATE);
    }

    private void init() {
        setupActionBar();
        setupEditTexts();
        setupLoginButton();
        setupNewUserButton();

    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

    private void setupNewUserButton() {
        newUserButton = getWindow().getDecorView().findViewById(R.id.register_button);

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), GeneralUserSurvey.class);
                startActivity(intent);
            }
        });
    }

    private void setupLoginButton() {
        loginButton = getWindow().getDecorView().findViewById(R.id.loginSubmit_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(attemptLogin()) {
//
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("username",String.valueOf(username.getText()));
//                    editor.putBoolean("loggedIn",true);
//                    editor.apply();

                    intent = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(intent);
                //}

            }
        });
    }

    private void setupEditTexts() {
        username = findViewById(R.id.loginUsername_edit);
        pwd = findViewById(R.id.loginPwd_edit);


    }


    public boolean attemptLogin() {
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);

        String pwdHash = String.valueOf(pwd.getText().hashCode());
        String usernameText = String.valueOf(username.getText());
        if(dbHandler.DBattemptLogin(usernameText,pwdHash)) {
            return true;
            //go to homepage

        }
        else
            pwd.setText("");
            Toast.makeText(getApplicationContext(),"Wrong Username or Password.", Toast.LENGTH_LONG).show();
            return false;
    }



}
