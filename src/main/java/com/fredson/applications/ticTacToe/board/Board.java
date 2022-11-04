package com.fredson.applications.ticTacToe.board;

import com.fredson.applications.ticTacToe.game.Player;

import java.util.Random;

public class Board {

    private final char[][] board = new char[3][3];
    private static final char X = 'X';
    private static final char O = 'O';
    private Position position = new Position();

    public Board() {
        clearBoard();
    }

    private void clearBoard() {
        for (int i = 0; i < board.length; i ++ ) {
            for (int j = 0; j < board.length; j ++) {
                board[i][j] = ' ';
            }
        }
    }

    public String getBoard() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j ++) {
                boardString.append(board[i][j]);
                if (j != 2) boardString.append(" | ");
            }
            boardString.append("\n");
            if (i != 2) boardString.append("---------");
            boardString.append("\n");
        }
        return boardString.toString();
    }

    public void setPosition(int row, int column, Player player) {
        position.setRow(row);
        position.setColumn(column);
        if (!isEmpty(position)) throw new IllegalArgumentException("Position is invalid!");
        board[position.getRow() - 1][position.getColumn() - 1] = player.toString().charAt(0);
    }

    public void setPosition(Player player) {
        Random positionRadom = new Random();
        while (true) {
            position.setRow(positionRadom.nextInt(1, 4));
            position.setColumn(positionRadom.nextInt(1, 4));
            if (isEmpty(position)) {
                board[position.getRow() - 1][position.getColumn() - 1] = player.toString().charAt(0);
                break;
            }
        }
    }

    private boolean isEmpty(Position position) {
        return board[position.getRow() - 1][position.getColumn() - 1] == ' ';
    }

    public boolean isEmpty() {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j ++) {
                position.setRow(i);
                position.setColumn(j);
                if (board[position.getRow()][position.getColumn()] == ' ') {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean getWinner(char typePlayer) {
        return getWinnerRow(typePlayer) || getWinnerColumn(typePlayer) || getWinnerDiagonal(typePlayer);
    }

    private boolean getWinnerRow(char typePlayer) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j ++) {
                position.setRow(i);
                position.setColumn(j);
                if (board[position.getRow()][position.getColumn()] != typePlayer) {
                    break;
                }
                if (position.getColumn() == 2) return true;
            }
        }
        return false;
    }

    private boolean getWinnerColumn(char typePlayer) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j ++) {
                position.setRow(j);
                position.setColumn(i);
                if (board[position.getRow()][position.getColumn()] != typePlayer) {
                    break;
                }
                if (position.getRow() == 2) return true;
            }
        }
        return false;
    }

    private boolean getWinnerDiagonal(char typePlayer) {
        for (int i = 0; i < board.length; i ++) {
            position.setRow(i);
            position.setColumn(i);
            if (board[position.getRow()][position.getColumn()] != typePlayer) {
               break;
            }
            if (position.getRow() == 2) return true;
        }
        for (int i = 0; i < board.length; i ++) {
            for (int j = board.length - 1; j >= 0 ; j --) {
                position.setRow(i);
                position.setColumn(j);
                if (board[position.getRow()][position.getColumn()] != typePlayer) {
                    return false;
                }
                i = i + 1;
            }
            if (position.getRow() == 2 && position.getColumn() == 0) return true;
        }
        return false;
    }
}
