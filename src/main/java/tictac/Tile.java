package tictac;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static tictac.MainApp.*;

public class Tile extends Rectangle {

    boolean playable = true;
    boolean isX;
    boolean isO;

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
                if (playable && gameOn) {
                    setFill(Color.LIGHTGREEN);
                }
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playable && gameOn) {
                    setFill(Color.WHITE);
                }
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playable && gameOn) {
                    if (xTurn && PvP) {
                        Image xImage = new Image("graphics/X.jpg");
                        ImagePattern xPattern = new ImagePattern(xImage);
                        setFill(xPattern);
                        setPlayable(false);
                        setAsX(true);
                        setAsO(false);
                        xTurn = false;
                        textToDisplay = "O player's turn";
                        MainApp.notification.setText(textToDisplay);

                    } else if (!xTurn && PvP) {
                        Image oImage = new Image("graphics/O.jpg");
                        ImagePattern oPattern = new ImagePattern(oImage);
                        setFill(oPattern);
                        setPlayable(false);
                        setAsX(false);
                        setAsO(true);
                        xTurn = true;
                        textToDisplay = "X player's turn";
                        MainApp.notification.setText(textToDisplay);

                    } else if (xTurn && !PvP) {
                        Image xImage = new Image("graphics/X.jpg");
                        ImagePattern xPattern = new ImagePattern(xImage);
                        setFill(xPattern);
                        setPlayable(false);
                        setAsX(true);
                        setAsO(false);
                        computerMove(MainApp.gameboard);
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
        return "Is playable: " + playable +
                " \nIs X: " + isX +
                " \nIs O: " + isO +
                " \nX coordinate: " + getX() +
                " \nY coordinate: " + getY() +
                System.lineSeparator();
    }

    public void computerMovePattern(Tile tile) {
        tile.setPlayable(false);
        tile.setAsO(true);
        tile.setAsX(false);
        Image oImage = new Image("graphics/O.jpg");
        ImagePattern oPattern = new ImagePattern(oImage);
        tile.setFill(oPattern);
    }


}
