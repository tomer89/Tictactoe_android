package com.inbaltako.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class ScoreActivity extends FragmentActivity {

    private TieFragment tieFragment;
    private HumanWonFragment humanWonFragment;
    private AndroidWonFragment androidWonFragment;
    private TieFragmentTwoPlayers tieFragmentTwoPlayers;
    private PlayerOneWonFragment playerOneWonFragment;
    private PlayerTwoWonFragment playerTwoWonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // setting portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tieFragment = new TieFragment();
        androidWonFragment = new AndroidWonFragment();
        humanWonFragment = new HumanWonFragment();
        tieFragmentTwoPlayers = new TieFragmentTwoPlayers();
        playerOneWonFragment = new PlayerOneWonFragment();
        playerTwoWonFragment = new PlayerTwoWonFragment();

        tieFragment.setArguments(getIntent().getExtras());
        androidWonFragment.setArguments(getIntent().getExtras());
        humanWonFragment.setArguments(getIntent().getExtras());

        Intent intent = getIntent();
        int result = intent.getIntExtra("result", -1);

        // show results of who won for player vrs computer mode:
        if (result == 1)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, tieFragment).commit();
        else if (result == 2)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, humanWonFragment).commit();
        else if(result == 3)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, androidWonFragment).commit();
        //show results for two player mode:
        else if(result == 11)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, tieFragmentTwoPlayers).commit();
        else if(result == 12)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, playerOneWonFragment).commit();
        else if(result ==13)
            getSupportFragmentManager().beginTransaction().add(R.id.score_activity, playerTwoWonFragment).commit();
    }

}
