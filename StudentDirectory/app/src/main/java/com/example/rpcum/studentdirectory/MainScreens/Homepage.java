package com.example.rpcum.studentdirectory.MainScreens;

//Created By Janai Williams: 11/2/17

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Surveys.GeneralUserSurvey;
import com.example.rpcum.studentdirectory.Utils.Settings;


public class Homepage extends AppCompatActivity {

    private Button searchButton, matchesResultsButton, viewProfileButton;
    private Intent intent;
    private ActionBar actionBar;
    private int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemID = item.getItemId();

        if(itemID == R.id.settings_button){
            intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        setupActionBar();
        setupButtons();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setTitle("");
    }

    private void setupButtons() {
        searchButton = findViewById(R.id.search_criteria_button);
        matchesResultsButton = findViewById(R.id.matches_page_button);
        viewProfileButton =findViewById(R.id.view_profile_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               /** METHOD NEEDS TO DO SOME SEARCH FUNCTION**/

               /**TESTING**/
               Toast.makeText(getApplicationContext(),"It Works!", Toast.LENGTH_LONG).show();

           }
        });

        matchesResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), MatchResults.class);
                startActivity(intent);
            }
        });

        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });
    }


}
