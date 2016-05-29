package com.inbaltako.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;


public class MenuActivity extends AppCompatActivity  {

    private ActionBar actionBar;
    private ImageButton exit;
    private TextView onePlayer;
    private TextView twoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        createBanner();

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first time", true);
        editor.commit();

        // setting portrait mode
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);

        exit = (ImageButton) findViewById(R.id.exitBtn);
        onePlayer = (TextView) findViewById(R.id.one_player_game);
        twoPlayer = (TextView) findViewById(R.id.two_player_game);
    }

    public void btnClicked(View v) {
        switch (v.getId()) {
            case R.id.exitBtn:
                finish();
                break;
            case R.id.one_player_game:
                startActivity(new Intent(this, BoardActivityComp.class));
                this.finish();
                break;
            case R.id.two_player_game:
                startActivity(new Intent(this, BoardActivityPlayer.class));
                this.finish();
                break;
        }
    }


    private void createBanner(){
        PublisherAdView mPublisherAdView = (PublisherAdView) findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("52AEE74759A9AC6500EC41B17866BBFC")  // htc
                .build();
        mPublisherAdView.loadAd(adRequest);
    }

}
