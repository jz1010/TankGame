import java.awt.*;
import java.util.ArrayList;

public class BigBullet{
    private int x, y;
    private int damageRadius = Settings.BIG_BULLET_DAMAGE_RADIUS;
    private int speed = 4;
    private int vXSpeed = 2, vYSpeed = 4, hXSpeed = 3, hYSpeed = 3;
    private String direction = "r";
    private final Color bulletColor = Settings.BIG_BULLET_COLOR;
    public static final int BULLET_H = 6;
    public static final int BULLET_W = 4;
    private int w = BULLET_W;
    private int h = BULLET_H;

    public BigBullet(int x,int y, String dir){
        this.x = x;
        this.y = y;
        this.direction = dir;

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

    public Color getBulletColor() {
        return bulletColor;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}
}
