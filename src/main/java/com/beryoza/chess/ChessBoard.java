package com.beryoza.chess;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)) {
            ChessPiece piece = board[startLine][startColumn];
            if (piece == null || !nowPlayer.equals(piece.getColor())) return false;

            if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                // Передвигаем фигуру
                board[endLine][endColumn] = piece;
                board[startLine][startColumn] = null;

                // Если двигалась ладья или король, устанавливаем check в false
                if (piece instanceof King || piece instanceof Rook) {
                    piece.check = false;
                }

                // Переключаем игрока
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2 (Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1 (White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}

