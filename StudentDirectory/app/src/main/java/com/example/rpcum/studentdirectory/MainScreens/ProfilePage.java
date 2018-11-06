package com.example.rpcum.studentdirectory.MainScreens;

//Created By Janai Williams: 11/2/17

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rpcum.studentdirectory.R;

public class ProfilePage extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        init();
    }

    private void init() {
        setupActionBar();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }
}
