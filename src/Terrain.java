import java.awt.*;

public class Terrain {
    private final int x = 0;
    private int y;
    private final int WIDTH = 600;
    private int HEIGHT;
    private Color groundColor = Color.GREEN;
    public Terrain(int Y){
        this.y = Y;
        this.HEIGHT = 600 - this.y;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
    public int getWIDTH() {
        return WIDTH;
    }
    public Color getGroundColor() {
        return groundColor;
    }
}
