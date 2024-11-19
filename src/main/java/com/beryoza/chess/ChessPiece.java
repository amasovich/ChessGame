package com.beryoza.chess;

/**
 * Абстрактный класс ChessPiece представляет шахматную фигуру.
 * Каждая фигура имеет цвет и может выполнять определённые ходы.
 */
public abstract class ChessPiece {

    /**
     * Цвет шахматной фигуры. Может быть "White" или "Black".
     * Сделано `protected` для прямого доступа в подклассах.
     */
    protected String color;

    /**
     * Переменная для отслеживания, двигалась ли фигура (для проверки возможности рокировки).
     * Сделано `protected` для прямого доступа в подклассах.
     */
    protected boolean check = true;

    /**
     * Конструктор для создания шахматной фигуры с заданным цветом.
     *
     * @param color цвет фигуры, может быть "White" или "Black".
     */
    public ChessPiece(String color) {
        this.color = color;
    }

    /**
     * Возвращает цвет фигуры.
     *
     * @return цвет фигуры.
     */
    public String getColor() {
        return color;
    }

    /**
     * Определяет, может ли фигура двигаться на заданную позицию на шахматной доске.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line начальная строка.
     * @param column начальный столбец.
     * @param toLine конечная строка.
     * @param toColumn конечный столбец.
     * @return true, если фигура может переместиться на заданную позицию, иначе false.
     */
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    /**
     * Возвращает символ, представляющий тип фигуры.
     *
     * @return символ фигуры.
     */
    public abstract String getSymbol();
}


