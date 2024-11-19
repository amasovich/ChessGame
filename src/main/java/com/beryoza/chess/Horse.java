package com.beryoza.chess;

public class Horse extends ChessPiece {

    // Конструктор, который принимает цвет фигуры
    public Horse(String color) {
        super(color);  // Вызов конструктора родительского класса ChessPiece
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

        // Конь может ходить только на 2 клетки в одном направлении и 1 в другом
        return (dLine == 2 && dColumn == 1) || (dLine == 1 && dColumn == 2);
    }

    @Override
    public String getSymbol() {
        return "H"; // Символ, который обозначает коня
    }
}


