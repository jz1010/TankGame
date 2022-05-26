import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//Importing all the necessary utilities for the program

public class TankProgram implements ActionListener {
    JFrame frame, titleFrame, endFrame, pauseFrame; // creates different frames and panels for game
    DrawPanel mainPanel;
    SidePanel sidePanel;

    Tank p1; // this is the player!
    JLabel healthBar, mainAmmo, title, score; // some JLabels to display info
    JLabel hScore = new JLabel(), fScore = new JLabel(); //These need to be initialized as they are called before init
    JPanel titlePanel, pausePanel, endPanel; // Panels to go on the frames
    JButton startButton; // Button to start game

    private boolean secReloading = false; //booleans to check reload logic
    private boolean mainReloading = false;
    private boolean alreadyReloading = false;

    int secReloadTimer;// timers for the game, bullet, reloads
    int mainReloadTimer;
    int addBulletTimer = 0;
    int gameTimer = (100 * Settings.GAME_SECONDS) + 100;


    int enemyNumber = 10; // unique id for each enemy
    int gameScore = 0; //scores to track current and high
    int highScore = 0;

    // makes arrayLists for bullets and enemies to keep track
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<BigBullet> bigBullets = new ArrayList<BigBullet>();
    ArrayList<Enemy> enemies=new ArrayList<Enemy>();
    Timer timer;

