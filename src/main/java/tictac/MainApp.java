package tictac;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    public static int TILE_SIZE = 200;
    public static boolean xTurn;

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

    public static void announceTurn(){
        if (xTurn) {
            System.out.println("X player's turn ");
        } else {
            System.out.println("O player's turn");
        }
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gameboard = newGame();

        Scene scene = new Scene (gameboard, TILE_SIZE * 3, TILE_SIZE * 4, Color.DARKSLATEGREY);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
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
