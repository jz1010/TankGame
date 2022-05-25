# Welcome to Janky Tank!


## Anay Pant and Jacob Zweiback

X-----------------------------------------------------------------------------------X


# The Game


### The point of Janky Tank is simple: DESTROY ALL ENEMIES!

You are a tank, and your goal is to kill all the enemies coming at you! Your scoree goes up the more enemies you kill.

You have two weapons: A static machine gun (shoots out small bullets – check Bullet.java) and a cannon “Big Bullet” (BigBullet.java). These are your weapons to take down the enemies.

However, you also have HP you have to watch out for! Make sure to monitor your hp to ensure you stay alive. Every time an enemy touches you, you lose HP.

There are 2 types of Enemies: Normal Enemies and Mega Enemies. The Mega Enemies move faster, have more HP, and do more damage than a normal enemy. However, you get 10 points for every mega enemy destroyed and 1 point for every enemy destroyed.

P.S: You **can** technically ram into enemies, although this will cost you HP. IT IS NOT ADVISED!!

P.P.S: In the settings file, you can find a list of all the global settings like tank HP, speed, and colors! Feel free to play with these settings and change the feel of the game. (Don’t try to change the other stuff unless you are a pro and can deal with 2000+ LOC). :)


# How To Play

The game is simple: All you need is WASD and a cursor (A MOUSE IS RECOMMENDED, BUT NOT REQUIRED).

The tank can move in 4 directions: up, down, left, and right. These correspond the the normal WASD keys you would find in many video games.

The tank also has 8 rotations for the turret. The tank will automatically point to where your cursor is, so where you point, it will shoot.

Note: Because the tank has a limited rotation, it will go to the **closest corner or edge**, not the **exact** point your cursor is at (It is a janky tank after all!)

Because the tank automatically fires its machine gun, all you have to do is click the left mouse button to fire the big bullet. Monitor the side panel to see when the bullet is loaded. If you see a orange bullet, it is loaded. If not, just wait a bit!

You can also pause the game by pressing the Escape key (esc). This will momentarily pause the game and allow you to refresh after destroying all those enemies.

If you die, no problem! Get back into the action by restarting. You can even keep track of your high score to see who is the best shooter!


# The Files

Here are some of the files, ranked from most important to least important:



* **TankProgram.java**
    * This is the actual program to run the game. This program connects all the other files and places all the components into Swing.
    * TankProgram.java also monitors the timers, frames, and other Swing Components for aesthetics and efficiency.
    * Run this to play the game!
* **DrawPanel.java**
    * This class is an extended JPanel, meaning it can be directly added to a frame in Swing
    * DrawPanel.java is the main Canvas for drawing the game itself
    * Some custom methods used:
        * DrawTank - self explanatory
        * DrawCorners - this is used to figure out the 8 points of rotation for the turret. This can be enabled/disabled in the Settings(see below).
        * DrawBullet + DrawBigBullet - passes in a bullet or big bullet to be drawn on screen. This took time because the dimensions change depending on the rotation of the turret, as well as the trajectory
        * DrawEnemy - similar to DrawTank, not too difficult to implement
    * This panel also keeps track of each individual pixel where a bullet, tank, or enemy is. This is **extremely** useful for collision detection.
* **SidePanel.java**
    * This panel is very similar to DrawPanel, another extended JPanel
    * This panel is in charge of displaying the ammo, health, and score
* **Tank.java**
    * This class houses the dimensions, rotation, turret rotation, and other information regarding the player.
    * The tank’s x and y are in the center (similar to greenfoot), which helps with rotation, as the drawing process is similar for different orientations
* **Enemy.java**
    * Very similar to tank class. Has a boolean **Mega** which distinguishes between mega and normal enemies
    * Also has a unique color associated based on its HP
* **Bullet.java + BigBullet.java**
    * Basic class for the bullets to keep track of their x,y, and direction fired. This is useful for drawing(see above)
* **Settings.java**
    * Arguably a very important file, but easy to make. This file is full of public static final variables that change the difficult, look, and aesthetic of the game. This makes it user-friendly, as a simple change in the Settings is easier than changing it everywhere else in the code.


# References



* Special Thanks to:
    * Nathan Lin for providing insight into rotation + multiple frames
    * [https://stackoverflow.com/questions/4580005/what-is-the-simplest-way-to-do-settings-files-in-java](https://stackoverflow.com/questions/4580005/what-is-the-simplest-way-to-do-settings-files-in-java)
        * Settings files with global settings
    * [https://stackoverflow.com/questions/1439022/get-mouse-position](https://stackoverflow.com/questions/1439022/get-mouse-position)
        * Getting mouse position to target turret
    * [https://stackoverflow.com/questions/9425451/how-to-get-the-timer-to-return-time-java#:~:text=The%20actionPerformed%20method%20has%20an%20ActionEvent%20parameter%20whose,mechanism%20with%20no%20graphical%20representation%20of%20its%20own](https://stackoverflow.com/questions/9425451/how-to-get-the-timer-to-return-time-java#:~:text=The%20actionPerformed%20method%20has%20an%20ActionEvent%20parameter%20whose,mechanism%20with%20no%20graphical%20representation%20of%20its%20own).
        * Adding a timer + getting data to add + delete objects
    * [https://stackoverflow.com/questions/21964768/simple-java-2d-graphics-draw-a-rectangle](https://stackoverflow.com/questions/21964768/simple-java-2d-graphics-draw-a-rectangle)
        * Initial idea of using Rect function in Swing, stuck with pixel-based
    * [https://stackoverflow.com/questions/20413910/how-can-i-add-a-keylistener-to-my-gui-in-java](https://stackoverflow.com/questions/20413910/how-can-i-add-a-keylistener-to-my-gui-in-java) and [https://stackoverflow.com/questions/10544956/java-swing-do-something-while-the-key-is-being-pressed](https://stackoverflow.com/questions/10544956/java-swing-do-something-while-the-key-is-being-pressed)
        * Adding keylistener for tank + escape