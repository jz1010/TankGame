import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    Tank p1, p2;
    AffineTransform tx = new AffineTransform();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    Timer timer;

    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();


        frame = new JFrame("Tank Program");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.drawTank(p1, Color.BLUE);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 16){
                    addBullet();
                }
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



            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //update cycle
        System.out.println(timer.getDelay());
        frame.pack();
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        mainPanel.clear();
        mainPanel.drawTank(p1, Color.BLUE);
        int counter = 0;
        while(counter < bullets.size()){
            Bullet bullet = bullets.get(counter);
            bullet.update();
            if((bullet.getX() + bullet.getW() > 600) || (bullet.getX() <0) ||(bullet.getY() < 0) || (bullet.getY() +bullet.getH()>600)){
                bullets.remove(counter);
                counter--;
            }
            else{
                mainPanel.drawBullet(bullet);

            }
            counter ++;
        }

        System.out.println(timer.getDelay());


    }

    public void init(){
        //System.out.println("Initializing...");
        p1 = new Tank(125, 500, 50, 30);
        ArrayList<Bullet>bullets= new ArrayList<Bullet>();
        mainPanel = new DrawPanel();
        timer = new Timer(10, this);
        timer.setInitialDelay(0);
        timer.start();

    }
    public void addBullet(){
        int bH = Bullet.BULLET_H;
        int bW = Bullet.BULLET_W;
        if(p1.getDirection().equals("r")){
            bullets.add(new Bullet(p1.getX() + p1.getW()/2, p1.getY()+p1.getH()/2, bW,bH, p1.getDirection()));
        }
        else if(p1.getDirection().equals("l")){
            bullets.add(new Bullet(p1.getX() - p1.getW()/2, p1.getY()-p1.getH()/2, bW,bH, p1.getDirection()));

        }
        else if(p1.getDirection().equals("u")){
            bullets.add(new Bullet(p1.getX() - p1.getH()/2, p1.getY()-p1.getW()/2, bH,bW, p1.getDirection()));

        }
        else if(p1.getDirection().equals("d")){
            bullets.add(new Bullet(p1.getX() + p1.getH()/2, p1.getY()+p1.getW()/2, bH,bW, p1.getDirection()));

        }

    }
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}