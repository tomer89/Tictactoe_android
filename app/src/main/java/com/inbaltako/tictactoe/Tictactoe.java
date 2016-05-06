package com.inbaltako.tictactoe;

import java.util.Random;

public class Tictactoe {
    private char board[];
    private final static int BOARD_SIZE = 9;
    private Random random;

    protected final static char HUMAN = 'X';
    protected final static char ANDROID = 'O';
    protected final static char EMPTY = ' ';

    public Tictactoe() {
        random = new Random();
        board = new char[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++)
            board[i] = EMPTY;

    }

    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++)
            board[i] = EMPTY;

    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public void setMove(char player, int location) {
        board[location] = player;
    }

    public int getAndroidMove() {
        int move;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] != HUMAN && board[i] != ANDROID) {
                char currentValue = board[i];
                board[i] = ANDROID;
                if (checkForWinner() == 3) {
                    setMove(ANDROID, i);
                    return i;
                } else {
                    board[i] = currentValue;
                }
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] != HUMAN && board[i] != ANDROID) {
                char currentMove = board[i];
                board[i] = HUMAN;
                if (checkForWinner() == 2) {
                    setMove(ANDROID, i);
                    return i;
                } else {
                    board[i] = currentMove;
                }
            }
        }

        do {
            move = random.nextInt(BOARD_SIZE);
        } while (board[move] == HUMAN || board[move] == ANDROID);
        setMove(ANDROID, move);

        return move;
    }

    // return
    // 0 when game is still running
    // 1 when a tie
    // 2 when human won
    // 3 when computer won
    public int checkForWinner() {
        // check for horizontal wins
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] == HUMAN &&
                    board[i + 1] == HUMAN &&
                    board[i + 2] == HUMAN) {
                return 2;  // human wins
            } else if (board[i] == ANDROID &&
                    board[i + 1] == ANDROID &&
                    board[i + 2] == ANDROID) {
                return 3;  // android wins
            }
        }

        // check for vertical wins
        for (int i = 0; i <= 2; i++) {
            if (board[i] == HUMAN &&
                    board[i + 3] == HUMAN &&
                    board[i + 6] == HUMAN) {
                return 2;  // human wins
            } else if (board[i] == ANDROID &&
                    board[i + 3] == ANDROID &&
                    board[i + 6] == ANDROID) {
                return 3;  // android wins
            }
        }

        // check for diagonal wins
        if (board[0] == HUMAN &&
                board[4] == HUMAN &&
                board[8] == HUMAN ||
                board[2] == HUMAN &&
                        board[4] == HUMAN &&
                        board[6] == HUMAN) {
            return 2;
        } else if (board[0] == ANDROID &&
                board[4] == ANDROID &&
                board[8] == ANDROID ||
                board[2] == ANDROID &&
                        board[4] == ANDROID &&
                        board[6] == ANDROID) {
            return 3;
        }

        // check for available moves
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] != HUMAN && board[i] != ANDROID)
                return 0;
        }
        
        return 1;
    }
}
