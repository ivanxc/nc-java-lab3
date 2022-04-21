package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import java.util.ArrayList;

public class King extends ChessPiece {
    private int[] rowMoves =    {1, 1, 1, 0, 0, -1, -1, -1};
    private int[] columnMoves = {-1, 0, 1, -1, 1, -1, 0, 1};

    public King(int row, int column, Color color, Chessboard board) {
        super(row, column, color, board);
    }

    @Override
    public ArrayList<String> canMoveTo() {
        ArrayList<String> availableCellsToMove = new ArrayList<>();

        for(int i = 0; i < rowMoves.length; i++) {
            if (row + rowMoves[i] < 8 && row + rowMoves[i] >= 0 &&
                column + columnMoves[i] < 8 && column + columnMoves[i] >= 0) {
                int newRow = row + rowMoves[i];
                int newColumn = column + columnMoves[i];
                ChessPiece chessPiece = board.getFigure(newColumn, newRow);
                if (chessPiece == null || chessPiece.color != color) {
                    availableCellsToMove.add(asString(newColumn, newRow));
                }
            }
        }

        return availableCellsToMove;
    }

    @Override
    public String toString() {
        return "King";
    }
}
