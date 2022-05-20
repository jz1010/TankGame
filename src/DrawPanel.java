import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Set;


public class DrawPanel extends JPanel{
    private final int WIDTH = Settings.DRAW_PANEL_DIMS;
    private final int HEIGHT = Settings.DRAW_PANEL_DIMS;
    private int[][] isPainted = new int[WIDTH][HEIGHT];
    private Color[][] colors = new Color[WIDTH][HEIGHT];

    //mats for each object for collision detection
    public int[][] tankMat = new int[WIDTH][HEIGHT];
    public int[][] bulletMat = new int[WIDTH][HEIGHT];
    public int[][] enemyMat = new int[WIDTH][HEIGHT];

    public void clear(){
            for(int i=0;i<isPainted.length;i++){
                for(int j=0;j<isPainted[0].length;j++){
                isPainted[i][j] = 0;
                tankMat[i][j] = 0;
                bulletMat[i][j] = 0;
                enemyMat[i][j] = 0;
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

            if(t.getX()<WIDTH-50) {
                for (int i = t.getX() - t.getW() / 2; i < t.getX() + t.getW() / 2; i++) {
                    for (int j = t.getY() - t.getH() / 2; j < t.getY() + t.getH() / 2; j++) {
                        isPainted[i][j] = 1;
                        colors[i][j] = Settings.PLAYER_COLOR;
                        tankMat[i][j] = 1;
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
                        tankMat[i][j] = 1;
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
                        tankMat[i][j] = 1;
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
                        tankMat[i][j] = 1;
                    }
                }

            }
            else{
                t.returnTank("d");
                drawTank(t);
            }
        }
        if(t.getBarrelDirection().equals("r")) {
            for (int i = t.getX(); i < t.getX() + t.getW(); i++) {
                for (int j = t.getY() - 3; j <= t.getY() + 3; j++) {
                    isPainted[i][j] = 1;
                    colors[i][j] = Settings.CANNON_COLOR;
                    tankMat[i][j] = 1;
                }
            }
        }
        else if(t.getBarrelDirection().equals("u")){
            for(int i = t.getX()-3;i<=t.getX()+3;i++){
                for(int j = t.getY()-t.getW(); j<=t.getY();j++){
                    isPainted[i][j] = 1;
                    colors[i][j] = Settings.CANNON_COLOR;
                    tankMat[i][j] = 1;
                }
            }
        }
        else if(t.getBarrelDirection().equals("l")){
            for(int i = t.getX()-t.getW();i<=t.getX();i++){
                for(int j = t.getY()-3; j<=t.getY()+3;j++){
                    isPainted[i][j] = 1;
                    colors[i][j] = Settings.CANNON_COLOR;
                    tankMat[i][j] = 1;
                }
            }
        }
        else if(t.getBarrelDirection().equals("d")){
            for(int i = t.getX()-3;i<=t.getX()+3;i++){
                for(int j = t.getY(); j<=t.getY()+t.getW();j++){
                    isPainted[i][j] = 1;
                    colors[i][j] = Settings.CANNON_COLOR;
                    tankMat[i][j] = 1;
                }
            }
        }
        else if(t.getBarrelDirection().equals("ru")){
            for(int i=0;i<(int)(t.getW()/1.4);i++){
                for(int k = t.getX()+i-3; k < t.getX()+i+3; k++){
                    for(int l = t.getY()-i - 3; l < t.getY()-i+3; l++){
                        isPainted[k][l] = 1;
                        colors[k][l] = Settings.CANNON_COLOR;
                        tankMat[k][l] = 1;
                    }
                }
            }
        }
        else if(t.getBarrelDirection().equals("lu")){
            for(int i=0;i<(int)(t.getW()/1.4);i++){
                for(int k = t.getX()-i-3; k < t.getX()-i+3; k++){
                    for(int l = t.getY()-i - 3; l < t.getY()-i+3; l++){
                        isPainted[k][l] = 1;
                        colors[k][l] = Settings.CANNON_COLOR;
                        tankMat[k][l] = 1;

                    }
                }
            }
        }
        else if(t.getBarrelDirection().equals("rd")){
            for(int i=0;i<(int)(t.getW()/1.4);i++){
                for(int k = t.getX()+i-3; k < t.getX()+i+3; k++){
                    for(int l = t.getY()+i - 3; l < t.getY()+i+3; l++){
                        isPainted[k][l] = 1;
                        colors[k][l] = Settings.CANNON_COLOR;
                        tankMat[k][l] = 1;

                    }
                }
            }
        }
        else if(t.getBarrelDirection().equals("ld")){
            for(int i=0;i<(int)(t.getW()/1.4);i++){
                for(int k = t.getX()-i-3; k < t.getX()-i+3; k++){
                    for(int l = t.getY()+i - 3; l < t.getY()+i+3; l++){
                        isPainted[k][l] = 1;
                        colors[k][l] = Settings.CANNON_COLOR;
                        tankMat[k][l] = 1;

                    }
                }
            }
        }

        if(Settings.SHOW_CORNERS){
            drawCorners(t);
        }
        repaint();



    }
    public void drawEnemy(Enemy e){
        for(int i = e.getX() - e.getW()/2; i < e.getX() + e.getW()/2; i++){
            for(int j = e.getY()- e.getH()/2; j < e.getY() + e.getH()/2; j++){
                isPainted[i][j] = 5;
                colors[i][j] = e.getEnemyColor();
                enemyMat[i][j] = 1;

            }
        }
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
        for(double i = (t.getX() + t.getW()/2) - 3; i < t.getX() + t.getW()/2;i++){
            for(double j = (t.getY() - t.getH()/2); j < (t.getY()-t.getH()/2) + 3;j++){
                isPainted[(int)i][(int)j] = 4;
                colors[(int) i][(int) j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //LU
        for(double i = (t.getX() - t.getW()/2); i < (t.getX() - t.getW()/2) + 3;i++){
            for(double j = (t.getY() - t.getH()/2); j < (t.getY()-t.getH()/2) + 3;j++){
                isPainted[(int)i][(int)j] = 4;
                colors[(int) i][(int) j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //RD
        for(double i = (t.getX() + t.getW()/2) - 3; i < t.getX() + t.getH()/2;i++){
            for(double j = (t.getY() + t.getH()/2) - 3; j < (t.getY()+t.getH()/2);j++){
                isPainted[(int)i][(int)j] = 4;
                colors[(int) i][(int) j] = Settings.PLAYER_CORNER_COLOR;
            }
        }
        //LD
        for(double i = (t.getX() - t.getW()/2); i < (t.getX() - t.getW()/2)+3;i++){
            for(double j = (t.getY() + t.getH()/2)-3; j < (t.getY()+t.getH()/2);j++){
                isPainted[(int)i][(int)j] = 4;
                colors[(int) i][(int) j] = Settings.PLAYER_CORNER_COLOR;
            }
        }


        repaint();
    }
    public void drawBullet(Bullet b){
        if(b.getDirection().equals("d") || b.getDirection().equals("l") || b.getDirection().equals("u") || b.getDirection().equals("r")){
            for(int i = b.getX();i<b.getX()+b.getW();i++){
                for(int j = b.getY();j<b.getY()+b.getH();j++){
                    isPainted[i][j] = 2;
                    colors[i][j] = b.getBulletColor();
                    bulletMat[i][j] = 1;

                }
            }
        }
        else if (b.getDirection().equals("ru")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 1; k < i + 1; k++){
                        for(int l = j - 1; l < j + 1; l++){
                            isPainted[k][l] = 2;
                            colors[k][l] = Settings.SMALL_BULLET_COLOR;
                            bulletMat[k][l] = 1;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
            }

        }
        else if (b.getDirection().equals("lu")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 1; k < i + 1; k++){
                        for(int l = j - 1; l < j + 1; l++){
                            isPainted[k][l] = 2;
                            colors[k][l] = Settings.SMALL_BULLET_COLOR;
                            bulletMat[k][l] = 1;


                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
            }
        }
        else if (b.getDirection().equals("rd")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 1; k < i + 1; k++){
                        for(int l = j - 1; l < j + 1; l++){
                            isPainted[k][l] = 2;
                            colors[k][l] = Settings.SMALL_BULLET_COLOR;
                            bulletMat[k][l] = 1;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j+=2;
                    }
                }
            }
        }
        else if (b.getDirection().equals("ld")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 1; k < i + 1; k++){
                        for(int l = j - 1; l < j + 1; l++){
                            isPainted[k][l] = 2;
                            colors[k][l] = Settings.SMALL_BULLET_COLOR;
                            bulletMat[k][l] = 1;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
            }
        }
        repaint();
    }
    public void drawBigBullet(BigBullet b){
        if(b.getDirection().equals("d") || b.getDirection().equals("l") || b.getDirection().equals("u") || b.getDirection().equals("r")){
            for(int i = b.getX();i<b.getX()+b.getW();i++){
                for(int j = b.getY();j<b.getY()+b.getH();j++){
                    isPainted[i][j] = 4;
                    colors[i][j] = Settings.BIG_BULLET_COLOR;
                    bulletMat[i][j] = 2;

                }
            }
        }
        else if (b.getDirection().equals("ru")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 3; k < i + 3; k++){
                        for(int l = j - 3; l < j + 3; l++){
                            isPainted[k][l] = 4;
                            colors[k][l] = Settings.BIG_BULLET_COLOR;
                            bulletMat[k][l] = 2;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
            }

        }
        else if (b.getDirection().equals("lu")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 3; k < i + 3; k++){
                        for(int l = j - 3; l < j + 3; l++){
                            isPainted[k][l] = 4;
                            colors[k][l] = Settings.BIG_BULLET_COLOR;
                            bulletMat[k][l] = 2;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
            }
        }
        else if (b.getDirection().equals("rd")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 3; k < i + 3; k++){
                        for(int l = j - 3; l < j + 3; l++){
                            isPainted[k][l] = 4;
                            colors[k][l] = Settings.BIG_BULLET_COLOR;
                            bulletMat[k][l] = 2;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j+=2;
                    }
                }
            }
        }
        else if (b.getDirection().equals("ld")){
            int counter = 0;
            for(int x = 0; x < (int) b.getH(); x++){
                int j = b.getY() + b.getW();
                for(int i = b.getX() - b.getH()/2; i < b.getX();i++){
                    //Draw square
                    for(int k = i - 3; k < i + 3; k++){
                        for(int l = j - 3; l < j + 3; l++){
                            isPainted[k][l] = 4;
                            colors[k][l] = Settings.BIG_BULLET_COLOR;
                            bulletMat[k][l] = 2;

                        }
                    }
                    counter++;
                    if(counter%3 == 0 && counter!=0){
                        j-=2;
                    }
                }
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