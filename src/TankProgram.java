import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.KeyStore;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    Tank p1, p2;
    Terrain ground;
    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();

        frame = new JFrame("Tank Program");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new DrawPanel();
        mainPanel.drawTank(p1, p1.getTankColor());
        mainPanel.drawTank(p2, p2.getTankColor());
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 37){
                    p1.moveLeft();
                }
                else if (e.getKeyCode() == 39){
                    p1.moveRight();
                }
                else if (e.getKeyCode() == 38){
                    p1.moveUp();
                }
                else if (e.getKeyCode() == 40){
                    p1.moveDown();
                }
                mainPanel.clear();
                mainPanel.drawTank(p1, p1.getTankColor());
                mainPanel.drawTank(p2, p2.getTankColor());

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        update();
        System.out.println(p1.getX());


    }

    public void actionPerformed(ActionEvent ae) {

    }

    public void init(){
        p1 = new Tank(125, 500, 50, 30);
        p2 = new Tank(425, 500, 50, 30);
        ground = new Terrain(550);
    }
    public void update(){
        p1.moveLeft();
    }
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}