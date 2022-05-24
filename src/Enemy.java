import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Enemy{
    private int x;
    private int y;
    public static final int w = 30;
    public static final int h = 30;
    private int health = 60;
    private  Color enemyColor = Settings.ENEMY_COLOR;
    private final int speed = 2;
    private String direction="r";
    private int number;
    private int[] randCoords = new int[]{-1, -1};
    private boolean Mega = false;



    public Enemy(int X, int Y, int num, boolean meg){
        this.x = X;
        this.y = Y;
        this.number = num;
        this.Mega = meg;
        if(meg){
            this.health *= 3;
        }

    }
    public void update(Tank t){
        if(Mega){
            this.enemyColor = new Color(Math.min(255,Math.abs(255 - Math.abs(3 * getHealth()))), 0, Math.min(255,Math.abs(3 * getHealth())));
        }
        else{
            this.enemyColor = new Color(Math.min(Math.abs(255 - Math.abs(3 * getHealth())),255), Math.min(255,Math.abs(3 * getHealth())), 0);
        }
        int randNum = TankProgram.getRandRange(1, 100);
        if(randNum < (int) (Settings.PROBABILITY_PATHFIND * 100)){
            if(t.getX() > this.x){
                //move right
                this.x += this.speed;
            }
            else if (t.getX() < this.x){
                //move left
                this.x -= this.speed;
            }
            if(t.getY() < this.y){
                //move up
                this.y -= this.speed;
            }
            else if (t.getY() > this.y){
                //move down
                this.y += this.speed;
            }

        }
        else{
            this.moveRandomDirection();
        }
    }
    public void moveRandomDirection(){
        if(Arrays.equals(randCoords, new int[]{-1, -1})){
            randCoords[0] = TankProgram.getRandRange(100, 500);
            randCoords[1] = TankProgram.getRandRange(100, 500);
        }
        goToPath();
    }
    public void goToPath(){
        if(randCoords[0] > this.x){
            //move right
            this.x += this.speed;
        }
        else if (randCoords[0] < this.x){
            //move left
            this.x -= this.speed;
        }
        if(randCoords[1] < this.y){
            //move up
            this.y -= this.speed;
        }
        else if (randCoords[1] > this.y){
            //move down
            this.y += this.speed;
        }
        if(Math.abs(randCoords[0] - getX()) < 10 && Math.abs(randCoords[1] - getY()) < 10){
            randCoords[0] = -1;
            randCoords[1] = -1;
        }

    }

    public Color getEnemyColor() {
        return enemyColor;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.w;}
    public int getH(){return this.h;}
    public boolean isMega() {
        return Mega;
    }

    public String getDirection(){return this.direction;}
    public int getHealth() {
        return health;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void takeDamage(int dmg){
        this.health -= dmg;
    }
}
