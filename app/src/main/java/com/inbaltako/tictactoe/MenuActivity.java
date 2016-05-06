package com.inbaltako.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    ActionBar actionBar;
    ImageButton exit;
    ImageButton newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // setting portrait mode
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);

        exit = (ImageButton) findViewById(R.id.exitBtn);
        newGame = (ImageButton) findViewById(R.id.newGameBtn);
    }

    public void btnClicked(View v) {
        switch (v.getId()) {
            case R.id.exitBtn:
                finish();
                break;
            case R.id.newGameBtn:
                startActivity(new Intent(this, BoardActivity.class));
                this.finish();
                break;
        }
    }
}
