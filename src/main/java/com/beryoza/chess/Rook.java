package com.beryoza.chess;

public class Rook extends ChessPiece {

    // Конструктор, который принимает цвет фигуры
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверяем, чтобы позиции были в пределах доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false; // Если не в пределах доски - движение невозможно
        }

        // Проверяем, чтобы ладья не оставалась на той же позиции
        if (line == toLine && column == toColumn) {
            return false; // Нельзя остаться на месте
        }

        // Ладья может двигаться либо по одной строке, либо по одному столбцу
        if (line != toLine && column != toColumn) {
            return false; // Если изменяются и строка, и столбец одновременно, ход невозможен
        }

        // Проверяем, что путь между текущей и целевой позицией свободен
        int stepLine = (line == toLine) ? 0 : (toLine > line ? 1 : -1);
        int stepColumn = (column == toColumn) ? 0 : (toColumn > column ? 1 : -1);

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

    @Override
    public String getSymbol() {
        return "R"; // Символ, который обозначает ладью
    }
}
