import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TankProgram implements ActionListener {
    JFrame frame;
    DrawPanel mainPanel;
    JPanel sidePanel;

    Tank p1;
    JLabel healthBar, ammoCount, mainAmmo;
    private boolean secReloading = false;
    private boolean mainReloading = false;
    private boolean alreadyReloading = false;

    int secReloadTimer;
    int mainReloadTimer;


    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<BigBullet> bigBullets = new ArrayList<BigBullet>();
    ArrayList<Enemy> enemies=new ArrayList<Enemy>();
    Timer timer;

    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public TankProgram() {
        init();


        mainPanel.drawTank(p1);
        sidePanel.add(healthBar);
        sidePanel.add(ammoCount);
        sidePanel.add(mainAmmo);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(sidePanel, BorderLayout.EAST);


        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!mainReloading){
                    addBigBullet();
                    mainReloading = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());
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
        }

        int counter = 0;
        while(counter < bullets.size()){
            Bullet bullet = bullets.get(counter);
            bullet.update();
            if((bullet.getX() + bullet.getW() >= 600) || (bullet.getX() <=0) ||(bullet.getY() <= 0) || (bullet.getY() +bullet.getH()>=600) || bullet.getX() - bullet.getW() <= 0 || bullet.getY() - bullet.getH() <= 0){
                bullets.remove(counter);
                counter--;
            }
            else{
                mainPanel.drawBullet(bullet);

            }

            counter ++;
        }

        counter = 0;
        while(counter < bigBullets.size()){
            BigBullet bullet = bigBullets.get(counter);
            bullet.update();
            if((bullet.getX() + bullet.getW() >= 600) || (bullet.getX() <=0) ||(bullet.getY() <= 0) || (bullet.getY() +bullet.getH()>=600) || bullet.getX() - bullet.getW() <= 0 || bullet.getY() - bullet.getH() <= 0){
                bigBullets.remove(counter);
                counter--;
            }
            else{
                mainPanel.drawBigBullet(bullet);

            }
            counter ++;
        }

        healthBar.setText(String.valueOf(p1.getHealth()));
        if(p1.getAmmo() <= 0 && !secReloading){
            ammoCount.setText("Reloading");
            secReloadTimer = 0;
            secReloading = true;
        }
        else if (!secReloading){
            ammoCount.setText(String.valueOf(p1.getAmmo()));
        }
        if(secReloading && secReloadTimer >= 100 * Settings.RELOAD_TIME_SECONDS) {  // num seconds * 100
            p1.setAmmo(Settings.BASE_SECOND_AMMO);
            secReloading = false;
        }
        else if(secReloading){
            secReloadTimer++;
        }
        reloadMain();

        if(getRandRange(1, 100) < 10){
            addEnemy();
        }
        for (Enemy enemy:enemies){
            mainPanel.drawEnemy(enemy);
        }


    }

    public void init(){
        mainPanel = new DrawPanel();
        sidePanel = new JPanel();

        p1 = new Tank(300, 300, 30, 30, 50);
        ArrayList<Bullet>bullets= new ArrayList<Bullet>();
        ArrayList<BigBullet> bigBullets = new ArrayList<>();

        frame = new JFrame("Tank Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        healthBar = new JLabel();
        healthBar.setText(String.valueOf(p1.getHealth()));
        ammoCount = new JLabel();
        ammoCount.setText(String.valueOf(p1.getAmmo()));
        mainAmmo = new JLabel();
        mainAmmo.setText("Ready to Fire!");


        timer = new Timer(10, this);
        timer.setInitialDelay(0);
        timer.start();

    }
    public void addBullet(){
        int bH = Bullet.BULLET_H;
        int bW = Bullet.BULLET_W;
        if(p1.getBarrelDirection().equals("r")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() + p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() + p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection(), false));
            }
        }
        else if(p1.getBarrelDirection().equals("l")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() - p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() - p1.getW(), p1.getY(), bW,bH, p1.getBarrelDirection(), true));
            }
        }
        else if(p1.getBarrelDirection().equals("u")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX(), p1.getY() - p1.getW(), bH,bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX(), p1.getY() - p1.getW(), bH,bW, p1.getBarrelDirection(), false));
            }
        }
        else if(p1.getBarrelDirection().equals("d")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX(), p1.getY()+p1.getW(), bH,bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX(), p1.getY()+p1.getW(), bH,bW, p1.getBarrelDirection(), false));
            }
        }
        else if (p1.getBarrelDirection().equals("ru")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() + (int)(p1.getW()/2), p1.getY() - (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() + (int)(p1.getW()/2), p1.getY() - (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), false));
            }
        }
        else if (p1.getBarrelDirection().equals("lu")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() - (int)(p1.getW()/2), p1.getY() - (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() - (int)(p1.getW()/2), p1.getY() - (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), false));
            }
        }
        else if (p1.getBarrelDirection().equals("rd")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() + (int)(p1.getW()/2), p1.getY() + (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() + (int)(p1.getW()/2), p1.getY() + (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), false));
            }
        }
        else if (p1.getBarrelDirection().equals("ld")){
            if(p1.getDirection().equals("u") || p1.getDirection().equals("d")){
                bullets.add(new Bullet(p1.getX() - (int)(p1.getW()/2), p1.getY() + (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), true));
            }
            else{
                bullets.add(new Bullet(p1.getX() - (int)(p1.getW()/2), p1.getY() + (int)(p1.getW()/2), bH, bW, p1.getBarrelDirection(), false));
            }
        }

    }
    public void addBigBullet(){
        int bH = BigBullet.BULLET_H;
        int bW = BigBullet.BULLET_W;
        if(p1.getBarrelDirection().equals("r")){
            bigBullets.add(new BigBullet(p1.getX() + p1.getW()/2, p1.getY(), p1.getBarrelDirection()));
        }
        else if(p1.getBarrelDirection().equals("l")){
            bigBullets.add(new BigBullet(p1.getX() - p1.getW()/2, p1.getY(), p1.getBarrelDirection()));
        }
        else if(p1.getBarrelDirection().equals("u")){
            bigBullets.add(new BigBullet(p1.getX(), p1.getY() - p1.getH()/2, p1.getBarrelDirection()));

        }
        else if(p1.getBarrelDirection().equals("d")){
            bigBullets.add(new BigBullet(p1.getX(), p1.getY() + p1.getH()/2, p1.getBarrelDirection()));
        }
        else if (p1.getBarrelDirection().equals("ru")){
            bigBullets.add(new BigBullet(p1.getX() + p1.getW()/2, p1.getY() - p1.getH()/2, p1.getBarrelDirection()));
        }
        else if (p1.getBarrelDirection().equals("lu")){
            bigBullets.add(new BigBullet(p1.getX() - p1.getW()/2, p1.getY() - p1.getH()/2, p1.getBarrelDirection()));
        }
        else if (p1.getBarrelDirection().equals("rd")){
            bigBullets.add(new BigBullet(p1.getX() + p1.getW()/2, p1.getY() + p1.getH()/2, p1.getBarrelDirection()));
        }
        else if (p1.getBarrelDirection().equals("ld")){
            bigBullets.add(new BigBullet(p1.getX() - p1.getW()/2, p1.getY() + p1.getH()/2, p1.getBarrelDirection()));
        }
    }
    public void reloadMain(){
        if(mainReloading && !alreadyReloading){
            mainReloadTimer = 0;
            alreadyReloading=true;
        }
        if(mainReloading && alreadyReloading && mainReloadTimer >= 100 * Settings.MAIN_RELOAD_TIME_SECONDS){
            alreadyReloading = false;
            mainReloading = false;
            mainReloadTimer = 0;
            mainAmmo.setText("Reloading Main!");

        }
        else if (mainReloading && alreadyReloading){
            mainReloadTimer +=2;
            mainAmmo.setText("Reloading Main!");

        }
        else{
            mainAmmo.setText("Ready to Fire!");
        }


    }
    public void addEnemy(){
        int eX = getRandRange(Enemy.w, 600 - Enemy.w);
        int eY = getRandRange(Enemy.h, 600 - Enemy.h);
        enemies.add(new Enemy(eX, eY));
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
        pointDist[0] = calcDist(mP, new double[]{p1.getX() + (double) p1.getH()/2, p1.getY()});
        //Left
        pointDist[1] = calcDist(mP, new double[]{p1.getX() - (double) p1.getH()/2, p1.getY()});
        //Up
        pointDist[2] = calcDist(mP, new double[]{p1.getX(), p1.getY() - (double) p1.getH()/2});
        //Down
        pointDist[3] = calcDist(mP, new double[]{p1.getX(), p1.getY() + (double) p1.getH()/2});
        //RightUp
        pointDist[4] = calcDist(mP, new double[]{p1.getX() + (double) p1.getH()/2/1.4, p1.getY() - (double) p1.getH()/2/1.4});
        //leftUp
        pointDist[5] = calcDist(mP, new double[]{p1.getX() - (double) p1.getH()/2/1.4, p1.getY() - (double) p1.getH()/2/1.4});
        //rightDown
        pointDist[6] = calcDist(mP, new double[]{p1.getX() + (double) p1.getH()/2/1.4, p1.getY() + (double) p1.getH()/2/1.4});
        //leftDown
        pointDist[7] = calcDist(mP, new double[]{p1.getX() - (double) p1.getH()/2/1.4, p1.getY() + (double) p1.getH()/2/1.4});

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

    public static int getRandRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
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