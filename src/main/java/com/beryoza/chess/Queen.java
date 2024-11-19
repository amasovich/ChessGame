package com.beryoza.chess;

public class Queen extends ChessPiece {

    // Конструктор, который принимает цвет фигуры
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы ферзь не оставался на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Логика движения ферзя - может двигаться как ладья и как слон
        // Проверяем, что ферзь движется по диагонали, вертикали или горизонтали
        if (Math.abs(toLine - line) == Math.abs(toColumn - column) || line == toLine || column == toColumn) {
            int stepLine = (toLine > line) ? 1 : (toLine < line) ? -1 : 0;
            int stepColumn = (toColumn > column) ? 1 : (toColumn < column) ? -1 : 0;

            int currentLine = line + stepLine;
            int currentColumn = column + stepColumn;

            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // Если на пути есть фигура, ход невозможен
                }
                currentLine += stepLine;
                currentColumn += stepColumn;
            }

            // Если целевая клетка либо пуста, либо занята фигурой противника, ход возможен
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "Q"; // Символ, который обозначает ферзя
    }
}
