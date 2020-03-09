package tictac;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile (int x, int y, int width, int height) {
        super(x, y, width, height);
        setFill(null);
        setStroke(Color.BLACK);
        setHeight(MainApp.TILE_SIZE);
        setWidth(MainApp.TILE_SIZE);
        setFill(Color.WHITE);
    }




}
