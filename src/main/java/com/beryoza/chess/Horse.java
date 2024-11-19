package com.beryoza.chess;

/**
 * Класс Horse представляет коня в шахматах.
 * Конь может двигаться буквой "Г" и перепрыгивать через другие фигуры.
 */
public class Horse extends ChessPiece {

    /**
     * Конструктор для создания коня с указанным цветом.
     *
     * @param color цвет коня ("White" или "Black").
     */
    public Horse(String color) {
        super(color);
    }

    /**
     * Определяет, может ли конь двигаться на заданную позицию на шахматной доске.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line начальная строка.
     * @param column начальный столбец.
     * @param toLine конечная строка.
     * @param toColumn конечный столбец.
     * @return true, если конь может переместиться на заданную позицию, иначе false.
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы конь не оставался на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Конь ходит буквой "Г"
        int dLine = Math.abs(toLine - line);
        int dColumn = Math.abs(toColumn - column);

        // Проверяем, что цель соответствует движениям коня
        if ((dLine == 2 && dColumn == 1) || (dLine == 1 && dColumn == 2)) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            // Конь может ходить либо на пустую клетку, либо на клетку с фигурой противника
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false; // Во всех других случаях ход невозможен
    }

    /**
     * Возвращает символ, представляющий коня.
     *
     * @return "H" для коня.
     */
    @Override
    public String getSymbol() {
        return "H"; // Символ, который обозначает коня
    }
}



