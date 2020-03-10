package tictac;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    public static int TILE_SIZE = 200;
    public static boolean xTurn;
    public static int xVictoryCounter;
    public static int oVictoryCounter;

    /*
    public static GridPane newGame() {
        GridPane root = new GridPane();
        root.setPrefSize(TILE_SIZE * 3, TILE_SIZE * 3);
        root.setGridLinesVisible(true);

        for (int i = 3; i < 3; i++ ) {
            ColumnConstraints col = new ColumnConstraints(TILE_SIZE);
            root.getColumnConstraints().add(col);
        }

        for (int j = 3; j < 3; j++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            root.getRowConstraints().add(row);
        }

        for (int y = 0; y  < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Tile tile = new Tile(x, y, TILE_SIZE, TILE_SIZE);
                root.add(tile, x, y);
            }
        }
        xTurn = true;

        return root;
    }

     */

    public static void announceTurn(){
        if (xTurn) {
            System.out.println("X player's turn ");
        } else {
            System.out.println("O player's turn");
        }
    }

    // for later usage:
    public static Node getNode (GridPane gridpane, int col, int row) {

        for (Node node : gridpane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gameboard = new GridPane();
        gameboard.setPrefSize(TILE_SIZE * 3, TILE_SIZE * 4);
        gameboard.setGridLinesVisible(false);
        gameboard.setStyle("-fx-background-color: linear-gradient(#23cdea, rgba(8,32,55,0.84))");

        for (int i = 0; i < 3; i++ ) {
            ColumnConstraints col = new ColumnConstraints(TILE_SIZE);
            gameboard.getColumnConstraints().add(col);
        }

        for (int j = 0; j < 4; j++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            gameboard.getRowConstraints().add(row);
        }


        //row 0
        Tile tile00 = new Tile(0,0,1,1);
        gameboard.add(tile00, (int) tile00.getX(), (int) tile00.getY());

        Tile tile10 = new Tile(1,0,1,1);
        gameboard.add(tile10, (int) tile10.getX(), (int) tile10.getY());

        Tile tile20 = new Tile(2,0,1,1);
        gameboard.add(tile20, (int) tile20.getX(), (int) tile20.getY());

        //row 1
        Tile tile01 = new Tile(0,1,1,1);
        gameboard.add(tile01, (int) tile01.getX(), (int) tile01.getY());

        Tile tile11 = new Tile(1,1,1,1);
        gameboard.add(tile11, (int) tile11.getX(), (int) tile11.getY());

        Tile tile21 = new Tile(2,1,1,1);
        gameboard.add(tile21, (int) tile21.getX(), (int) tile21.getY());

        //row 2
        Tile tile02 = new Tile(0,2,1,1);
        gameboard.add(tile02, (int) tile02.getX(), (int) tile02.getY());

        Tile tile12 = new Tile(1,2,1,1);
        gameboard.add(tile12, (int) tile12.getX(), (int) tile12.getY());

        Tile tile22 = new Tile(2,2,1,1);
        gameboard.add(tile22, (int) tile22.getX(), (int) tile22.getY());

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


        gameboard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ((tile00.isX() && tile10.isX() && tile20.isX()) ||
                        (tile01.isX() && tile11.isX() && tile21.isX()) ||
                        (tile02.isX() && tile12.isX() && tile22.isX()) ||
                        (tile00.isX() && tile01.isX() && tile02.isX()) ||
                        (tile10.isX() && tile11.isX() && tile12.isX()) ||
                        (tile20.isX() && tile21.isX() && tile22.isX()) ||
                        (tile00.isX() && tile11.isX() && tile22.isX()) ||
                        (tile20.isX() && tile11.isX() && tile02.isX())) {
                    System.out.println("X player won!");
                    xVictoryCounter++;
                    xScore.setText("X score: " + xVictoryCounter);
                    tile00.tileReset();
                    tile10.tileReset();
                    tile20.tileReset();
                    tile01.tileReset();
                    tile11.tileReset();
                    tile21.tileReset();
                    tile02.tileReset();
                    tile12.tileReset();
                    tile22.tileReset();


                } else if ((tile00.isO() && tile10.isO() && tile20.isO()) ||
                        (tile01.isO() && tile11.isO() && tile21.isO()) ||
                        (tile02.isO() && tile12.isO() && tile22.isO()) ||
                        (tile00.isO() && tile01.isO() && tile02.isO()) ||
                        (tile10.isO() && tile11.isO() && tile12.isO()) ||
                        (tile20.isO() && tile21.isO() && tile22.isO()) ||
                        (tile00.isO() && tile11.isO() && tile22.isO()) ||
                        (tile20.isO() && tile11.isO() && tile02.isO())) {
                    System.out.println("O player won!");
                    oVictoryCounter++;
                    oScore.setText("O score: " + oVictoryCounter);
                    tile00.tileReset();
                    tile10.tileReset();
                    tile20.tileReset();
                    tile01.tileReset();
                    tile11.tileReset();
                    tile21.tileReset();
                    tile02.tileReset();
                    tile12.tileReset();
                    tile22.tileReset();


                } else if (!tile00.playable && !tile10.playable && !tile20.playable &&
                        !tile01.playable && !tile11.playable && !tile21.playable &&
                        !tile02.playable && !tile12.playable && !tile22.playable) {
                    System.out.println("Draw!");
                    tile00.tileReset();
                    tile10.tileReset();
                    tile20.tileReset();
                    tile01.tileReset();
                    tile11.tileReset();
                    tile21.tileReset();
                    tile02.tileReset();
                    tile12.tileReset();
                    tile22.tileReset();
                }
            }
        });


        Scene scene = new Scene (gameboard, TILE_SIZE * 3, TILE_SIZE * 4, Color.DARKSLATEGREY);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        xTurn = true;
        int xVictoryCounter = 0;
        int oVictoryCounter = 0;
        announceTurn();


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


    // Calling Platform.exit() is the preferred way to explicitly terminate a JavaFX Application


    public static void main(String[] args) {
        launch(args);
    }

}
