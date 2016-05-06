package com.inbaltako.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BoardActivity extends AppCompatActivity {

    private ReturnDialog returnDialog;
    private Tictactoe game;
    private ImageButton buttons[];
    private TextView movesLeft;
    private TextView player;
    private int totalMoves;
    private Intent intent;

    private boolean humanTurn = true;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        returnDialog = new ReturnDialog();
        intent = new Intent(this, ScoreActivity.class);
        player = (TextView) findViewById(R.id.turnsText);
        movesLeft = (TextView) findViewById(R.id.movesLeft);
        totalMoves = 9;

        movesLeft.setText(Integer.toString(totalMoves));

        // setting portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // enable return button
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        game = new Tictactoe();
        buttons = new ImageButton[game.getBoardSize()];
        initButtons();

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

    private void startGame() {
        game.clearBoard();

        for (int i = 0; i < game.getBoardSize(); i++) {
            buttons[i].setImageResource(R.drawable.empty_button);
            buttons[i].setEnabled(true);
            buttons[i].setOnClickListener(new BoardClickLstener(i));
        }

        if (humanTurn) {
            player.setText(R.string.your);
            humanTurn = false;
        } else {
            player.setText(R.string.opponent);
            int move = game.getAndroidMove();
            humanTurn = true;
            setMove(game.ANDROID, move);
        }
    }

    private void setMove(char player, int location) {
        game.setMove(player, location);
        buttons[location].setEnabled(false);
        if (player == game.HUMAN) {
            buttons[location].setImageResource(R.drawable.x);
            if (totalMoves >= 1) {
                totalMoves--;
                movesLeft.setText(Integer.toString(totalMoves));
            }
        } else {
            buttons[location].setImageResource(R.drawable.o);
            if (totalMoves >= 1) {
                totalMoves--;
                movesLeft.setText(Integer.toString(totalMoves));
            }
        }
    }

    private void killCallingActivity() {
        finish();
    }

    private class BoardClickLstener implements View.OnClickListener {

        int location;

        public BoardClickLstener(int location) {
            this.location = location;
        }

        @Override
        public void onClick(View v) {
            if (!gameOver) {
                if (buttons[location].isEnabled()) {
                    setMove(game.HUMAN, location);
                    int winner = game.checkForWinner();
                    if (winner == 0) {
                        player.setText(R.string.opponent);
                        int move = game.getAndroidMove();
                        setMove(game.ANDROID, move);
                        winner = game.checkForWinner();
                    }
                    if (winner == 0) {
                        player.setText(R.string.your);
                    } else if (winner == 1) {
                        gameOver = true;
                        intent.putExtra("result", 1);
                        startActivity(intent);
                        killCallingActivity();
                    } else if (winner == 2) {
                        gameOver = true;
                        intent.putExtra("result", 2);
                        startActivity(intent);
                        killCallingActivity();
                    } else {
                        gameOver = true;
                        intent.putExtra("result", 3);
                        startActivity(intent);
                        killCallingActivity();
                    }
                }
            }
        }
    }
}