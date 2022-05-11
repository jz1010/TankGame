import java.awt.*;

public class Tank {
    private int x, y, w, h;
    private int health = 100;
    private int ammo = 100;
    private Color tankColor = Color.RED;
    private int speed = 2;
    private String direction;
    public Tank(int X, int Y, int W, int H){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
    }
    
    public void moveLeft(){this.x -= this.speed;}
    public void moveRight(){this.x += this.speed;}
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }
    public Color getTankColor() {
        return tankColor;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}
