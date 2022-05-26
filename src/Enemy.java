import java.awt.*;
import java.util.Arrays;

public class Enemy{
    //basic dims
    private int x;
    private int y;
    public static final int w = 30;
    public static final int h = 30;

    // Health, speed, direction
    private int health = 60;

    private int speed = 2;
    private String direction="r";

    //these are actually kinda cool
    // enemyColor changes based on HP of enemy, so you can see how much hp they have even though its not displayed
    private  Color enemyColor = Settings.ENEMY_COLOR;
    // this is their id - more in TankProgram
    private int number;
    // their target coordinates for path finding - more below
    private int[] randCoords = new int[]{-1, -1};
    // if theyre a mega enemy or not
    private boolean Mega = false;



    public Enemy(int X, int Y, int num, boolean meg){
        this.x = X;
        this.y = Y;
        this.number = num;
        this.Mega = meg;
        // mega enemies are a bit faster with more HP
        if(meg){
            this.health *= 3;
            this.speed += 1;
        }

    }
    public void update(Tank t){ // gives enemies a point to move to and updates color
        // Pink if mega, green if not
        if(Mega){
            this.enemyColor = new Color(Math.min(255,Math.abs(255 - Math.abs(3 * getHealth()))), 0, Math.min(255,Math.abs(3 * getHealth())));
        }
        else{
            this.enemyColor = new Color(Math.min(Math.abs(255 - Math.abs(3 * getHealth())),255), Math.min(255,Math.abs(3 * getHealth())), 0);
        }

        int randNum = TankProgram.getRandRange(1, 100);
        // if randNum is within the probability threshold, the enemy will path find the tank
        // otherwise it will pathfind a random point

        if(randNum < (int) (Settings.PROBABILITY_PATH_FIND * 100)){
            //pathfinding tank
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
            //pathfinding point
            this.moveRandomDirection();
        }
    }
    public void moveRandomDirection(){ // this is the code to pathfind to randCoords
        if(Arrays.equals(randCoords, new int[]{-1, -1})){
            randCoords[0] = TankProgram.getRandRange(100, 500);
            randCoords[1] = TankProgram.getRandRange(100, 500);
        }
        goToPath();
    }
    public void goToPath(){
        // same logic for pathfinding to tank, just offsets x and y by distance
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

    // this is pretty cool, tells us if the enemy is within the tank for collision detection
    public boolean Contains(Tank t){
        for(int i = t.getX() - t.getW(); i < t.getX() + t.getW();i++){
            for(int j = t.getY() - t.getH(); j < t.getY() + t.getH();j++){
                if(this.x == i && this.y == j){
                    return true;
                }
            }
        }
        return false;
    }

    //getters and setters
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
