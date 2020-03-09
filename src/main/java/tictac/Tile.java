package tictac;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static tictac.MainApp.xTurn;

public class Tile extends Rectangle {

    boolean playable = true;
    boolean isX;
    boolean isO;

    public Tile (int x, int y, int width, int height) {
        super(x, y, width, height);
        setFill(null);
        setStroke(Color.BLACK);
        setHeight(MainApp.TILE_SIZE);
        setWidth(MainApp.TILE_SIZE);
        setFill(Color.WHITE);

        /*
        Image xImage = new Image("resources/graphics/X.jpg");
        Image oImage = new Image("resources/graphics/O.jpg");
        ImagePattern xPattern = new ImagePattern(xImage);
        ImagePattern yPattern = new ImagePattern(oImage);

         */

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
                        xTurn = false;
                    } else {
                        Image oImage = new Image("graphics/O.jpg");
                        ImagePattern oPattern = new ImagePattern(oImage);
                        setFill(oPattern);
                        setPlayable(false);
                        setAsX(false);
                        setAsO(true);
                        xTurn = true;
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
}
