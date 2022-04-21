package com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece.Color;

public class GameManager {
    private Color playerColor = Color.WHITE;
    private boolean chessPicked = false;
    private ChessPiece pickedChessPiece;

    public Color getPlayerColor() {
        return playerColor;
    }

    public void nextMove() {
        pickedChessPiece = null;
        chessPicked = false;

        if (playerColor == Color.WHITE) {
            playerColor = Color.BLACK;
        } else {
            playerColor = Color.WHITE;
        }
    }

    public boolean isChessPicked() {
        return chessPicked;
    }

    public void setChessPicked(boolean chessPicked) {
        this.chessPicked = chessPicked;
    }

    public ChessPiece getPickedChessPiece() {
        return pickedChessPiece;
    }

    public void setPickedChessPiece(
        ChessPiece pickedChessPiece) {
        this.pickedChessPiece = pickedChessPiece;
    }
}
