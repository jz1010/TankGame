import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tank{
    private int x, y, w, h;
    private int health = 100;
    private int ammo = 100;
    private Color tankColor = Color.RED;
    private int speed = 6;
    private String direction;
    private ArrayList<Integer> center = new ArrayList<>();
    private int rotation = 90;
    private int[] DEFAULT_RECT;
    private int[] DEFAULT_CANNON;
    private int[] hull;
    private int[] cannon;


    public Tank(int X, int Y, int W, int H){
        this.x = X;
        this.y = Y;
        this.w = W;
        this.h = H;
        center.add((2*x + w)/2);
        center.add((2*y + h)/2);
        center.add(3);
        center.add(3);
        DEFAULT_RECT = new int[]{x, y, w, h};
        DEFAULT_CANNON = new int[]{x + (4*w/6) - 2, y+h/2,4*w/6,h/6};
        hull = new int[]{x, y, w, h};
        cannon = new int[]{x + (4*w/6), y+h/2 - 2,4*w/6,h/6};

    }

    public void moveTank(String direction){
        this.direction = direction;
        if(direction.equals("r")){
            //rotate tank
            this.x += speed;
            this.center.set(0, (2*x + hull[2]) /2);
            rotateTank(90);
        }
        else if(direction.equals("l")){
            this.x -= speed;
            this.center.set(0, (2*x + hull[2]) /2);

            rotateTank(270);
        }
        else if(direction.equals("u")){
            this.y -= speed;
            this.center.set(1, (2*y + hull[3]) /2);

            rotateTank(0);
        }
        else if(direction.equals("d")){
            this.y += speed;
            this.center.set(1, (2*y + hull[3]) /2);

            rotateTank(180);
        }
        DEFAULT_RECT[0] = x;
        DEFAULT_RECT[1] = y;
        DEFAULT_CANNON[0] = x + (4*w/6);
        DEFAULT_CANNON[1] = y + h/2;

        this.center.set(0, (2*hull[0] + hull[2]) /2);
        this.center.set(1, (2*hull[1] + hull[3]) /2);


    }
    public void rotateTank(int r){
        this.rotation = r;
        if(this.rotation == 90){
            //looking to the right, pretty standard
            hull = DEFAULT_RECT.clone();
            cannon = DEFAULT_CANNON.clone();
            cannon[1] = DEFAULT_CANNON[1]  - center.get(3);

        }
        else if (this.rotation == 180){
            //downwards, x, is x + w, y is same, w and h change
            hull[0] = DEFAULT_RECT[0] + DEFAULT_RECT[2]/4;
            hull[1] = DEFAULT_RECT[1] - DEFAULT_RECT[3]/4;
            hull[2] = DEFAULT_RECT[3];
            hull[3] = DEFAULT_RECT[2];

            cannon[0] = DEFAULT_CANNON[0] - DEFAULT_CANNON[2]/4 + 2;
            cannon[1] = DEFAULT_CANNON[1] + DEFAULT_CANNON[3];
            cannon[2] = DEFAULT_CANNON[3];
            cannon[3] = DEFAULT_CANNON[2];

        }
        else if (this.rotation == 270){
            //left, same but reversed rotation
            hull = DEFAULT_RECT.clone();
            cannon[0] = center.get(0)  - center.get(2) - DEFAULT_CANNON[2];
            cannon[1] = center.get(1);
            cannon[2] = DEFAULT_CANNON[2];
            cannon[3] = DEFAULT_CANNON[3];
        }
        else{
            //up, x = x + w/4, y = y - height/2, w and h change
            hull[0] = DEFAULT_RECT[0] + DEFAULT_RECT[2]/4;
            hull[1] = DEFAULT_RECT[1] - DEFAULT_RECT[3]/2;
            hull[2] = DEFAULT_RECT[3];
            hull[3] = DEFAULT_RECT[2];

            cannon[0] = center.get(0);
            cannon[1] = center.get(1) - DEFAULT_CANNON[2] - center.get(2);
            cannon[2] = DEFAULT_CANNON[3];
            cannon[3] = DEFAULT_CANNON[2];
        }

    }

    public int[] getHull(){return this.hull;}
    public int[] getCannon() {
        return cannon;
    }
    public Color getTankColor() {
        return tankColor;
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

}
