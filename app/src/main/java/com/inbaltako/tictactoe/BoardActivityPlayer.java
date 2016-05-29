package com.inbaltako.tictactoe;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;

import javax.net.ssl.SSLEngineResult;


public class BoardActivityPlayer extends AppCompatActivity {

    private ReturnDialog returnDialog;
    private Tictactoe game;
    private ImageButton buttons[];
    private TextView tvMovesLeft;
    private TextView tvPlayer;
    private TextView tvPlayerOneScore;
    private TextView tvPlayerTwoScore;

    private int totalMoves;
    private int scorePlayerOne;
    private int scorePlayerTwo;

    private Intent intent;

    PublisherInterstitialAd mPublisherInterstitialAd;

    private boolean playerOneTurn = true;
    private boolean gameOver = false;
    private static final int DELAY = 350;
    private static final int SCORE_DELAY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_activity_player);


        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-1826176491547625/7048099993");
        requestNewInterstitial();

        mPublisherInterstitialAd.getAdListener();

        createBanner();

        enterScore();

        returnDialog = new ReturnDialog();
        intent = new Intent(this, ScoreActivity.class);
        tvPlayer = (TextView) findViewById(R.id.turnsText);
        tvMovesLeft = (TextView) findViewById(R.id.movesLeft);
        tvPlayerOneScore = (TextView) findViewById(R.id.viewScorePlayerOne);
        tvPlayerTwoScore = (TextView) findViewById(R.id.viewScorePlayerTwo);
        tvPlayer.setText(R.string.player_one);

        // setting portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvMovesLeft.setText(Integer.toString(totalMoves));
        tvPlayerOneScore.setText(Integer.toString(scorePlayerOne));
        tvPlayerTwoScore.setText(Integer.toString(scorePlayerTwo));

        // enable return button
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        game = new Tictactoe();
        buttons = new ImageButton[game.getBoardSize()];
        initButtons();

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                //finishGame();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                finishGame();
            }
        });

        startGame();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnDialog.show(getFragmentManager(), "returnToMenu");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initButtons() {
        buttons[0] = (ImageButton) findViewById(R.id.boardBtn1);
        buttons[1] = (ImageButton) findViewById(R.id.boardBtn2);
        buttons[2] = (ImageButton) findViewById(R.id.boardBtn3);
        buttons[3] = (ImageButton) findViewById(R.id.boardBtn4);
        buttons[4] = (ImageButton) findViewById(R.id.boardBtn5);
        buttons[5] = (ImageButton) findViewById(R.id.boardBtn6);
        buttons[6] = (ImageButton) findViewById(R.id.boardBtn7);
        buttons[7] = (ImageButton) findViewById(R.id.boardBtn8);
        buttons[8] = (ImageButton) findViewById(R.id.boardBtn9);
    }

    private void disableBtns(){
        for (int i = 0; i < game.getBoardSize(); i++) {
            buttons[i].setEnabled(false);
        }
    }

    private void enableBtns(){
        for (int i = 0; i < game.getBoardSize(); i++) {
            if(game.board[i] != game.HUMAN && game.board[i] != game.ANDROID)
                buttons[i].setEnabled(true);
        }
    }

    private void startGame() {
        game.clearBoard();

        for (int i = 0; i < game.getBoardSize(); i++) {
            buttons[i].setImageResource(R.drawable.empty_button);
            buttons[i].setEnabled(true);
            buttons[i].setOnClickListener(new BoardClickLstener(i,totalMoves));
        }

        if (playerOneTurn) {
            playerOneTurn = false;
        } else {
            //int move = game.getAndroidMove();
            playerOneTurn = true;
            //setMove(game.ANDROID, move);
        }
    }

    private void setMove(char player, int location) {
        this.disableBtns();

        if (totalMoves >= 1){
            totalMoves--;
            tvMovesLeft.setText(Integer.toString(totalMoves));
        }

        int winner = game.checkForWinner();
        /*if (winner != 0) {
            gameOver = true;
            return;
        }*/
        //else {

            game.setMove(player, location);
            buttons[location].setEnabled(false);


            //game.Human in this case is player 1
            if (player == game.HUMAN) {
                buttons[location].setImageResource(R.drawable.x);
                playerOneTurn = false;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (totalMoves - 1>= 1 && game.checkForWinner() == 0) {
                            tvPlayer.setText(R.string.player_one);
                        }
                    }
                }, DELAY);
            } else {
                buttons[location].setImageResource(R.drawable.o);
                playerOneTurn = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (totalMoves >= 1 && game.checkForWinner() == 0) {
                            tvPlayer.setText(R.string.player_two);
                        }
                    }
                }, DELAY);
            }
        //}
        if(game.checkForWinner() == 0) this.enableBtns();
        else {
            if (mPublisherInterstitialAd.isLoaded()) {
                mPublisherInterstitialAd.show();
            }


        }
    }

    private void killCallingActivity() {
        finish();
    }

    private class BoardClickLstener implements View.OnClickListener {

        int location;
        int totalMoves;

        public BoardClickLstener(int location , int totalMoves) {
            this.location = location;
            this.totalMoves = totalMoves;
        }


        @Override
        public void onClick(View v) {
            int winner = game.checkForWinner();
            if(totalMoves != 0) {
                if (winner == 0) {
                    if (buttons[location].isEnabled()) {
                        if (playerOneTurn) {
                            setMove(game.HUMAN, location);
                            playerOneTurn = false;

                        } else {
                            setMove(game.ANDROID, location);
                        }
                        winner = game.checkForWinner();
                        //if game is finished continue playing else finish game
                        if (winner == 0) {
                            return;
                        }

                        // show ad
                        if (mPublisherInterstitialAd.isLoaded()) {
                            mPublisherInterstitialAd.show();
                            return;
                        } else {
                            finishGame();
                        }
                    }
                }
            }
        }
    }

    private void createBanner(){
        PublisherAdView mPublisherAdView = (PublisherAdView) findViewById(R.id.publisherAdView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("52AEE74759A9AC6500EC41B17866BBFC")  // My Galaxy Nexus test phone
                .build();
        mPublisherAdView.loadAd(adRequest);
    }

    private void enterScore(){
        totalMoves = 9;

        SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean firstTime = sharedPreferences.getBoolean("first time", true);
        if (firstTime) {
            editor.putInt("player one score", 0);
            editor.putInt("player two score", 0);
        }
        editor.putBoolean("first time", false);
        editor.commit();

        scorePlayerOne = sharedPreferences.getInt("player two score", 0);
        scorePlayerTwo = sharedPreferences.getInt("player one score", 0);
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("52AEE74759A9AC6500EC41B17866BBFC")
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }



    private void finishGame(){
        int winner = game.checkForWinner();

        if (winner == 1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameOver = true;
                    intent.putExtra("result", 11);
                    startActivity(intent);
                    killCallingActivity();
                }
            }, SCORE_DELAY);
        } else if (winner == 2) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameOver = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int playerOneScore = sharedPreferences.getInt("player one score", 0) + 1;
                    editor.putInt("player one score", playerOneScore);
                    editor.commit();

                    intent.putExtra("result", 12);
                    startActivity(intent);
                    killCallingActivity();
                }
            }, SCORE_DELAY);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameOver = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    int playerTwoScore = sharedPreferences.getInt("player two score", 0) + 1;
                    editor.putInt("player two score", playerTwoScore);
                    editor.commit();

                    intent.putExtra("result", 13);
                    startActivity(intent);
                    killCallingActivity();
                }
            }, SCORE_DELAY);
        }
    }


}

