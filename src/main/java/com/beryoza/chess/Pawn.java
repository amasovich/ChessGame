package com.beryoza.chess;

/**
 * Класс Pawn представляет пешку в шахматах.
 * Пешка может двигаться вперед на одну клетку, или на две клетки с начальной позиции.
 * Пешка также может атаковать фигуры противника по диагонали.
 */
public class Pawn extends ChessPiece {

    /**
     * Конструктор для создания пешки с указанным цветом.
     *
     * @param color цвет пешки ("White" или "Black").
     */
    public Pawn(String color) {
        super(color);
    }

    /**
     * Определяет, может ли пешка двигаться на заданную позицию на шахматной доске.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line начальная строка.
     * @param column начальный столбец.
     * @param toLine конечная строка.
     * @param toColumn конечный столбец.
     * @return true, если пешка может переместиться на заданную позицию, иначе false.
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Пешка не может остаться на той же позиции
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Определяем направление движения в зависимости от цвета пешки
        int direction = this.color.equals("White") ? 1 : -1; // Белая идет вверх, черная идет вниз
        int startLine = this.color.equals("White") ? 1 : 6; // Начальная позиция для белых и черных пешек

        // Проверка на первый ход (можно на 2 клетки)
        if (line == startLine && toLine - line == 2 * direction && column == toColumn &&
                chessBoard.board[toLine][toColumn] == null &&
                chessBoard.board[line + direction][column] == null) {
            return true;
        }

        // Обычный ход пешки на 1 клетку вперед
        if (toLine - line == direction && column == toColumn && chessBoard.board[toLine][toColumn] == null) {
            return true;
        }

        // Съедение фигуры (диагональное движение на одну клетку)
        if (toLine - line == direction && Math.abs(toColumn - column) == 1 &&
                chessBoard.board[toLine][toColumn] != null &&
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color)) {
            return true;
        }

        // В остальных случаях ход невозможен
        return false;
    }

    /**
     * Возвращает символ, представляющий пешку.
     *
     * @return "P" для пешки.
     */
    @Override
    public String getSymbol() {
        return "P"; // Символ, который обозначает пешку
    }
}
