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
    private int vXSpeed = 6;
    private int vYSpeed = 6;
    private int hXSpeed = 6;
    private int hYSpeed = 6;
    private final String direction;
    private ArrayList<Integer> center = new ArrayList<>();
    private int rotation = 90;
    private boolean shotVertically = false;



    public Bullet(int X, int Y, int W, int H, String dir, boolean shotVertically){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
        this.direction = dir;
        this.shotVertically = shotVertically;

    }

    public void update(){
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
