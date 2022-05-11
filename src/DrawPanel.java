import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawPanel extends JPanel{
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private boolean[][] isPainted = new boolean[WIDTH][HEIGHT];
    private Color[][] colors = new Color[WIDTH][HEIGHT];
    private String mode = "Pencil";
    private Color color = Color.BLACK;

    public void setMode(String mode) {
            this.mode = mode;
            }

    public void setColor(Color color) {
            this.color = color;
            }

    public void clear(){
            for(int i=0;i<isPainted.length;i++){
            for(int j=0;j<isPainted[0].length;j++){
            isPainted[i][j]=false;
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
            if (isPainted[x][y]) {


            g.setColor(colors[x][y]);
            g.fillRect(x, y, 1, 1);

            }
            }
            }
            }

    public void drawing(int x, int y, String mode){
            }

    public void mousePressed(MouseEvent e) {
            // Check the current mode
            int x=e.getX();
            int y=e.getY();

            }

    public void mouseDragged(MouseEvent e) {
            mousePressed(e);
            }

    public void mouseReleased(MouseEvent e) {
            repaint();
            }
    public void drawTank(Tank t){
        //draw a rectangle where the tank should be in the grid
        for(int i = t.getX(); i < t.getX() + t.getW(); i++){
            for(int j = t.getY(); j < t.getY() + t.getH(); j++){
                isPainted[i][j] = true;
                colors[i][j] = t.getTankColor();
            }
        }
        repaint();
    }
}