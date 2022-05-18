import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Set;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    JPanel sidePanel;

    Tank p1;
    JLabel healthBar, ammoCount;
    private boolean secReloading = false;

    int reloadTimer;


    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies=new ArrayList<Enemy>();
    Timer timer;

    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();


        mainPanel.drawTank(p1);
        sidePanel.add(healthBar);
        sidePanel.add(ammoCount);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(sidePanel, BorderLayout.EAST);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                if(e.getKeyCode() == Settings.SECONDARY_FIRE && !(ammoCount.getText().equals("Reloading"))){ // space bar to fire
                    addBullet();
                    p1.decAmmo();
                }

                //lets do wasd
                if(e.getKeyCode() == Settings.PLAYER_MOVE_RIGHT){ // right arrow
                    p1.moveTank("r");
                }
                else if (e.getKeyCode() == Settings.PLAYER_MOVE_LEFT){ // left arrow
                    p1.moveTank("l");
                }
                else if (e.getKeyCode() == Settings.PLAYER_MOVE_UP){ // up arrow
                    p1.moveTank("u");
                }
                else if (e.getKeyCode() == Settings.PLAYER_MOVE_DOWN){ // down arrow
                    p1.moveTank("d");
                }


            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        frame.pack();
        frame.setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        mainPanel.clear();
        mainPanel.drawBorder();
        mainPanel.drawTank(p1);

        Point mousePoint = mainPanel.getMousePosition();
        if(mousePoint != null){
            double mX = mousePoint.getX();
            double mY = mousePoint.getY();
            p1.moveBarrel(getClosestPoint(new double[]{mX, mY}));
            System.out.println(getClosestPoint(new double[]{mX, mY}));

        }


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

        healthBar.setText(String.valueOf(p1.getHealth()));
        if(p1.getAmmo() <= 0 && !secReloading){
            ammoCount.setText("Reloading");
            reloadTimer = 0;
            secReloading = true;
        }
        else if (!secReloading){
            ammoCount.setText(String.valueOf(p1.getAmmo()));
        }
        if(secReloading && reloadTimer >= 100 * Settings.RELOAD_TIME_SECONDS) {  // num seconds * 100
            p1.setAmmo(Settings.BASE_SECOND_AMMO);
            secReloading = false;
        }
        else if(secReloading){
            reloadTimer ++;
        }


    }

    public void init(){
        mainPanel = new DrawPanel();
        sidePanel = new JPanel();

        p1 = new Tank(125, 500, 50, 30);
        ArrayList<Bullet>bullets= new ArrayList<Bullet>();

        frame = new JFrame("Tank Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        healthBar = new JLabel();
        healthBar.setText(String.valueOf(p1.getHealth()));
        ammoCount = new JLabel();
        ammoCount.setText(String.valueOf(p1.getAmmo()));


        timer = new Timer(10, this);
        timer.setInitialDelay(0);
        timer.start();

    }
    public void addBullet(){
        int bH = Bullet.BULLET_H;
        int bW = Bullet.BULLET_W;
        if(p1.getBarrelDirection().equals("r")){
            bullets.add(new Bullet(p1.getX() + p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection()));
        }
        else if(p1.getBarrelDirection().equals("l")){
            bullets.add(new Bullet(p1.getX() - p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection()));

        }
        else if(p1.getBarrelDirection().equals("u")){
            bullets.add(new Bullet(p1.getX(), p1.getY() - p1.getW(), bH,bW, p1.getBarrelDirection()));

        }
        else if(p1.getBarrelDirection().equals("d")){
            bullets.add(new Bullet(p1.getX(), p1.getY()+p1.getW(), bH,bW, p1.getBarrelDirection()));

        }

    }
    public void addEnemy(){
        Enemy enemy=new Enemy(0,0,50,50);
        int eH = enemy.getH();
        int eW = enemy.getW();
        int eX = (int)(Math.random()*600);
        int eY = (int)(Math.random()*600);
        enemies.add(new Enemy(eX, eY, eW, eH));
    }
    public static double calcDist(double[] a, double[] b){
        double x = a[0] - b[0];
        double y = a[1] - b[1];
        return Math.sqrt((x * x) + (y * y));
    }
    public String getClosestPoint(double[] mP){
        //Right,left,up,down,rightUp,leftUp,rightDown,leftDown
        double[] pointDist = new double[8];
        //Right
        pointDist[0] = calcDist(mP, new double[]{p1.getX() + (double) p1.getW()/2, p1.getY()});
        //Left
        pointDist[1] = calcDist(mP, new double[]{p1.getX() - (double) p1.getW()/2, p1.getY()});
        //Up
        pointDist[2] = calcDist(mP, new double[]{p1.getX(), p1.getY() - (double) p1.getH()/2});
        //Down
        pointDist[3] = calcDist(mP, new double[]{p1.getX(), p1.getY() + (double) p1.getH()/2});
        //RightUp
        pointDist[4] = calcDist(mP, new double[]{p1.getX() + (double) p1.getW()/2, p1.getY() - (double) p1.getH()/2});
        //leftUp
        pointDist[5] = calcDist(mP, new double[]{p1.getX() - (double) p1.getW()/2, p1.getY() - (double) p1.getH()/2});
        //rightDown
        pointDist[6] = calcDist(mP, new double[]{p1.getX() + (double) p1.getW()/2, p1.getY() + (double) p1.getH()/2});
        //leftDown
        pointDist[7] = calcDist(mP, new double[]{p1.getX() - (double) p1.getW()/2, p1.getY() + (double) p1.getH()/2});

        int bestPoint = getIndexofMin(pointDist);
        if(bestPoint == 0) {//R
            return "r";
        }
        else if(bestPoint == 1) {//L
            return "l";
        }
        else if(bestPoint == 2) {//U
            return "u";
        }
        else if(bestPoint == 3) {//D
            return "d";
        }
        else if(bestPoint == 4) {//RU
            return "ru";
        }else if(bestPoint == 5) {//LU
            return "lu";
        }
        else if(bestPoint == 6) {//RD
            return "rd";
        }
        else if(bestPoint == 7) {//LD
            return "ld";
        }

        return "r";
    }

    public static int getIndexofMin(double[] list){
        int bestIndex = 0;
        double lowestVal = 10000;
        for(int i = 0; i < list.length; i++){
            if(list[i] < lowestVal){
                bestIndex = i;
                lowestVal = list[i];
            }
        }
        return bestIndex;
    }
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}