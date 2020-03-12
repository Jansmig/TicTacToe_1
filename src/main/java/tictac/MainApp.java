package tictac;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp extends Application {

    public static int TILE_SIZE = 200;
    public static boolean xTurn;
    public static int xVictoryCounter;
    public static int oVictoryCounter;
    public static String textToDisplay = "X player's turn";
    public static Text notification = new Text("" + textToDisplay);


    public static GridPane newGame() {
        GridPane root = new GridPane();
        root.setPrefSize(TILE_SIZE * 3, TILE_SIZE * 4);
        root.setStyle("-fx-background-color: linear-gradient(#23cdea, rgba(8,32,55,0.84))");

        for (int i = 0; i < 3; i++) {
            ColumnConstraints col = new ColumnConstraints(TILE_SIZE);
            root.getColumnConstraints().add(col);
        }
        for (int j = 0; j < 4; j++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            root.getRowConstraints().add(row);
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = new Tile(x, y, TILE_SIZE, TILE_SIZE);
                root.add(tile, x, y);
            }
        }
        return root;
    }


    public static List<Tile> getAllTiles(GridPane gridpane) {
        return
                gridpane.getChildren().stream()
                        .filter(e -> e instanceof Rectangle)
                        .map(e -> (Tile) e)
                        .collect(Collectors.toList());
    }

    public static Tile getTile(GridPane gridpane, int col, int row) {
        return
                getAllTiles(gridpane).stream()
                        .filter(e -> e.getX() == col)
                        .filter(e -> e.getY() == row)
                        .findFirst().get();
    }

    public static boolean checkXvicotry(GridPane gridpane) {

        return (getTile(gridpane, 0, 0).isX() && getTile(gridpane, 1, 0).isX() && getTile(gridpane, 2, 0).isX()) ||
                (getTile(gridpane, 0, 1).isX() && getTile(gridpane, 1, 1).isX() && getTile(gridpane, 2, 1).isX()) ||
                (getTile(gridpane, 0, 2).isX() && getTile(gridpane, 1, 2).isX() && getTile(gridpane, 2, 2).isX()) ||
                (getTile(gridpane, 0, 0).isX() && getTile(gridpane, 0, 1).isX() && getTile(gridpane, 0, 2).isX()) ||
                (getTile(gridpane, 1, 0).isX() && getTile(gridpane, 1, 1).isX() && getTile(gridpane, 1, 2).isX()) ||
                (getTile(gridpane, 2, 0).isX() && getTile(gridpane, 2, 1).isX() && getTile(gridpane, 2, 2).isX()) ||
                (getTile(gridpane, 0, 0).isX() && getTile(gridpane, 1, 1).isX() && getTile(gridpane, 2, 2).isX()) ||
                (getTile(gridpane, 2, 0).isX() && getTile(gridpane, 1, 1).isX() && getTile(gridpane, 0, 2).isX());
    }

    public static boolean checkOvicotry(GridPane gridpane) {

        return (getTile(gridpane, 0, 0).isO() && getTile(gridpane, 1, 0).isO() && getTile(gridpane, 2, 0).isO()) ||
                (getTile(gridpane, 0, 1).isO() && getTile(gridpane, 1, 1).isO() && getTile(gridpane, 2, 1).isO()) ||
                (getTile(gridpane, 0, 2).isO() && getTile(gridpane, 1, 2).isO() && getTile(gridpane, 2, 2).isO()) ||
                (getTile(gridpane, 0, 0).isO() && getTile(gridpane, 0, 1).isO() && getTile(gridpane, 0, 2).isO()) ||
                (getTile(gridpane, 1, 0).isO() && getTile(gridpane, 1, 1).isO() && getTile(gridpane, 1, 2).isO()) ||
                (getTile(gridpane, 2, 0).isO() && getTile(gridpane, 2, 1).isO() && getTile(gridpane, 2, 2).isO()) ||
                (getTile(gridpane, 0, 0).isO() && getTile(gridpane, 1, 1).isO() && getTile(gridpane, 2, 2).isO()) ||
                (getTile(gridpane, 2, 0).isO() && getTile(gridpane, 1, 1).isO() && getTile(gridpane, 0, 2).isO());
    }

    public static boolean checkDraw(GridPane gridpane) {

        return (!getTile(gridpane, 0, 0).isPlayable() &&
                !getTile(gridpane, 1, 0).isPlayable() &&
                !getTile(gridpane, 2, 0).isPlayable() &&
                !getTile(gridpane, 0, 1).isPlayable() &&
                !getTile(gridpane, 1, 1).isPlayable() &&
                !getTile(gridpane, 2, 1).isPlayable() &&
                !getTile(gridpane, 0, 2).isPlayable() &&
                !getTile(gridpane, 1, 2).isPlayable() &&
                !getTile(gridpane, 2, 2).isPlayable());
    }

    public static void boardReset(GridPane gridpane) {
        getTile(gridpane, 0, 0).tileReset();
        getTile(gridpane, 1, 0).tileReset();
        getTile(gridpane, 2, 0).tileReset();
        getTile(gridpane, 0, 1).tileReset();
        getTile(gridpane, 1, 1).tileReset();
        getTile(gridpane, 2, 1).tileReset();
        getTile(gridpane, 0, 2).tileReset();
        getTile(gridpane, 1, 2).tileReset();
        getTile(gridpane, 2, 2).tileReset();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gameboard = newGame();

        Text xScore = new Text("X score: " + xVictoryCounter);
        xScore.setFont(Font.font("Agency FB", FontWeight.BOLD, 48));
        GridPane.setHalignment(xScore, HPos.CENTER);
        GridPane.setValignment(xScore, VPos.TOP);
        xScore.setFill(Color.WHITE);
        gameboard.add(xScore, 1, 3);

        Text oScore = new Text("O score: " + oVictoryCounter);
        oScore.setFont(Font.font("Agency FB", FontWeight.BOLD, 48));
        GridPane.setHalignment(oScore, HPos.CENTER);
        GridPane.setValignment(oScore, VPos.BOTTOM);
        oScore.setFill(Color.WHITE);
        gameboard.add(oScore, 1, 3);

        gameboard.add(notification, 1, 3);
        notification.setFont(Font.font("Agency FB", FontWeight.BOLD, 28));
        GridPane.setHalignment(notification, HPos.CENTER);
        GridPane.setValignment(notification, VPos.CENTER);
        notification.setFill(Color.WHITE);


        gameboard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkXvicotry(gameboard)) {
                    textToDisplay = "X player won!";
                    notification.setText(textToDisplay);
                    xVictoryCounter++;
                    xScore.setText("X score: " + xVictoryCounter);
                    boardReset(gameboard);

                } else if (checkOvicotry(gameboard)) {
                    textToDisplay = "O player won!";
                    notification.setText(textToDisplay);
                    oVictoryCounter++;
                    oScore.setText("O score: " + oVictoryCounter);
                    boardReset(gameboard);

                } else if (checkDraw(gameboard)) {
                    textToDisplay = "Draw!";
                    notification.setText(textToDisplay);
                    boardReset(gameboard);
                }
            }
        });


        Scene scene = new Scene(gameboard, TILE_SIZE * 3, TILE_SIZE * 4, Color.DARKSLATEGREY);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        xTurn = true;
        int xVictoryCounter = 0;
        int oVictoryCounter = 0;


        /*
        // this button is Useless in the current state

        Button newGame = new Button();
        newGame.setText("New Game");
        GridPane.setHalignment(newGame, HPos.CENTER);
        GridPane.setHalignment(newGame, HPos.CENTER);
        gameboard.add(newGame, 0, 3);
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 if (!tile00.playable && !tile10.playable && !tile20.playable &&
                        !tile01.playable && !tile11.playable && !tile21.playable &&
                        !tile02.playable && !tile12.playable && !tile22.playable) {
                    tile00.tileReset();
                    tile10.tileReset();
                    tile20.tileReset();
                    tile01.tileReset();
                    tile11.tileReset();
                    tile21.tileReset();
                    tile02.tileReset();
                    tile12.tileReset();
                    tile22.tileReset();

                } else {
                    System.out.println("Please finish the current game first.");
                }
            }
        });

         */

    }


    public static void main(String[] args) {
        launch(args);
    }

}
