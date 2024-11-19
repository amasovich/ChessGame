package com.beryoza.chess;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы конь не пытался остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Конь ходит буквой "Г"
        int dLine = Math.abs(toLine - line);
        int dColumn = Math.abs(toColumn - column);

        // Проверяем, что цель соответствует движениям коня
        if ((dLine == 2 && dColumn == 1) || (dLine == 1 && dColumn == 2)) {
            // Проверяем, что в целевой позиции либо пусто, либо находится фигура противника
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false; // Во всех других случаях ход невозможен
    }

    @Override
    public String getSymbol() {
        return "H"; // Символ, который обозначает коня
    }
}



