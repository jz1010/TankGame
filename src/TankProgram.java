import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    Tank p1, p2;
    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();

        frame = new JFrame("Tank Program");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new DrawPanel();
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.drawTank(p1);
        mainPanel.drawTank(p2);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    public void init(){
        p1 = new Tank(100, 300, 40, 20);
        p2 = new Tank(400, 300, 40, 20);
    }
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}