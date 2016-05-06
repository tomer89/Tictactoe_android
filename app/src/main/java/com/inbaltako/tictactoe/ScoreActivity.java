package com.inbaltako.tictactoe;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScoreActivity extends FragmentActivity {

    TieFragment tieFragment;
    HumanWonFragment humanWonFragment;
    AndroidWonFragment androidWonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // setting portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tieFragment = new TieFragment();
        androidWonFragment = new AndroidWonFragment();
        humanWonFragment = new HumanWonFragment();

        tieFragment.setArguments(getIntent().getExtras());
        androidWonFragment.setArguments(getIntent().getExtras());
        humanWonFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.score_activity, tieFragment).commit();
    }

}
