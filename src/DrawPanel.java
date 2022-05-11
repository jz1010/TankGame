import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawPanel extends JPanel{
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private int[][] isPainted = new int[WIDTH][HEIGHT];
    private Color[][] colors = new Color[WIDTH][HEIGHT];

    public void clear(){
            for(int i=0;i<isPainted.length;i++){
            for(int j=0;j<isPainted[0].length;j++){
            isPainted[i][j] = 0;
            repaint();
            }
            }
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

    public void drawTank(Tank t, Color c){
        //draw a rectangle where the tank should be in the grid
        for(int i = t.getHull()[0]; i < t.getHull()[0] + t.getHull()[2]; i++){
            for(int j = t.getHull()[1]; j < t.getHull()[1] + t.getHull()[3]; j++){
                isPainted[i][j] = 1;
                if (Math.abs(i - t.getCenter().get(0)) < 3 && Math.abs(j - t.getCenter().get(1)) < 3){
                    colors[i][j] = Color.BLACK;
                }
                else{
                    colors[i][j] = c;
                }

            }
        }
        for(int i = t.getCannon()[0];i<t.getCannon()[0] + t.getCannon()[2];i++){
            for(int j = t.getCannon()[1]; j<t.getCannon()[1]+t.getCannon()[3];j++){
                isPainted[i][j] = 2;
                colors[i][j] = Color.GREEN;
            }
        }
    }
    public void update(){
        repaint();
    }

}