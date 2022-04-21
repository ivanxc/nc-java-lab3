package com.ivanxc.netcracker.lab;

import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.Chessboard;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.GameManager;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Bishop;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.ChessPiece;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.King;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Knight;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Pawn;
import com.ivanxc.netcracker.lab.chapter9.programmingprojects.chess.figures.Rook;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController {
    private ChessPiece choosenChessPiece;
    private Chessboard chessboard;
    private GameManager game = new GameManager();
    private AudioClip moveSound;
    @FXML
    private GridPane gridPaneBoard;
    @FXML
    private ImageView pickedImage;
    private ArrayList<int[]> availableRectanglesToMove = new ArrayList<>();
    @FXML
    private final Rectangle[][] rectangles = new Rectangle[8][8];
    @FXML
    private final ImageView[][] imageViews = new ImageView[8][8];

    private ChessPiece getFigure(int column, int row) {
        return chessboard.getFigure(column, row);
    }

    private void showAvailableMoves(int column, int row) {
        ArrayList<String> availableCellsToMove = chessboard.getFigure(column, row).canMoveTo();
        for(String move : availableCellsToMove) {
            int columnOnGridPane = move.charAt(0) - 'a';
            int rowOnGridPane = '8' - move.charAt(1);
            availableRectanglesToMove.add(new int[]{rowOnGridPane, columnOnGridPane});
            rectangles[rowOnGridPane][columnOnGridPane]
                .fillProperty().set(Color.rgb(152, 251, 152));
        }
    }

    public void click(MouseEvent mouseEvent) throws IOException {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        ChessPiece chessPiece = getFigure(colIndex, rowIndex);

        // Если кликнул на фигуру своего цвета
        if (chessPiece != null && chessPiece.getColor() == game.getPlayerColor()) {
            if (clickedNode instanceof ImageView) {
                cleanAvailableMoves();
                availableRectanglesToMove = new ArrayList<>();
                pickedImage = (ImageView) clickedNode;
                showAvailableMoves(colIndex, rowIndex);
                game.setChessPicked(true);
                game.setPickedChessPiece(chessPiece);
            }
        } else if (game.isChessPicked()) {
            ChessPiece pickedChessPiece = game.getPickedChessPiece();
            if (pickedChessPiece.canMoveTo().contains(ChessPiece.asString(colIndex, rowIndex))) {
                if (imageViews[rowIndex][colIndex] != null) {
                    if (chessboard.getFigure(colIndex, rowIndex) instanceof King) {
                        String winner = pickedChessPiece.getColor() == ChessPiece.Color.WHITE ?
                            "Белые" : "Черные";
                        showEndScreen(winner);
                        return;
                    }
                    gridPaneBoard.getChildren().remove(imageViews[rowIndex][colIndex]);
                    imageViews[rowIndex][colIndex] =
                        imageViews[pickedChessPiece.getRow()][pickedChessPiece.getColumn()];
                    imageViews[pickedChessPiece.getRow()][pickedChessPiece.getColumn()] = null;
                } else if (pickedChessPiece instanceof Pawn) {
                    Pawn pawn = (Pawn)pickedChessPiece;
                    if (pawn.isThereEnPassant() &&
                        chessboard.
                            getFigure(colIndex, rowIndex - pawn.getDirection())
                            instanceof Pawn && pawn !=
                        getFigure(colIndex, rowIndex - pawn.getDirection())) {
                        gridPaneBoard.getChildren()
                            .remove(imageViews[rowIndex - pawn.getDirection()][colIndex]);
                        imageViews[rowIndex - pawn.getDirection()][colIndex] = null;
                    }
                }

                GridPane.setRowIndex(pickedImage, rowIndex);
                GridPane.setColumnIndex(pickedImage, colIndex);
                imageViews[rowIndex][colIndex] = pickedImage;
                imageViews[pickedChessPiece.getRow()][pickedChessPiece.getColumn()] = null;

                chessboard.setFigure(pickedChessPiece, colIndex, rowIndex);

                if (pickedChessPiece instanceof Pawn &&
                    (pickedChessPiece.getRow() == 0 &&
                    pickedChessPiece.getColor() == ChessPiece.Color.WHITE ||
                    pickedChessPiece.getRow() == 7 &&
                    pickedChessPiece.getColor() == ChessPiece.Color.BLACK)) {
                        replaceChess(pickedChessPiece);
                }
                pickedImage = null;
                game.nextMove();
                cleanAvailableMoves();
                moveSound.play();
            }
        }
    }

    private void replaceChess(ChessPiece chessPiece) throws IOException {
        int row = chessPiece.getRow();
        int column = chessPiece.getColumn();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/chooseChess.fxml"));
        Parent root = loader.load();
        ChessChooseController controller = (ChessChooseController) loader.getController();

        controller.setMain(this);
        controller.setRow(row);
        controller.setColumn(column);
        controller.setColor(chessPiece.getColor());
        controller.setBoard(chessboard);

        stage.setTitle("Выбор фигуры");
        stage.setScene(new Scene(root, 240, 180));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        Image newFigure;

        if (choosenChessPiece.getColor() == ChessPiece.Color.WHITE) {
            if (choosenChessPiece instanceof Rook) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/white_rook.png")));
            } else if (choosenChessPiece instanceof Knight) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/white_knight.png")));
            } else if (choosenChessPiece instanceof Bishop) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/white_bishop.png")));
            } else {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/white_queen.png")));
            }
        } else {
            if (choosenChessPiece instanceof Rook) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/black_rook.png")));
            } else if (choosenChessPiece instanceof Knight) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/black_knight.png")));
            } else if (choosenChessPiece instanceof Bishop) {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/black_bishop.png")));
            } else {
                newFigure = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("images/black_queen.png")));
            }
        }
        ImageView imageViewNewFigure = new ImageView(newFigure);
        imageViewNewFigure.setFitWidth(50.0);
        imageViewNewFigure.setFitHeight(50.0);
        ImageView imageView = imageViews[row][column];
        gridPaneBoard.getChildren().remove(imageView);
        gridPaneBoard.add(imageViewNewFigure, column, row);
        imageViews[row][column] = imageViewNewFigure;
        chessboard.replaceFigure(chessPiece, choosenChessPiece);
    }

    public void cleanAvailableMoves() {
        for(int[] rectangleIndex : availableRectanglesToMove) {
            int row = rectangleIndex[0];
            int column = rectangleIndex[1];
            Rectangle rectangle = rectangles[row][column];
            Color color = ((row + column) % 2 == 1 ?
                Color.rgb(255, 255, 255) : Color.rgb(202, 202, 202));
            rectangle.fillProperty().set(color);
        }
        availableRectanglesToMove = new ArrayList<>();
    }

    public void initialize() {
        moveSound = new AudioClip(this.getClass().getResource("sounds/move.mp3").toExternalForm());
        chessboard = new Chessboard();

        // Create chessboard cells
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle();
                if ((i + j) % 2 == 1) {
                    rectangle.fillProperty().set(Color.rgb(255, 255, 255));
                } else {
                    rectangle.fillProperty().set(Color.rgb(202, 202, 202));
                }
                rectangle.widthProperty().set(50);
                rectangle.heightProperty().set(50);
                rectangles[i][j] = rectangle;
                gridPaneBoard.add(rectangle, j, i);
            }
        }

        // Set pawns on GridPane board
        for(int i = 0; i < 8; i++) {
            final Image imageBlackPawn =
                new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream("images/black_pawn.png")));
            ImageView imageViewBlackPawn = new ImageView(imageBlackPawn);
            imageViewBlackPawn.setFitWidth(50.0);
            imageViewBlackPawn.setFitHeight(50.0);
            gridPaneBoard.add(imageViewBlackPawn, i, 1);
            imageViews[1][i] = imageViewBlackPawn;

            final Image imageWhitePawn =
                new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream("images/white_pawn.png")));
            ImageView imageViewWhitePawn = new ImageView(imageWhitePawn);
            imageViewWhitePawn.setFitWidth(50.0);
            imageViewWhitePawn.setFitHeight(50.0);
            gridPaneBoard.add(imageViewWhitePawn, i, 6);
            imageViews[6][i] = imageViewWhitePawn;
        }

        String[] blackFiguresPaths = { "images/black_rook.png", "images/black_knight.png",
            "images/black_bishop.png", "images/black_queen.png",
            "images/black_king.png", "images/black_bishop.png",
            "images/black_knight.png", "images/black_rook.png"};

        String[] whiteFiguresPaths = { "images/white_rook.png", "images/white_knight.png",
            "images/white_bishop.png", "images/white_queen.png",
            "images/white_king.png", "images/white_bishop.png",
            "images/white_knight.png", "images/white_rook.png"};

        // Set another figures on GridPane board
        for(int i = 0; i < 8; i++) {
            final Image imageBlackAnotherFigure =
                new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream(blackFiguresPaths[i])));
            ImageView imageViewBlackAnotherFigure = new ImageView(imageBlackAnotherFigure);
            imageViewBlackAnotherFigure.setFitWidth(50.0);
            imageViewBlackAnotherFigure.setFitHeight(50.0);
            gridPaneBoard.add(imageViewBlackAnotherFigure, i, 0);
            imageViews[0][i] = imageViewBlackAnotherFigure;

            final Image imageWhiteAnotherFigure =
                new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream(whiteFiguresPaths[i])));
            ImageView imageViewWhiteAnotherFigure = new ImageView(imageWhiteAnotherFigure);
            imageViewWhiteAnotherFigure.setFitWidth(50.0);
            imageViewWhiteAnotherFigure.setFitHeight(50.0);
            gridPaneBoard.add(imageViewWhiteAnotherFigure, i , 7);
            imageViews[7][i] = imageViewWhiteAnotherFigure;
        }
    }

    public void setChoosenChessPiece(
        ChessPiece choosenChessPiece) {
        this.choosenChessPiece = choosenChessPiece;
    }

    public void showEndScreen(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Конец игры");
        alert.setHeaderText("Конец игры");
        alert.setContentText("Победитель: " + winner);
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                Stage stage = (Stage) gridPaneBoard.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("view/game.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 500, 500);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("Шахматы на двоих");
                stage.setScene(scene);
                stage.show();
            }
        });
        alert.showAndWait();
    }
}
