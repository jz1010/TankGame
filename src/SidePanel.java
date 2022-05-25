import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class SidePanel extends JPanel{
    private boolean[][] isPainted;
    private Color[][] colors;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;

    public SidePanel() {
        // Set background color
        setBackground(Color.WHITE);

        // Add mouse listeners
        // Initialize instance variables
        isPainted = new boolean[WIDTH][HEIGHT];
        colors = new Color[WIDTH][HEIGHT];
    }

    // Can be called to change the current mode of the drawing panel
    public void clear(){
        for(int i=0;i<isPainted.length;i++){
            for(int j=0;j<isPainted[0].length;j++){
                isPainted[i][j]=false;
                repaint();
            }
        }
    }

    // Sets the size of the DrawingPanel, so frame.pack() considers its preferred size when sizing the JFrame

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    // This is the method that draws everything

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Loop through the 2D array and draw a 1x1 rectangle on each pixel that is currently painted
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (isPainted[x][y]) {


                    g.setColor(colors[x][y]);
                    g.fillRect(x, y, 1, 1);

                }
            }
        }
    }
    public void drawAmmo(Tank t){
        /*for(int i = 50;i<(t.getAmmo() * 2) + 50;i+=2){
            for(int j = 30;j<60;j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }*/
    }
    public void drawBigBullet(){
        for(int i = 50;i<100;i+=1){
            for(int j = 30;j<60;j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.ORANGE;
            }
        }
        for(int j = 40;j<50;j++){
            for(int i = 100;i<120;i++){
                isPainted[i][j] = true;
                colors[i][j] = Color.ORANGE;
            }
        }
    }
    public void drawBigBulletRed(){
        for(int i = 50;i<100;i+=1){
            for(int j = 30;j<60;j++){
                isPainted[i][j] = true;
                colors[i][j] = new Color(99, 35, 0);
            }
        }
        for(int j = 40;j<50;j++){
            for(int i = 100;i<120;i++){
                isPainted[i][j] = true;
                colors[i][j] = new Color(99, 35, 0);
            }
        }
    }
    public void drawHP(Tank t){
        //Draw HP from 80 - 100
        for(int i = 50; i < 55; i++){
            for(int j = 80; j < 100; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }
        for(int i = 65; i < 70; i++){
            for(int j = 80; j < 100; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }
        for(int i = 55; i < 65; i++){
            for(int j = 89; j < 93; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }
        for(int i = 73; i < 78; i++){
            for(int j = 80; j < 100; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }
        for(int i = 78; i < 86; i++){
            for(int j = 80; j < 84; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }

        for(int i = 78; i < 86; i++){
            for(int j = 90; j < 94; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }
        for(int i = 86; i < 90; i++){
            for(int j = 80; j < 94d; j++){
                isPainted[i][j] = true;
                colors[i][j] = Color.BLACK;
            }
        }



        int numGreenCells = t.getHealth();
        int i = 50;
        int k = 120;
        for(int j = 0; j < 10; j++){
            //draw a cell for each 1 in the 10
            for(int x = 0; x < 10; x++){
                for(int z = i; z < i + 8; z++){
                    for(int l = k; l < k + 2;l++){
                        isPainted[z][l] = true;
                        if((j * 10) + x < t.getHealth()){
                            colors[z][l] = Color.GREEN;
                        }
                        else{
                            colors[z][l] = Color.RED;
                        }
                    }


                }
                k+=2;
            }
            i += 10;
            k = 120;
        }
    }

}