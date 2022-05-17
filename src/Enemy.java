import java.awt.*;
import java.util.ArrayList;

public class Enemy{
    private int x;
    private int y;
    private final int w;
    private final int h;
    private int health = 100;
    private int ammo = 30;
    private final Color enemyColor = Color.RED;
    private final int speed = 14;
    private String direction="r";



    public Enemy(int X, int Y, int W, int H){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;

    }

    public void moveEnemy(String direction){
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
    public void returnEnemy(String direction) {
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

    public Color getEnemyColor() {
        return enemyColor;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public String getDirection(){return this.direction;}
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