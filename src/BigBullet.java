import java.awt.*;
import java.util.ArrayList;

public class BigBullet{
    private int x, y, w, h;
    private int damageRadius = Settings.BIG_BULLET_DAMAGE_RADIUS;
    private int speed = 4;
    private int vXSpeed = 2, vYSpeed = 4, hXSpeed = 4, hYSpeed = 2;
    private String direction = "r";
    private final Color bulletColor = Settings.BIG_BULLET_COLOR;

    public BigBullet(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
