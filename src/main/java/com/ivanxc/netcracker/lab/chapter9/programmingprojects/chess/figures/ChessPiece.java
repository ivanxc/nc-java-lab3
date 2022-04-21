package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import java.util.ArrayList;

public abstract class ChessPiece {
    private int moveCounter = -1;
    protected int row;
    protected int column;
    protected Color color;
    protected Chessboard board;

    public ChessPiece(int row, int column, Color color, Chessboard board) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.board = board;
    }

    public void setPosition(String coordinates) {
        row = coordinates.charAt(1) - 49;
        column = coordinates.charAt(0) - 97;
    }

    protected ArrayList<String> allVerticalMoves() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        for(int i = row - 1; i >= 0; i--) {
            ChessPiece chessPiece = board.getFigure(column, i);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(column, i));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(column, i));
                break;
            } else {
                break;
            }
        }

        for(int i = row + 1; i < 8; i++) {
            ChessPiece chessPiece = board.getFigure(column, i);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(column, i));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(column, i));
                break;
            } else {
                break;
            }
        }

        return availableCellsToMove;
    }

    protected ArrayList<String> allHorizontalMoves() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        for(int i = column - 1; i >= 0; i--) {
            ChessPiece chessPiece = board.getFigure(i, row);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(i, row));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(i, row));
                break;
            } else {
                break;
            }
        }

        for(int i = column + 1; i < 8; i++) {
            ChessPiece chessPiece = board.getFigure(i, row);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(i, row));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(i, row));
                break;
            } else {
                break;
            }
        }

        return availableCellsToMove;
    }

    protected ArrayList<String> allDiagonalMoves() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        // to left-down
        for(int c = column - 1, r = row - 1; c >= 0 && r >= 0; c--, r--) {
            ChessPiece chessPiece = board.getFigure(c, r);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(c, r));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(c, r));
                break;
            } else {
                break;
            }
        }

        // to left-up
        for(int c = column - 1, r = row + 1; c >= 0 && r < 8; c--, r++) {
            ChessPiece chessPiece = board.getFigure(c, r);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(c, r));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(c, r));
                break;
            } else {
                break;
            }
        }

        // to right-up
        for(int c = column + 1, r = row + 1; c < 8 && r < 8; c++, r++) {
            ChessPiece chessPiece = board.getFigure(c, r);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(c, r));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(c, r));
                break;
            } else {
                break;
            }
        }

        // to right-down
        for(int c = column + 1, r = row - 1; c < 8 && r > 0; c++, r--) {
            ChessPiece chessPiece = board.getFigure(c, r);
            if (chessPiece == null) {
                availableCellsToMove.add(asString(c, r));
            } else if (chessPiece.color != color) {
                availableCellsToMove.add(asString(c, r));
                break;
            } else {
                break;
            }
        }

        return availableCellsToMove;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public void increaseMoveCounter() {
        this.moveCounter++;
    }

    public static String asString(int column, int row) {
        return (char)('a' + column) + "" + (char)('8' - row);
    }

    public abstract ArrayList<String> canMoveTo();

    public enum Color {
        BLACK,
        WHITE
    }
}
