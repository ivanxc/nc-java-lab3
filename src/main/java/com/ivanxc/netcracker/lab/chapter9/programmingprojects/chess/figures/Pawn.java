package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
    private final int direction;
    private final int enPassantRow;

    public Pawn(int row, int column, Color color, Chessboard board) {
        super(row, column, color, board);
        if (color == Color.WHITE) {
            direction = -1;
            enPassantRow = 3;
        } else {
            direction = 1;
            enPassantRow = 4;
        }
    }

    @Override
    public ArrayList<String> canMoveTo() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        int direction = -1;
        char pawsRow = 6;
        char enPassantRow = 3;
        if (color == Color.BLACK) {
            direction = 1;
            pawsRow = 1;
            enPassantRow = 4;
        }

        // Если впереди свободно
        if (board.isEmptyCell(column, row + direction)) {
            availableCellsToMove.add(asString(column, row + direction));
        }
        // Если столбец больше 0 и слева сверху есть фигура
        if (column > 0 && !board.isEmptyCell(column - 1, row + direction) &&
            board.getFigure(column - 1, row + direction).getColor() != color) {
            availableCellsToMove.add(asString(column - 1, row + direction));
        }
        // Если столбец меньше 7 и справа сверху есть фигура
        if (column < 7 && !board.isEmptyCell(column + 1, row + direction) &&
            board.getFigure(column + 1, row + direction).getColor() != color) {
            availableCellsToMove.add(asString(column + 1, row + direction));
        }
        // Если стартовая строка и никто не мешает пройти.
        if (row == pawsRow && board.isEmptyCell(column, row + direction)
                     && board.isEmptyCell(column, row + direction * 2)) {
            availableCellsToMove.add(asString(column, row + direction * 2));
        }

        // Взятие на проходе
        String leftSide = (char)('a' + column - 1) + "" + (char)('8' - enPassantRow - direction * 2)
            + "-" + (char)('a' + column - 1) + (char)('8' - enPassantRow);
        String rightSide = (char)('a' + column + 1) + "" + (char)('8' - enPassantRow - direction * 2)
            + "-" + (char)('a' + column + 1) + (char)('8' - enPassantRow);

        if (row == enPassantRow && board.getLastMove().matches(leftSide)) {
            if (column > 0 && board.getFigure(column - 1, row) instanceof Pawn) {
                availableCellsToMove.add(asString(column - 1, row + direction));
            }
        }
        if (row == enPassantRow && board.getLastMove().matches(rightSide)) {
            if (column < 7 && board.getFigure(column + 1, row) instanceof Pawn) {
                availableCellsToMove.add(asString(column + 1, row + direction));
            }
        }

        return availableCellsToMove;
    }

    public boolean isThereEnPassant() {
        String leftSide = (char)('a' + column - 1) + "" + (char)('8' - enPassantRow - direction * 2)
            + "-" + (char)('a' + column - 1) + (char)('8' - enPassantRow);
        String rightSide = (char)('a' + column + 1) + "" + (char)('8' - enPassantRow - direction * 2)
            + "-" + (char)('a' + column + 1) + (char)('8' - enPassantRow);

        if (row == enPassantRow && board.getLastMove().matches(leftSide)) {
            if (column > 0 && board.getFigure(column - 1, row) instanceof Pawn) {
                return true;
            }
        }
        if (row == enPassantRow && board.getLastMove().matches(rightSide)) {
            if (column < 7 && board.getFigure(column + 1, row) instanceof Pawn) {
                return true;
            }
        }
        return false;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}