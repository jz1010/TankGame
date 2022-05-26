import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bullet{
    //dims
    private int x, y, w, h;
    //diagonal rotation
    public static final int BULLET_W = 2;
    public static final int BULLET_H = 2;

    //color, speed, direction
    //v and h speed is for diagonal, 1.4 is sqrt 2
    private Color bulletColor = Color.BLACK;
    private int speed = 6;
    private int vXSpeed = (int)(6/1.4);
    private int vYSpeed = (int)(6/1.4);
    private int hXSpeed = (int)(6/1.4);
    private int hYSpeed = (int)(6/1.4);
    private final String direction;
    private ArrayList<Integer> center = new ArrayList<>();
    //only necessary for rectangular tank
    private int rotation = 90;
    //this is for v and h speed
    private boolean shotVertically = false;



    public Bullet(int X, int Y, int W, int H, String dir, boolean shotVertically){
        //boring constructor stuff
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
        this.direction = dir;
        this.shotVertically = shotVertically;

    }

    public void update(){
        //updates based on directions
        if(getDirection().equals("r")){
            this.x += this.speed;
        }
        else if(getDirection().equals("l")){
            this.x -= this.speed;
        }
        else if(getDirection().equals("u")){
            this.y -= this.speed;
        }
        else if(getDirection().equals("d")){
            this.y += this.speed;
        }
        else if(getDirection().equals("ru")){
            if(shotVertically){
                this.x += this.vXSpeed;
                this.y -= this.vYSpeed;
            }
            else{
                this.x += this.hXSpeed;
                this.y -= this.hYSpeed;
            }
        }
        else if(getDirection().equals("lu")){
            if(shotVertically){
                this.x -= this.vXSpeed;
                this.y -= this.vYSpeed;
            }
            else{
                this.x -= this.hXSpeed;
                this.y -= this.hYSpeed;
            }
        }
        else if(getDirection().equals("rd")){
            if(shotVertically){
                this.x += this.vXSpeed;
                this.y += this.vYSpeed;
            }
            else{
                this.x += this.hXSpeed;
                this.y += this.hYSpeed;
            }
        }
        else if(getDirection().equals("ld")){
            if(shotVertically){
                this.x -= this.vXSpeed;
                this.y += this.vYSpeed;
            }
            else{
                this.x -= this.hXSpeed;
                this.y += this.hYSpeed;
            }
        }

    }

    //getters
    public Color getBulletColor() {
        return bulletColor;
    }
    public ArrayList<Integer> getCenter() {
        return center;
    }
    public int getRotation(){return rotation;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}

}
