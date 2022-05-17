import java.awt.*;
import java.util.ArrayList;

public class Tank{
    private int x;
    private int y;
    private final int w;
    private final int h;
    private int health = 100;
    private int ammo = 30;
    private final Color tankColor = Color.RED;
    private final int speed = 14;
    private String direction="r";
    private String barrelDirection="r";



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

    public Color getTankColor() {
        return tankColor;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}
    public String getBarrelDirection(){return this.barrelDirection;}
    public int getHealth() {
        return health;
    }
    public int getAmmo() {
        return ammo;
    }

    public void decAmmo(){this.ammo -= 1;}
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
