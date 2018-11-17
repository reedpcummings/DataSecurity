package com.example.rpcum.studentdirectory.MainScreens;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.Settings;

public class MatchResults extends AppCompatActivity {

    private ActionBar actionBar;
    private Intent intent;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_results);
        init();
    }

    private void init() {
        setupActionBar();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemID = item.getItemId();

        if(itemID == android.R.id.home){
            intent = new Intent(getApplicationContext(), Homepage.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
