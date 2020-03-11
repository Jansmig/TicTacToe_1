package tictac;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javax.swing.undo.CannotUndoException;

import static tictac.MainApp.*;

public class Tile extends Rectangle {

    boolean playable = true;
    boolean isX;
    boolean isO;
    String Identifier; // out

    public Tile(int x, int y, int width, int height) {
        super(x, y, width, height);
        setFill(null);
        setStroke(Color.BLACK);
        setHeight(MainApp.TILE_SIZE);
        setWidth(MainApp.TILE_SIZE);
        setFill(Color.WHITE);


        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playable) {
                    setFill(Color.LIGHTGREEN);
                }
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playable) {
                    setFill(Color.WHITE);
                }
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playable) {
                    if (xTurn) {
                        Image xImage = new Image("graphics/X.jpg");
                        ImagePattern xPattern = new ImagePattern(xImage);
                        setFill(xPattern);
                        setPlayable(false);
                        setAsX(true);
                        setAsO(false);
                        setIdentifier("X");
                        xTurn = false;
                        textToDisplay = "O player's turn";
                        MainApp.notification.setText(textToDisplay);
                        //announceTurn();
                    } else {
                        Image oImage = new Image("graphics/O.jpg");
                        ImagePattern oPattern = new ImagePattern(oImage);
                        setFill(oPattern);
                        setPlayable(false);
                        setAsX(false);
                        setAsO(true);
                        setIdentifier("O");
                        xTurn = true;
                        textToDisplay = "X player's turn";
                        MainApp.notification.setText(textToDisplay);
                        //announceTurn();
                    }


                }
            }
        });


    }

    public void setPlayable(boolean bool) {
        this.playable = bool;
    }

    public void setAsX(boolean bool) {
        isX = bool;
    }

    public void setAsO(boolean bool) {
        isO = bool;
    }

    public void setIdentifier(String str) {
        Identifier = str;
    }

    public boolean isPlayable() {
        return playable;
    }

    public boolean isX() {
        return isX;
    }

    public boolean isO() {
        return isO;
    }

    public void tileReset() {
        this.playable = true;
        this.isX = false;
        this.isO = false;
        setFill(Color.WHITE);
    }

    @Override
    public String toString() {
        return Identifier;
    }

    /*
    public Node getNode(GridPane grid, int col, int row) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            } else {
                return null;
            }
        }
    }

     */

    /*
    public static boolean scanForX (GridPane grid, int col, int row) {

        for (Node tile : grid.getChildren()) {
            if (GridPane.getColumnIndex(tile) == col && GridPane.getRowIndex(tile) == row) {
                if(tile.isX()) {
                    return true;
                }

            }
        }
    }

    */


}
