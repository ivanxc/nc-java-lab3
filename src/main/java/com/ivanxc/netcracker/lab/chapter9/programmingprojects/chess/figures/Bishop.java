package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import java.util.ArrayList;

public class Bishop extends ChessPiece {
    public Bishop(int row, int column, Color color, Chessboard board) {
        super(row, column, color, board);
    }

    @Override
    public ArrayList<String> canMoveTo() {
        ArrayList<String> availableCellsToMove = new ArrayList<>(allDiagonalMoves());

        return availableCellsToMove;
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
