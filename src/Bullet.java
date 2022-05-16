import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bullet{
    private int x, y, w, h;
    public static final int BULLET_W = 4;
    public static final int BULLET_H = 2;
    private Color bulletColor = Color.BLACK;
    private int speed = 6;
    private final String direction;
    private ArrayList<Integer> center = new ArrayList<>();
    private int rotation = 90;



    public Bullet(int X, int Y, int W, int H, String dir){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
        this.direction = dir;

    }

    public void moveTank(String direction){

    }

    public Color getBulletColor() {
        return bulletColor;
    }
    public ArrayList<Integer> getCenter() {
        return center;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getRotation(){return rotation;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}

}
