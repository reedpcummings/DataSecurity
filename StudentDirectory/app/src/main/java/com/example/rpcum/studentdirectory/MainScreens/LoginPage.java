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
import com.example.rpcum.studentdirectory.Utils.MyDBHandler;


public class LoginPage extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    private Button loginButton, newUserButton;
    private Intent intent;
    private EditText username, pwd;
    private ActionBar actionBar;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //Context context = getActivity();
        sp = getSharedPreferences("loggedIn",MODE_PRIVATE);
        editor = sp.edit();
        editor.putBoolean("loggedIn",false);
        editor.putString("username","");
        editor.putBoolean("search",false);
        editor.commit();

//        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);
//        dbHandler.deleteContents();
//        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp.db", null, 1);
//        dbHandler.createTable();

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

                if(attemptLogin()) {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",String.valueOf(username.getText()));
                    editor.putBoolean("loggedIn",true);
                    editor.apply();

                    intent = new Intent(getApplicationContext(), Homepage.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void setupEditTexts() {
        username = findViewById(R.id.loginUsername_edit);
        pwd = findViewById(R.id.loginPwd_edit);


    }


    public boolean attemptLogin() {
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

        //String pwdHash = String.valueOf(pwd.getText());

        String pwdHash = dbHandler.hashPwd(String.valueOf(pwd.getText()));

        String usernameText = String.valueOf(username.getText());
        if(dbHandler.DBattemptLogin(usernameText,pwdHash)) {
            return true;


        }
        else
            pwd.setText("");
            Toast.makeText(getApplicationContext(),"Wrong Username or Password.", Toast.LENGTH_LONG).show();
            return false;
    }


    }
