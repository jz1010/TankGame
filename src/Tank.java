import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tank{
    private int x, y, w, h;
    private int health = 100;
    private int ammo = 100;
    private Color tankColor = Color.RED;
    private int speed = 6;
    private String direction="r";
    private ArrayList<Integer> center = new ArrayList<>();
    private int rotation = 90;



    public Tank(int X, int Y, int W, int H){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;

    }

    public void moveTank(String direction){
        this.direction = direction;
        if(direction.equals("r")){
            //rotate tank

            this.x += speed;

        }
        else if(direction.equals("l")){
            this.x -= speed;


        }
        else if(direction.equals("u")){
            this.y -= speed;


        }
        else if(direction.equals("d")){
            this.y += speed;


        }



    }

    public Color getTankColor() {
        return tankColor;
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
