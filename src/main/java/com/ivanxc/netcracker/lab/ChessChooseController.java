package com.ivanxc.netcracker.lab;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Bishop;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece.Color;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Knight;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Queen;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Rook;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ChessChooseController {
    @FXML
    AnchorPane globParent;
    private int row;
    private int column;
    private ChessPiece.Color color;
    private Chessboard board;
    private GameController main; // ссылка на основной контроллер

    public void chooseRook(MouseEvent mouseEvent) {
        main.setChoosenChessPiece(new Rook(row, column, color, board));
        globParent.getScene().getWindow().hide();
    }

    public void chooseKnight(MouseEvent mouseEvent) {
        main.setChoosenChessPiece(new Knight(row, column, color, board));
        globParent.getScene().getWindow().hide();
    }

    public void chooseBishop(MouseEvent mouseEvent) {
        main.setChoosenChessPiece(new Bishop(row, column, color, board));
        globParent.getScene().getWindow().hide();
    }

    public void chooseQueen(MouseEvent mouseEvent) {
        main.setChoosenChessPiece(new Queen(row, column, color, board));
        globParent.getScene().getWindow().hide();
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setColor(
        Color color) {
        this.color = color;
    }

    public void setBoard(Chessboard board) {
        this.board = board;
    }

    public void setMain(GameController main) {
        this.main = main;
    }
}
