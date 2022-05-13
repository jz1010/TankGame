import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    Tank p1, p2;
    AffineTransform tx = new AffineTransform();
    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();

        frame = new JFrame("Tank Program");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new DrawPanel();
        mainPanel.drawTank(p1, Color.BLUE);
        //mainPanel.drawTank(p2, Color.RED);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 39){
                    p1.moveTank("r");
                }
                else if (e.getKeyCode() == 37){
                    p1.moveTank("l");
                }
                else if (e.getKeyCode() == 38){
                    p1.moveTank("u");
                }
                else if (e.getKeyCode() == 40){
                    p1.moveTank("d");
                }

                /*if (e.getKeyCode() == 65){
                    p2.moveTank("l");
                }
                else if (e.getKeyCode() == 83){
                    p2.moveTank("d");
                }
                else if (e.getKeyCode() == 68){
                    p2.moveTank("r");
                }
                else if (e.getKeyCode() == 87){
                    p2.moveTank("u");
                }*/

                mainPanel.clear();
                mainPanel.drawTank(p1, Color.BLUE);
                //mainPanel.drawTank(p2, Color.RED);

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.pack();
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {

    }

    public void init(){
        //System.out.println("Initializing...");
        p1 = new Tank(125, 500, 50, 30);
        //p2 = new Tank(425, 500, 50, 30);
    }
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}