package tictac;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Button;
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
    public static String textToDisplay = "Select the game mode";
    public static Text notification = new Text("" + textToDisplay);
    public static boolean gameOn = false;
    public static boolean PvP = false;
    public static GridPane gameboard = new GridPane();


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


    public static List<Tile> getPlayableTiles(GridPane gridpane) {
        return
                gridpane.getChildren().stream()
                        .filter(e -> e instanceof Rectangle)
                        .map(e -> (Tile) e)
                        .filter(Tile::isPlayable)
                        .collect(Collectors.toList());
    }

    public static Tile getTile(GridPane gridpane, int col, int row) {
        return
                getAllTiles(gridpane).stream()
                        .filter(e -> e.getX() == col)
                        .filter(e -> e.getY() == row)
                        .findFirst().get();
    }

    public static void computerMove(GridPane gridpane) {
        List<Tile> tempList = new ArrayList<>();
        tempList = getPlayableTiles(gridpane);
        int tempInt = tempList.size();
        if (tempInt > 0) {
            int randInt = new Random().nextInt(tempInt);
            Tile tempTile = tempList.get(randInt);
            tempTile.computerMovePattern(tempTile);
        } else {
            checkXvicotry(gridpane);
            checkOvicotry(gridpane);
            checkDraw(gridpane);
        }
        textToDisplay = "X player's turn";
        MainApp.notification.setText(textToDisplay);
        checkXvicotry(gridpane);
        checkOvicotry(gridpane);
        checkDraw(gridpane);
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

        gameboard = newGame();

        Text xScore = new Text("X score: " + xVictoryCounter);
        xScore.setFont(Font.font("Agency FB", FontWeight.BOLD, 48));
        GridPane.setHalignment(xScore, HPos.CENTER);
        GridPane.setValignment(xScore, VPos.TOP);
        xScore.setFill(Color.WHITE);

        Text oScore = new Text("O score: " + oVictoryCounter);
        oScore.setFont(Font.font("Agency FB", FontWeight.BOLD, 48));
        GridPane.setHalignment(oScore, HPos.CENTER);
        GridPane.setValignment(oScore, VPos.BOTTOM);
        oScore.setFill(Color.WHITE);

        notification.setFont(Font.font("Agency FB", FontWeight.BOLD, 28));
        GridPane.setHalignment(notification, HPos.CENTER);
        GridPane.setValignment(notification, VPos.CENTER);
        notification.setFill(Color.WHITE);
        gameboard.add(notification, 1, 3);


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


        Button newGamePvPBtn = new Button();
        newGamePvPBtn.setText("Player vs Player");
        GridPane.setHalignment(newGamePvPBtn, HPos.CENTER);
        newGamePvPBtn.setStyle("    -fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 8,7,6;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;" +
                "    -fx-font-weight: bold;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        gameboard.add(newGamePvPBtn, 0, 3);

        Button newGamePvEBtn = new Button();
        newGamePvEBtn.setText("Player vs Computer");
        GridPane.setHalignment(newGamePvEBtn, HPos.CENTER);
        newGamePvEBtn.setStyle("    -fx-background-color: \n" +
                "        linear-gradient(#f2f2f2, #d6d6d6),\n" +
                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n" +
                "    -fx-background-radius: 8,7,6;\n" +
                "    -fx-background-insets: 0,1,2;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;" +
                "    -fx-font-weight: bold;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        gameboard.add(newGamePvEBtn, 2, 3);
        newGamePvEBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameboard.getChildren().remove(newGamePvEBtn);
                gameboard.getChildren().remove(newGamePvPBtn);
                gameboard.add(xScore, 1, 3);
                gameboard.add(oScore, 1, 3);
                textToDisplay = "X player's turn";
                notification.setText(textToDisplay);
                gameOn = true;
                PvP = false;
            }
        });

        newGamePvPBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameboard.getChildren().remove(newGamePvPBtn);
                gameboard.getChildren().remove(newGamePvEBtn);
                gameboard.add(xScore, 1, 3);
                gameboard.add(oScore, 1, 3);
                textToDisplay = "X player's turn";
                notification.setText(textToDisplay);
                gameOn = true;
                PvP = true;
            }
        });


    }


    public static void main(String[] args) {
        launch(args);
    }

}
