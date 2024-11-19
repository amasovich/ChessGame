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

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            // Проверяем начальные условия для рокировки белых по 0 столбцу
            if (board[0][0] == null || board[0][4] == null) return false;

            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {

                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) {

                    // Совершаем рокировку
                    board[0][4] = null; // Убираем короля с текущей позиции
                    board[0][2] = new King("White");
                    board[0][2].check = false; // Король уже двигался
                    board[0][0] = null; // Убираем ладью с текущей позиции
                    board[0][3] = new Rook("White");
                    board[0][3].check = false; // Ладья уже двигалась

                    // Передаем ход черным
                    nowPlayer = "Black";
                    return true;
                }
            }
        } else {
            // Проверяем начальные условия для рокировки черных по 0 столбцу
            if (board[7][0] == null || board[7][4] == null) return false;

            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {

                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) {

                    // Совершаем рокировку
                    board[7][4] = null; // Убираем короля с текущей позиции
                    board[7][2] = new King("Black");
                    board[7][2].check = false; // Король уже двигался
                    board[7][0] = null; // Убираем ладью с текущей позиции
                    board[7][3] = new Rook("Black");
                    board[7][3].check = false; // Ладья уже двигалась

                    // Передаем ход белым
                    nowPlayer = "White";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            // Проверяем начальные условия для рокировки белых по 7 столбцу
            if (board[0][7] == null || board[0][4] == null) return false;

            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][5] == null && board[0][6] == null) {

                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) {

                    // Совершаем рокировку
                    board[0][4] = null; // Убираем короля с текущей позиции
                    board[0][6] = new King("White");
                    board[0][6].check = false; // Король уже двигался
                    board[0][7] = null; // Убираем ладью с текущей позиции
                    board[0][5] = new Rook("White");
                    board[0][5].check = false; // Ладья уже двигалась

                    // Передаем ход черным
                    nowPlayer = "Black";
                    return true;
                }
            }
        } else {
            // Проверяем начальные условия для рокировки черных по 7 столбцу
            if (board[7][7] == null || board[7][4] == null) return false;

            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][5] == null && board[7][6] == null) {

                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) {

                    // Совершаем рокировку
                    board[7][4] = null; // Убираем короля с текущей позиции
                    board[7][6] = new King("Black");
                    board[7][6].check = false; // Король уже двигался
                    board[7][7] = null; // Убираем ладью с текущей позиции
                    board[7][5] = new Rook("Black");
                    board[7][5].check = false; // Ладья уже двигалась

                    // Передаем ход белым
                    nowPlayer = "White";
                    return true;
                }
            }
        }
        return false;
    }

}

