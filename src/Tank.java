import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class Tank{
    private int x;
    private int y;
    private int w;
    private int h;
    private int d;
    private final int BASE_W;
    private final int BASE_H;
    private int ammo = Settings.BASE_SECOND_AMMO;
    private int health = Settings.PLAYER_STARTING_HEALTH;
    private final int speed = 14;
    private String direction="r";
    private String barrelDirection="r";



    public Tank(int X, int Y, int W, int H, int D){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
        this.d=D;
        this.BASE_W = w;
        this.BASE_H = h;

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
    public void returnTank(String direction) {
        this.direction = direction;
        if (direction.equals("r")) {
            this.x -= speed;
        } else if (direction.equals("l")) {
            this.x += speed;
        } else if (direction.equals("u")) {
            this.y += speed;
        } else if (direction.equals("d")) {
            this.y -= speed;
        }
    }
    public void moveBarrel(String direction) {
        this.barrelDirection = direction;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public int getD(){return this.d;}
    public int getBASE_H() {
        return BASE_H;
    }
    public int getBASE_W() {
        return BASE_W;
    }
    public int getHealth() {
        return health;
    }
    public String getDirection(){return this.direction;}
    public String getBarrelDirection(){return this.barrelDirection;}
    public int getAmmo() {
        return ammo;
    }

    public void decAmmo(){this.ammo -= 1;}
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public void setH(int h) {
        this.h = h;
    }
    public void setW(int w){this.w = w;}
    public void decHealth(){this.health -= Settings.ENEMY_DAMAGE;}
}
