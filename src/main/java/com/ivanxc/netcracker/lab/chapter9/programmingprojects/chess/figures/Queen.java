package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import java.util.ArrayList;

public class Queen extends ChessPiece {
    public Queen(int row, int column, Color color, Chessboard board) {
        super(row, column, color, board);
    }

    @Override
    public ArrayList<String> canMoveTo() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        availableCellsToMove.addAll(allDiagonalMoves());
        availableCellsToMove.addAll(allHorizontalMoves());
        availableCellsToMove.addAll(allVerticalMoves());

        return availableCellsToMove;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
