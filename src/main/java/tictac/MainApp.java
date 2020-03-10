package tictac;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
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
/*
    public static boolean scanForX (GridPane gridpane, int col, int row) {

        for (Node node : gridpane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                if(node.toString().contains("X"));
                }
            }
        return false;
    }

 */

    public static boolean scanForX (GridPane gridpane) {
        return gridpane.getChildren().get(0).toString().contains("X");
    }

    public static String scanForIdentifier (GridPane gridpane) {
        return
        gridpane.getChildren().get(0).toString();
    }

    /*
    public boolean chceckRow0() {
        if (tile00.isX() && tile10.isX() && tile20.isX()) {
            return true;
        } else {
            return false;
        }
    }

     */




    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gameboard = new GridPane();
        gameboard.setPrefSize(TILE_SIZE * 3, TILE_SIZE * 3);
        gameboard.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++ ) {
            ColumnConstraints col = new ColumnConstraints(TILE_SIZE);
            gameboard.getColumnConstraints().add(col);
        }

        for (int j = 0; j < 3; j++) {
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
                    gameboard.getChildren().clear();
                    xVictoryCounter++;
                } else if ((tile00.isO() && tile10.isO() && tile20.isO()) ||
                        (tile01.isO() && tile11.isO() && tile21.isO()) ||
                        (tile02.isO() && tile12.isO() && tile22.isO()) ||
                        (tile00.isO() && tile01.isO() && tile02.isO()) ||
                        (tile10.isO() && tile11.isO() && tile12.isO()) ||
                        (tile20.isO() && tile21.isO() && tile22.isO()) ||
                        (tile00.isO() && tile11.isO() && tile22.isO()) ||
                        (tile20.isO() && tile11.isO() && tile02.isO())) {
                    System.out.println("O player won!");
                    gameboard.getChildren().clear();
                    oVictoryCounter++;
                } else if (!tile00.playable && !tile10.playable && !tile20.playable &&
                        !tile01.playable && !tile11.playable && !tile21.playable &&
                        !tile02.playable && !tile12.playable && !tile22.playable) {
                    System.out.println("Draw!");
                    gameboard.getChildren().clear();
                }
            }
        });

        Button newGame = new Button();
        newGame.setText("New Game");



        Scene scene = new Scene (gameboard, TILE_SIZE * 3, TILE_SIZE * 4, Color.DARKSLATEGREY);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        xTurn = true;
        int xVictoryCounter = 0;
        int oVictoryCounter = 0;
        announceTurn();


    }

/*
        // reset
        ObservableList<Node> children = gameboard.getChildren();
        children.clear();
 */

    // Calling Platform.exit() is the preferred way to explicitly terminate a JavaFX Application


    public static void main(String[] args) {
        launch(args);
    }

}
