package com.inbaltako.tictactoe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class BoardActivity extends AppCompatActivity {

    private ReturnDialog returnDialog;
    private Tictactoe game;
    private ImageButton buttons[];

    private boolean humanTurn = true;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        returnDialog = new ReturnDialog();

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

    public void initButtons() {
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

    public void startGame() {
        game.clearBoard();

        for (int i = 0; i < game.getBoardSize(); i++) {
            buttons[i].setImageResource(R.drawable.o);
            buttons[i].setEnabled(true);
            buttons[i].setOnClickListener(new BoardClickLstener(i));
        }

        if (humanTurn) {
            // -- TO DO -- update your turn text
            humanTurn = false;
        } else {
            // -- TO DO -- update oppunets turn text
            int move = game.getAndroidMove();
            setMove(game.ANDROID, move);
            humanTurn = true;
        }
    }

    public void setMove(char player, int location) {
        game.setMove(player, location);
        buttons[location].setEnabled(false);
        buttons[location].setImageResource(R.drawable.x);
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
                        // -- TO DO -- update computer turn text
                        int move = game.getAndroidMove();
                        setMove(game.ANDROID, move);
                        // -- TO DO -- update button[location] to be O
                        winner = game.checkForWinner();
                    }

                    if (winner == 0) {
                        // -- TO DO -- update human turn text
                    } else if (winner == 1) {
                        // -- TO DO -- move to tie fragment
                        gameOver = true;
                    } else if (winner == 2) {
                        // -- TO DO -- move to human won fragment
                        gameOver = true;
                    } else {
                        // -- TO DO -- move to android won fragment
                        gameOver = true;
                    }
                }
            }
        }
    }
}