    // This is the TankProgram constructor which sets up the JFrame and all other components and containers
    public TankProgram() {

        //Initialize new frames
        frame = new JFrame("Janky Tank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleFrame = new JFrame("Janky Tank");
        titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleFrame.setSize(600, 600);
        titleFrame.setLocationRelativeTo(null);
        titleFrame.setLayout(new BorderLayout());

        //Adding fancy stuff
        title = new JLabel("Janky Tank");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        //starting the game on start button
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleFrame.setVisible(false);
                init();
                frame.setSize(1000, 800);

                frame.setVisible(true);

                mainPanel.drawTank(p1);
                sidePanel.add(score);
                frame.add(mainPanel, BorderLayout.CENTER);
                frame.add(sidePanel, BorderLayout.EAST);
            }
        });

        // more font stuff
        startButton.setFont(new Font("Arial", Font.BOLD, 50));
        startButton.setHorizontalAlignment(JLabel.CENTER);
        startButton.setVerticalAlignment(JLabel.CENTER);
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.add(title);
        titlePanel.add(startButton);
        titleFrame.add(titlePanel, BorderLayout.CENTER);

        titleFrame.setVisible(true);
        titleFrame.setSize(600, 600);
        titleFrame.setResizable(false);

        //create new JFrame for end screen with a button to restart the game
        endFrame = new JFrame("Janky Tank");
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(600, 600);
        endFrame.setLocationRelativeTo(null);
        endFrame.setResizable(false);
        endFrame.setLayout(new BorderLayout());
        // new labels and fonts for score
        JLabel endLabel = new JLabel("Game Over");
        endLabel.setFont(new Font("Arial", Font.BOLD, 50));
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endLabel.setVerticalAlignment(JLabel.CENTER);

        //restarting game on restart click
        JButton restartButton = new JButton("Restart");
        restartButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                endFrame.setVisible(false);


                //frame.setVisible(true);
                frame=new JFrame("Janky Tank");
                init();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setSize(1000, 1000);
                frame.setVisible(true);

                mainPanel.drawTank(p1);
                frame.add(mainPanel, BorderLayout.CENTER);
                frame.add(sidePanel, BorderLayout.EAST);
                timer.restart();
                resetAllTimers(); // restarted timers
                frame.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(!mainReloading){ //tells us if the bullet should fire
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
                frame.addKeyListener(new KeyListener() { // listens to keypresses for movement
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

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
                        else if (e.getKeyCode()==Settings.ESCAPE){ // pauses the game
                            //frame.setVisible(false);
                            pauseFrame.setVisible(true);
                        }


                    }
                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
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
        // adding restart button to a JPanel on the end frame
        restartButton.setFont(new Font("Arial", Font.BOLD, 50));
        restartButton.setHorizontalAlignment(JLabel.CENTER);
        restartButton.setVerticalAlignment(JLabel.CENTER);
        endPanel = new JPanel();
        endPanel.setLayout(new GridLayout(4, 1));
        endPanel.add(endLabel);

        //adding the current score and high score
        endPanel.add(fScore);
        endPanel.add(hScore);
        endPanel.add(restartButton);

        //adding end panel
        endFrame.add(endPanel, BorderLayout.CENTER);
        endFrame.setVisible(false);

        //create new JFrame for pause screen with a button to resume the game
        pauseFrame = new JFrame("Janky Tank");
        pauseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pauseFrame.setSize(600, 600);
        pauseFrame.setLocationRelativeTo(null);
        pauseFrame.setResizable(false);
        pauseFrame.setLayout(new BorderLayout());
        // font and labels
        JLabel pauseLabel = new JLabel("Paused");
        pauseLabel.setFont(new Font("Arial", Font.BOLD, 50));
        pauseLabel.setHorizontalAlignment(JLabel.CENTER);
        pauseLabel.setVerticalAlignment(JLabel.CENTER);
        JButton resumeButton = new JButton("Resume");
        //adding resume button
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseFrame.setVisible(false);
                timer.restart();
                //frame.setVisible(true);
            }
        }) ;
        //adding button and font to panel
        resumeButton.setFont(new Font("Arial", Font.BOLD, 50));
        resumeButton.setHorizontalAlignment(JLabel.CENTER);
        resumeButton.setVerticalAlignment(JLabel.CENTER);
        pausePanel = new JPanel();
        pausePanel.setLayout(new GridLayout(2, 1));
        pausePanel.add(pauseLabel);
        pausePanel.add(resumeButton);
        pauseFrame.add(pausePanel, BorderLayout.CENTER);
        pauseFrame.setVisible(false);

        // we have to re-initialize the mouse listener in case the game wasn't restarted earlier (1st game)
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!mainReloading){ // same as before w reloading and clicking
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
        frame.addKeyListener(new KeyListener() { // key listener for tank movement
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
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
                else if (e.getKeyCode()==Settings.ESCAPE){ //pause game
                    timer.stop();
                    pauseFrame.setVisible(true);
                }


            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //tighten the frame
        frame.pack();
    }
    public void actionPerformed(ActionEvent ae) {
        // this method constantly runs every cycle, around 10 ms per cycle (we try!)

        // runs the game if the tank is alive, the frame is on screen and not paused
        if(p1.getHealth() > 0 && frame.isVisible() && !pauseFrame.isVisible()){
            //clear panels
            mainPanel.clear();
            sidePanel.clear();

            // drawing border, tank, and ammo on side
            mainPanel.drawBorder();
            mainPanel.drawTank(p1);
            sidePanel.drawAmmo(p1);
            if(!(mainReloading)){
                sidePanel.drawBigBullet();
            }
            else{
                sidePanel.drawBigBulletRed();
            }
            //draws HP
            sidePanel.drawHP(p1);

            //updates timer, adds bullet if needed
            reloadMain();
            addBulletTimer++;
            if(addBulletTimer % Settings.BULLET_ADD_RATE == 0){
                addBullet();
            }
            if(addBulletTimer > 100){
                addBulletTimer = 0;
            }

            //adding labels to panel
            score.setText("Score: " + gameScore);
            sidePanel.add(score);
            healthBar.setText("Health Left: " + p1.getHealth());
            sidePanel.add(healthBar);

            // gets mouse position
            Point mousePoint = mainPanel.getMousePosition();
            if(mousePoint != null){
                double mX = mousePoint.getX();
                double mY = mousePoint.getY();
                //moves barrel to closest mouse point
                p1.moveBarrel(getClosestPoint(new double[]{mX, mY}));
            }

            //goes through bullet list and updates bullets
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

            // same thing with big bullets, goes through list
            counter = 0;
            while(counter < bigBullets.size()){
                BigBullet bullet = bigBullets.get(counter);
                bullet.update();
                if((bullet.getX() + bullet.getW() >= 596) || (bullet.getX() <=5) ||(bullet.getY() <= 5) || (bullet.getY() +bullet.getH()>=595) || bullet.getX() - bullet.getW() <= 5 || bullet.getY() - bullet.getH() <= 5){
                    bigBullets.remove(counter);
                    counter--;
                }
                else{
                    mainPanel.drawBigBullet(bullet);

                }
                counter ++;
            }

            // This is to add enemies randomly, but it wont happen if theres no debug setting on
            if(!Settings.DEBUG_NO_ENEMY){
                if(getRandRange(1, 100) < 5){
                    if(getRandRange(1, 8) == 3){
                        addEnemy(enemyNumber, true);
                        enemyNumber++;
                    }
                    else{
                        addEnemy(enemyNumber, false);
                        enemyNumber++;
                    }

                }
            }

            // updates, removes, or does damage for each enemy
            for (int e = 0; e < enemies.size();e++){
                Enemy enemy = enemies.get(e);
                enemy.update(p1);

                if(enemy.Contains(p1)){
                    if(enemy.isMega()){
                        p1.decHealthMega();
                    }
                    enemies.remove(e);
                    e--;
                    p1.decHealth();
                }
                else if(enemy.getHealth() > 0){
                    mainPanel.drawEnemy(enemy);
                }
                else{
                    if(enemies.get(e).isMega()){
                        gameScore += 10;
                    }
                    else{
                        gameScore ++;
                    }
                    enemies.remove(e);
                    e--;
                }


            }

            // checks for bullets
            for(int z = 0; z < bullets.size();z++){
                Bullet bullet = bullets.get(z);
                boolean enemyHit = false;
                for (int e = 0; e < enemies.size();e++){
                    Enemy cE = enemies.get(e);
                    //fix this
                    if(mainPanel.enemyMat[bullet.getX()][bullet.getY()] == cE.getNumber()){
                        enemies.get(e).takeDamage(Settings.BULLET_DMG);
                        e--;
                        enemyHit = true;
                    }
                    if(enemyHit){
                        break;
                    }
                }
                if(enemyHit){
                    bullets.remove(z);
                    z--;
                }
            }

            // checks for big bullets
            for(int z = 0; z < bigBullets.size();z++){
                BigBullet bullet = bigBullets.get(z);
                boolean enemyHit = false;
                for (int e = 0; e < enemies.size();e++){
                    Enemy cE = enemies.get(e);
                    if(mainPanel.enemyMat[bullet.getX()][bullet.getY()] == cE.getNumber()){
                        bigSplashKill(bullet);
                        enemyHit = true;
                    }
                    if(enemyHit){
                        break;
                    }
                }
            }
        }

        // checks if the tank is dead, updates score in end frame
        else if (p1.getHealth()<=0){
            if(highScore < gameScore){
                highScore = gameScore;
            }
            // high score and low scores are checked in here
            fScore.setText("Score: " + gameScore);
            hScore.setText("High Score: " + highScore);

            // setting frames true and false
            frame.setVisible(false);
            endFrame.setVisible(true);
        }
    }

    public void init(){ //initializing function, resets the frame
        mainPanel = new DrawPanel();
        sidePanel = new SidePanel();
        // new panels for main and side panels


        // new tank and bullets arrays to keep track of information
        p1 = new Tank(300, 300, 30, 30, 50);
        bullets= new ArrayList<Bullet>();
        bigBullets = new ArrayList<>();
        enemies = new ArrayList<>();

        // Sets JLabels for side panel and main panel
        mainAmmo = new JLabel();
        mainAmmo.setText("Ready to Fire!");
        healthBar = new JLabel();
        healthBar.setText("Health Left: " + p1.getHealth());
        score = new JLabel();
        score.setText("Score: " + gameScore);

        // starts internal delay timer, which cycles every 5 ms
        timer = new Timer(10, this);
        timer.setInitialDelay(0);
        timer.start();

    }
    public void resetAllTimers(){
        // resets all timers, both for pausing and ending game
        secReloading = false;
        mainReloading = false;
        alreadyReloading = false;
        enemyNumber = 10;
        addBulletTimer = 0;
        gameScore = 0;
        gameTimer = (100 * Settings.GAME_SECONDS) + 100;
    }
    public void addBullet(){//adds bullet based on turret position
        int bH = Bullet.BULLET_H;//constants
        int bW = Bullet.BULLET_W;

        //checks for each direction and adds bullet for each case
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
        //same thing as bullet w/ big bullet, adds for different turret direction
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
    //reloads main bullet functionality, this is called every iteration
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
    //adds an enemy with an id of num and boolean b
    public void addEnemy(int num, boolean b){

        //NOTE: the reason each enemy has a unique ID is for collision detection
        //NOTE cont'd - we need to know which enemy a bullet is hitting, they keep track of their ID

        //add them in one of four sections (borders)
        int randSection = getRandRange(1, 4);
        if(randSection == 1){//top rectangle
            int eX = getRandRange(Enemy.w, 600 - Enemy.w);
            int eY = getRandRange(Enemy.h, 60 - Enemy.h);
            enemies.add(new Enemy(eX, eY, num, b));

        }
        else if (randSection == 2){// bottom rectangle
            int eX = getRandRange(Enemy.w, 600 - Enemy.w);
            int eY = getRandRange(540 - Enemy.h, 600 - Enemy.h);
            enemies.add(new Enemy(eX, eY, num, b));

        }
        else if (randSection == 3){//left rectangle
            int eX = getRandRange(Enemy.w, 60 - Enemy.w);
            int eY = getRandRange(Enemy.h, 600 - Enemy.h);
            enemies.add(new Enemy(eX, eY, num, b));
        }
        else if (randSection == 4){//right rectangle
            int eX = getRandRange(540 - Enemy.w, 600 - Enemy.w);
            int eY = getRandRange(Enemy.h, 600 - Enemy.h);
            enemies.add(new Enemy(eX, eY, num, b));

        }
    }
    public void bigSplashKill(BigBullet b){
        // penetration kill for big bullet, different than a normal bullet

        for(int e = 0; e < enemies.size(); e++){
                if(mainPanel.enemyMat[b.getX()][b.getY()] == enemies.get(e).getNumber()){
                    if(enemies.get(e).isMega()){
                        gameScore+= 10;
                    }
                    else{
                        gameScore++;
                    }
                    enemies.remove(e);
                    e--;
                }

        }

    }
    public static double calcDist(double[] a, double[] b){
        // static method for distance calculation, used for turret location
        double x = a[0] - b[0];
        double y = a[1] - b[1];
        return Math.sqrt((x * x) + (y * y));
    }
    //This code decides which angle the mouse is pointing at
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

        // gets closest index and returns direction turret should face
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


    public static int getRandRange(int min, int max){ // VERY useful static function to get random numer
        //useful for adding stuff in cycles or random spots
        return (int) ((Math.random() * (max - min)) + min + 1);
    }
    public static int getIndexofMin(double[] list){
        // static function for index of a lowest value
        //used for turret rotation
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
    //run the program!
    public static void main(String[] args) {
        TankProgram x = new TankProgram();
    }




}