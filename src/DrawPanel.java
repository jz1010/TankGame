import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawPanel extends JPanel{
    private final int WIDTH = Settings.DRAW_PANEL_DIMS;
    private final int HEIGHT = Settings.DRAW_PANEL_DIMS;
    private int[][] isPainted = new int[WIDTH][HEIGHT];
    private Color[][] colors = new Color[WIDTH][HEIGHT];

    public void clear(){
            for(int i=0;i<isPainted.length;i++){
                for(int j=0;j<isPainted[0].length;j++){
                isPainted[i][j] = 0;
                }
            }
        repaint();

    }

    @Override
    public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
            }
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Loop through the 2D array and draw a 1x1 rectangle on each pixel that is currently painted
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y] != 0) {
                        g.setColor(colors[x][y]);
                        g.fillRect(x, y, 1, 1);
                    }
                }
            }
    }

    public void drawTank(Tank t){

        if (t.getDirection().equals("r")){
            t.setH(t.getBASE_H());
            t.setW(t.getBASE_W());

            if(t.getX()<WIDTH-50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Settings.PLAYER_COLOR;
                    }
                }
                if(t.getBarrelDirection().equals("r")) {
                    for (int i = t.getX(); i < t.getX() + t.getW(); i++) {
                        for (int j = t.getY() - 3; j <= t.getY() + 3; j++) {
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("u")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY()-t.getW(); j<=t.getY();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("l")){
                    for(int i = t.getX()-t.getW();i<=t.getX();i++){
                        for(int j = t.getY()-3; j<=t.getY()+3;j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("d")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY(); j<=t.getY()+t.getW();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("ru")){
                    for(int x = 0; x < (int) t.getW()/2; x++){
                        int j = t.getY();
                        for(int i = t.getX(); i < t.getX() + t.getW()/2;i++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                            //ADD HEREjdsajfasdfjopajfa
                        }
                }
                }
            }
            else{
                t.returnTank("r");
                drawTank(t);
            }
        }
        else if (t.getDirection().equals("u")){
            t.setH(t.getBASE_W());
            t.setW(t.getBASE_H());
            if(t.getY()>50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Settings.PLAYER_COLOR;
                    }
                }
                if(t.getBarrelDirection().equals("r")) {
                    for (int i = t.getX(); i < t.getX() + t.getH(); i++) {
                        for (int j = t.getY() - 3; j <= t.getY() + 3; j++) {
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("u")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY()-t.getH(); j<=t.getY();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("l")){
                    for(int i = t.getX()-t.getH();i<=t.getX();i++){
                        for(int j = t.getY()-3; j<=t.getY()+3;j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("d")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY(); j<=t.getY()+t.getH();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
            }
            else{
                t.returnTank("u");
                drawTank(t);
            }
        }
        else if (t.getDirection().equals("l")){
            t.setH(t.getBASE_H());
            t.setW(t.getBASE_W());

            if(t.getX()>50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Settings.PLAYER_COLOR;
                    }
                }
                if(t.getBarrelDirection().equals("r")) {
                    for (int i = t.getX(); i < t.getX() + t.getW(); i++) {
                        for (int j = t.getY() - 3; j <= t.getY() + 3; j++) {
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("u")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY()-t.getW(); j<=t.getY();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("l")){
                    for(int i = t.getX()-t.getW();i<=t.getX();i++){
                        for(int j = t.getY()-3; j<=t.getY()+3;j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("d")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY(); j<=t.getY()+t.getW();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
            }
            else{
                t.returnTank("l");
                drawTank(t);
            }
        }
        else if (t.getDirection().equals("d")){
            t.setH(t.getBASE_W());
            t.setW(t.getBASE_H());

            if(t.getY() < HEIGHT - 50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Settings.PLAYER_COLOR;
                    }
                }
                if(t.getBarrelDirection().equals("r")) {
                    for (int i = t.getX(); i < t.getX() + t.getH(); i++) {
                        for (int j = t.getY() - 3; j <= t.getY() + 3; j++) {
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("u")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY()-t.getH(); j<=t.getY();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("l")){
                    for(int i = t.getX()-t.getH();i<=t.getX();i++){
                        for(int j = t.getY()-3; j<=t.getY()+3;j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
                else if(t.getBarrelDirection().equals("d")){
                    for(int i = t.getX()-3;i<=t.getX()+3;i++){
                        for(int j = t.getY(); j<=t.getY()+t.getH();j++){
                            isPainted[i][j] = 1;
                            colors[i][j] = Settings.CANNON_COLOR;
                        }
                    }
                }
            }
            else{
                t.returnTank("d");
                drawTank(t);
            }
        }
        if(Settings.SHOW_CORNERS){
            drawCorners(t);
        }
        repaint();



    }
    public void drawCorners(Tank t){
        //R
        for(int i = (t.getX() + t.getW()/2) - 3; i < t.getX() + t.getW()/2;i++){
            for(int j = (t.getY()); j < (t.getY()) + 3;j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //L
        for(int i = (t.getX() - t.getW()/2); i < (t.getX() - t.getW()/2) + 3;i++){
            for(int j = (t.getY()); j < (t.getY()) + 3;j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //U
        for(int i = (t.getX())-2; i < (t.getX())+2;i++){
            for(int j = (t.getY() - t.getH()/2); j < (t.getY() - t.getH()/2) + 3;j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //D
        for(int i = (t.getX())-2; i < (t.getX()) + 2;i++){
            for(int j = (t.getY() + t.getH()/2)-3; j < (t.getY() + t.getH()/2);j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //RU
        for(int i = (t.getX() + t.getW()/2) - 3; i < t.getX() + t.getW()/2;i++){
            for(int j = (t.getY() - t.getH()/2); j < (t.getY()-t.getH()/2) + 3;j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //LU
        for(int i = (t.getX() - t.getW()/2); i < (t.getX() - t.getW()/2) + 3;i++){
            for(int j = (t.getY() - t.getH()/2); j < (t.getY()-t.getH()/2) + 3;j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //RD
        for(int i = (t.getX() + t.getW()/2) - 3; i < t.getX() + t.getW()/2;i++){
            for(int j = (t.getY() + t.getH()/2)-3; j < (t.getY()+t.getH()/2);j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //LD
        for(int i = (t.getX() - t.getW()/2); i < (t.getX() - t.getW()/2)+3;i++){
            for(int j = (t.getY() + t.getH()/2)-3; j < (t.getY()+t.getH()/2);j++){
                isPainted[i][j] = 4;
                colors[i][j] = Settings.PLAYER_CORNER_COLOR;
            }
        }

        repaint();
    }
    public void drawBullet(Bullet b){
        for(int i = b.getX();i<b.getX()+b.getW();i++){
            for(int j = b.getY();j<b.getY()+b.getH();j++){
                isPainted[i][j] = 2;
                colors[i][j] = b.getBulletColor();
            }
        }
        repaint();
    }
    public void drawBorder(){
        //top
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 600; j++){
                isPainted[i][j] = 3;
                colors[i][j] = Settings.BORDER_COLOR;
            }
        }
        for(int i = 0; i < 600; i++){
            for(int j = 0; j < 30; j++){
                isPainted[i][j] = 3;
                colors[i][j] = Settings.BORDER_COLOR;
            }
        }
        for(int i = 570; i < 600; i++){
            for(int j = 0; j<600;j++){
                isPainted[i][j] = 3;
                colors[i][j] = Settings.BORDER_COLOR;
            }
        }
        for(int i = 0; i<600;i++){
            for(int j = 570;j<600;j++){
                isPainted[i][j]=3;
                colors[i][j]=Color.GRAY;
            }
        }
    }

}