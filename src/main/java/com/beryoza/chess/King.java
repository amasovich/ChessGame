package com.beryoza.chess;

/**
 * Класс King представляет короля в шахматах.
 * Король может двигаться на одну клетку в любом направлении.
 */
public class King extends ChessPiece {

    /**
     * Конструктор для создания короля с указанным цветом.
     *
     * @param color цвет короля ("White" или "Black").
     */
    public King(String color) {
        super(color);
    }

    /**
     * Определяет, может ли король двигаться на заданную позицию на шахматной доске.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line начальная строка.
     * @param column начальный столбец.
     * @param toLine конечная строка.
     * @param toColumn конечный столбец.
     * @return true, если король может переместиться на заданную позицию, иначе false.
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы король не оставался на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Король может двигаться только на одну клетку в любом направлении
        if (Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            // Проверка на возможность перемещения на клетку, которая либо пуста, либо занята фигурой противника
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Возвращает символ, представляющий короля.
     *
     * @return "K" для короля.
     */
    @Override
    public String getSymbol() {
        return "K"; // Символ, который обозначает короля
    }

    /**
     * Проверяет, находится ли позиция под атакой.
     *
     * @param chessBoard текущая шахматная доска.
     * @param line строка позиции.
     * @param column столбец позиции.
     * @return true, если позиция под атакой, иначе false.
     */
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        // Проходим по всей доске и проверяем, может ли какая-либо фигура противника напасть на клетку
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // Клетка находится под атакой
                    }
                }
            }
        }
        return false; // Клетка не под атакой
    }
}
