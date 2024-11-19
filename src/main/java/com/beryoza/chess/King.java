package com.beryoza.chess;

public class King extends ChessPiece {

    // Конструктор, который принимает цвет фигуры
    public King(String color) {
        super(color);
    }

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

    @Override
    public String getSymbol() {
        return "K"; // Символ, который обозначает короля
    }

    // Метод, который проверяет, находится ли клетка под атакой
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
