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

    public void drawTank(Tank t, Color c){
        System.out.println(t.getDirection());
        //draw a rectangle where the tank should be in the grid
        if (t.getDirection().equals("r")){
            if(t.getX()<WIDTH-50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = c;
                    }
                }
                for(int i = t.getX();i<t.getX()+t.getW();i++){
                    for(int j = t.getY()-3; j<=t.getY()+3;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Color.GREEN;
                    }
                }
            }
        }
        else if (t.getDirection().equals("u")){
            if(t.getY()>50){
                for(int i=t.getX()-t.getH()/2;i<t.getX()+t.getH()/2;i++){
                    for(int j=t.getY()-t.getW()/2;j<t.getY()+t.getW()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = c;
                    }
                }
                for(int i = t.getX()-3;i<=t.getX()+3;i++){
                    for(int j = t.getY()-t.getW(); j<=t.getY();j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Color.GREEN;
                    }
                }
            }
        }
        else if (t.getDirection().equals("l")){
            if(t.getX()>50){
                for(int i=t.getX()-t.getW()/2;i<t.getX()+t.getW()/2;i++){
                    for(int j=t.getY()-t.getH()/2;j<t.getY()+t.getH()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = c;
                    }
                }
                for(int i = t.getX()-t.getW();i<=t.getX();i++){
                    for(int j = t.getY()-3; j<=t.getY()+3;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Color.GREEN;
                    }
                }
            }
        }
        else if (t.getDirection().equals("d")){
            if(t.getY() < HEIGHT - 50){
                for(int i=t.getX()-t.getH()/2;i<t.getX()+t.getH()/2;i++){
                    for(int j=t.getY()-t.getW()/2;j<t.getY()+t.getW()/2;j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = c;
                    }
                }
                for(int i = t.getX()-3;i<=t.getX()+3;i++){
                    for(int j = t.getY(); j<=t.getY()+t.getW();j++){
                        isPainted[i][j] = 1;
                        colors[i][j] = Color.GREEN;
                    }
                }
            }
        }
        repaint();

    }
    public void update(){
        repaint();
    }

}