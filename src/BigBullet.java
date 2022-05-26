import java.awt.*;
import java.util.ArrayList;

public class BigBullet{
    // basic dims
    private int x, y;
    private int w = BULLET_W;
    private int h = BULLET_H;
    //important for bullet rotation
    public static final int BULLET_H = 6;
    public static final int BULLET_W = 8;


    //speed, direction, color
    private int speed = 8;
    //diagonal movement, we can change the slope of the bullet but m=1 works for now
    private int hXSpeed = 8, hYSpeed = 8;
    private String direction = "r";
    private final Color bulletColor = Settings.BIG_BULLET_COLOR;

    public BigBullet(int x,int y, String dir){
        this.x = x;
        this.y = y;
        this.direction = dir;

    }
    public void update(){
        //updates bullet based on direction
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
                this.x += this.hXSpeed;
                this.y -= this.hYSpeed;
        }
        else if(getDirection().equals("lu")){
                this.x -= this.hXSpeed;
                this.y -= this.hYSpeed;
        }
        else if(getDirection().equals("rd")){
                this.x += this.hXSpeed;
                this.y += this.hYSpeed;
        }
        else if(getDirection().equals("ld")){
                this.x -= this.hXSpeed;
                this.y += this.hYSpeed;

        }

    }

    //getters
    public Color getBulletColor() {
        return bulletColor;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}
}
