package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Bishop;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece.Color;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.King;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Knight;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Pawn;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Queen;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Rook;
import java.util.ArrayList;
import java.util.Arrays;

public class Chessboard {
    private ChessPiece[][] board;
    private String lastMove;

    public Chessboard() {
        board = new ChessPiece[8][8];
        setStartPositions(Color.WHITE);
        setStartPositions(Color.BLACK);
    }

    private void setStartPositions(Color color) {
        int pawsRow;
        int otherFiguresRow;
        if (color == Color.WHITE) {
            pawsRow = 6;
            otherFiguresRow = 7;
        } else {
            pawsRow = 1;
            otherFiguresRow = 0;
        }

        for(char column = 0; column < 8; column++) {
            setFigure(new Pawn(pawsRow, column, color, this), column, pawsRow);
        }

        Rook leftRook = new Rook(otherFiguresRow, 0, color, this);
        Knight leftKnight = new Knight(otherFiguresRow, 1, color, this);
        Bishop leftBishop = new Bishop(otherFiguresRow, 2, color, this);
        Queen queen = new Queen(otherFiguresRow, 3, color, this);
        King king = new King(otherFiguresRow, 4, color, this);
        Bishop rightBishop = new Bishop(otherFiguresRow, 5, color, this);
        Knight rightKnight = new Knight(otherFiguresRow, 6, color, this);
        Rook rightRook = new Rook(otherFiguresRow, 7, color, this);

        setFigure(leftRook,0, otherFiguresRow);
        setFigure(leftKnight, 1, otherFiguresRow);
        setFigure(leftBishop, 2, otherFiguresRow);
        setFigure(queen, 3, otherFiguresRow);
        setFigure(king, 4, otherFiguresRow);
        setFigure(rightBishop, 5, otherFiguresRow);
        setFigure(rightKnight, 6, otherFiguresRow);
        setFigure(rightRook, 7, otherFiguresRow);
    }

    public boolean isEmptyCell(int column, int row) {
        return board[row][column] == null;
    }

    public void setFigure(ChessPiece chessPiece, int column, int row) {
        lastMove = ChessPiece.asString(chessPiece.getColumn(), chessPiece.getRow()) + "-" +
            ChessPiece.asString(column, row);

        board[chessPiece.getRow()][chessPiece.getColumn()] = null;
        board[row][column] = chessPiece;

        chessPiece.setRow(row);
        chessPiece.setColumn(column);
        chessPiece.increaseMoveCounter();
    }

    public ChessPiece getFigure(int column, int row) {
        return board[row][column];
    }

    public String getLastMove() {
        return lastMove;
    }

    public void replaceFigure(ChessPiece from, ChessPiece to) {
        board[from.getRow()][from.getColumn()] = to;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }
}
