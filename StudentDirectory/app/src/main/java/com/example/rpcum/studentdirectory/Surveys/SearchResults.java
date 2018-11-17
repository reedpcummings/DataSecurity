package com.example.rpcum.studentdirectory.Surveys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rpcum.studentdirectory.R;
import com.example.rpcum.studentdirectory.Utils.MyDBHandler;

import java.util.ArrayList;

/*
This page displays the search results. It brings in values from the survey completed on the last page,
and displays them. It is very rough right now, I am not so good with the UI part. Maybe you can fix
this. I was trying to put it into a table like the following:
/The lower the match rating the better/

First Name      Age     Gender      Match Rating
Bob             22       Male           8
Chase           23       Male           12
Tori            21      Female          5

etc.

If you could do that, that would be great.

We also do not have any functionality for the matches button on the homepage, so maybe implement that
if you have the time. If not, take it out, and display phone number and email in the table above.

*/
public class SearchResults extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        TextView firstName1 = findViewById(R.id.match_firstName1);
//        TextView firstName2 = findViewById(R.id.match_firstName2);
//        TextView firstName3 = findViewById(R.id.match_firstName3);
//        TextView firstName4 = findViewById(R.id.match_firstName4);
//        TextView firstName5 = findViewById(R.id.match_firstName5);
//        TextView firstName6 = findViewById(R.id.match_firstName6);
//        TextView firstName7 = findViewById(R.id.match_firstName7);
//        TextView firstName8 = findViewById(R.id.match_firstName8);
//        TextView firstName9 = findViewById(R.id.match_firstName9);
//        TextView firstName10 = findViewById(R.id.match_firstName10);

        TextView age1 = findViewById(R.id.match_age1);
//        TextView age2 = findViewById(R.id.match_age2);
//        TextView age3 = findViewById(R.id.match_age3);
//        TextView age4 = findViewById(R.id.match_age4);
//        TextView age5 = findViewById(R.id.match_age5);
//        TextView age6 = findViewById(R.id.match_age6);
//        TextView age7 = findViewById(R.id.match_age7);
//        TextView age8 = findViewById(R.id.match_age8);
//        TextView age9 = findViewById(R.id.match_age9);
//        TextView age10 = findViewById(R.id.match_age10);

        TextView gender1 = findViewById(R.id.match_gender1);
//        TextView gender2 = findViewById(R.id.match_gender2);
//        TextView gender3 = findViewById(R.id.match_gender3);
//        TextView gender4 = findViewById(R.id.match_gender4);
//        TextView gender5 = findViewById(R.id.match_gender5);
//        TextView gender6 = findViewById(R.id.match_gender6);
//        TextView gender7 = findViewById(R.id.match_gender7);
//        TextView gender8 = findViewById(R.id.match_gender8);
//        TextView gender9 = findViewById(R.id.match_gender9);
//        TextView gender10 = findViewById(R.id.match_gender10);

        TextView rating1 = findViewById(R.id.match_rating1);
//        TextView rating2 = findViewById(R.id.match_rating2);
//        TextView rating3 = findViewById(R.id.match_rating3);
//        TextView rating4 = findViewById(R.id.match_rating4);
//        TextView rating5 = findViewById(R.id.match_rating5);
//        TextView rating6 = findViewById(R.id.match_rating6);
//        TextView rating7 = findViewById(R.id.match_rating7);
//        TextView rating8 = findViewById(R.id.match_rating8);
//        TextView rating9 = findViewById(R.id.match_rating9);
//        TextView rating10 = findViewById(R.id.match_rating10);


        intent = getIntent();
        ArrayList<String[]> matches;
        String[] wants = intent.getStringArrayExtra("wants");
        int[] wantsValues = intent.getIntArrayExtra("wantValues");
        String uname = intent.getStringExtra("username");
        MyDBHandler dbHandler = new MyDBHandler(this, "datingApp3.db", null, 1);

        //returns the matches in an arrayList, with the last value being how close of a match(the smaller the better)
        //I want to display fname, age, gender, and compatibility score of top 10(array list returned is all of DB)
        //right now there are only 5 useable entries in the db. maybe add 5 more users so we can display 10

        matches = dbHandler.searchForBestMatch(wants,wantsValues,uname);

        String[] match1 = matches.get(0);
        String[] match2 = matches.get(1);
        String[] match3 = matches.get(2);
        String[] match4 = matches.get(3);
        String[] match5 = matches.get(4);
//        String[] match6 = matches.get(5);
//        String[] match7 = matches.get(6);
//        String[] match8 = matches.get(7);
//        String[] match9 = matches.get(8);
//        String[] match10 = matches.get(9);

        firstName1.setText(dbHandler.getFirstName(match1[0]));
//        firstName2.setText(dbHandler.getFirstName(match2[0]));
//        firstName3.setText(dbHandler.getFirstName(match3[0]));
//        firstName4.setText(dbHandler.getFirstName(match4[0]));
//        firstName5.setText(dbHandler.getFirstName(match5[0]));
//        firstName6.setText(dbHandler.getFirstName(match6[0]));
//        firstName7.setText(dbHandler.getFirstName(match7[0]));
//        firstName8.setText(dbHandler.getFirstName(match8[0]));
//        firstName9.setText(dbHandler.getFirstName(match9[0]));
//        firstName10.setText(dbHandler.getFirstName(match10[0]));
//
        age1.setText(dbHandler.getAge(match1[0]));
//        age2.setText(dbHandler.getAge(match2[0]));
//        age3.setText(dbHandler.getAge(match3[0]));
//        age4.setText(dbHandler.getAge(match4[0]));
//        age5.setText(dbHandler.getAge(match5[0]));
//        age6.setText(dbHandler.getAge(match6[0]));
//        age7.setText(dbHandler.getAge(match7[0]));
//        age8.setText(dbHandler.getAge(match8[0]));
//        age9.setText(dbHandler.getAge(match9[0]));
//        age10.setText(dbHandler.getAge(match10[0]));
//
        gender1.setText(dbHandler.getGender(match1[0]));
//        gender2.setText(dbHandler.getGender(match1[0]));
//        gender3.setText(dbHandler.getGender(match1[0]));
//        gender4.setText(dbHandler.getGender(match1[0]));
//        gender5.setText(dbHandler.getGender(match1[0]));
//        gender6.setText(dbHandler.getGender(match1[0]));
//        gender7.setText(dbHandler.getGender(match1[0]));
//        gender8.setText(dbHandler.getGender(match1[0]));
//        gender9.setText(dbHandler.getGender(match1[0]));
//        gender10.setText(dbHandler.getGender(match1[0]));
//
        rating1.setText(match1[match1.length-1]);
//        rating2.setText(match2[match2.length-1]);
//        rating3.setText(match3[match3.length-1]);
//        rating4.setText(match4[match4.length-1]);
//        rating5.setText(match5[match5.length-1]);
//        rating6.setText(match6[match6.length-1]);
//        rating7.setText(match7[match7.length-1]);
//        rating8.setText(match8[match8.length-1]);
//        rating9.setText(match9[match9.length-1]);
//        rating10.setText(match10[match10.length-1]);

        init();
    }

    private void init() {


    }

}
