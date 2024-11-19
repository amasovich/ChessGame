package com.beryoza.chess;

/**
 * Класс Queen представляет ферзя в шахматах.
 * Ферзь может двигаться по диагонали, горизонтали или вертикали.
 */
public class Queen extends ChessPiece {

    /**
     * Конструктор для создания ферзя с указанным цветом.
     *
     * @param color цвет ферзя ("White" или "Black").
     */
    public Queen(String color) {
        super(color);
    }

    /**
     * Определяет, может ли ферзь двигаться на заданную позицию на шахматной доске.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line начальная строка.
     * @param column начальный столбец.
     * @param toLine конечная строка.
     * @param toColumn конечный столбец.
     * @return true, если ферзь может переместиться на заданную позицию, иначе false.
     */
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

        // Логика движения ферзя: либо по диагонали, либо по прямой
        int stepLine = 0;
        int stepColumn = 0;

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // Ферзь движется по диагонали
            stepLine = (toLine > line) ? 1 : -1;
            stepColumn = (toColumn > column) ? 1 : -1;
        } else if (line == toLine || column == toColumn) {
            // Ферзь движется по горизонтали или вертикали
            stepLine = (line == toLine) ? 0 : (toLine > line ? 1 : -1);
            stepColumn = (column == toColumn) ? 0 : (toColumn > column ? 1 : -1);
        } else {
            // Ни по диагонали, ни по прямой — ход невозможен
            return false;
        }

        // Проверяем, что путь между текущей и целевой позицией свободен
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

        return false;
    }

    /**
     * Возвращает символ, представляющий ферзя.
     *
     * @return "Q" для ферзя.
     */
    @Override
    public String getSymbol() {
        return "Q"; // Символ, который обозначает ферзя
    }
}
