package com.beryoza.chess;

public class Bishop extends ChessPiece {

    // Конструктор, который принимает цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы слон не оставался на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Слон ходит только по диагонали, проверяем что |toLine - line| == |toColumn - column|
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }

        // Проверяем, что путь между текущей и целевой позицией свободен
        int dLine = (toLine > line) ? 1 : -1;
        int dColumn = (toColumn > column) ? 1 : -1;

        int currentLine = line + dLine;
        int currentColumn = column + dColumn;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; // Если на пути есть фигура, ход невозможен
            }
            currentLine += dLine;
            currentColumn += dColumn;
        }

        // Если целевая клетка либо пуста, либо занята фигурой противника, ход возможен
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "B"; // Символ, который обозначает слона
    }
}
