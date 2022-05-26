import java.awt.*;

public class Settings {

    // This file is the most user-friendly ---- has all the settings


    // COLORS
    public static final Color BORDER_COLOR = Color.GRAY;
    public static final Color PLAYER_COLOR = new Color(96, 115, 94);
    public static final Color ENEMY_COLOR = new Color(0, 255, 0);
    public static final Color CANNON_COLOR = new Color(9, 97, 0);
    public static final Color BIG_BULLET_COLOR = Color.ORANGE;
    public static final Color PLAYER_CORNER_COLOR = Color.RED;

    //DAMAGE + HEALTH
    public static final int PLAYER_STARTING_HEALTH = 100;
    public static final int BULLET_DMG = 20;
    public static final int ENEMY_DAMAGE = 10;
    public static final int MEGA_ENEMY_DAMAGE = 25;

    //KEYCODES
    public static final int PLAYER_MOVE_RIGHT = 68;//D
    public static final int PLAYER_MOVE_LEFT = 65;//A
    public static final int PLAYER_MOVE_UP = 87;//W
    public static final int PLAYER_MOVE_DOWN = 83;//S
    public static final int ESCAPE= 27;//Escape key

    //GRAPHICS
    public static final int DRAW_PANEL_DIMS = 600;
    public static final boolean SHOW_CORNERS = false;

    //TIMERS
    public static final int BASE_SECOND_AMMO = 80;
    public static final int MAIN_RELOAD_TIME_SECONDS = 5;
    public static final int BULLET_ADD_RATE = 5;//lower means more bullets
    public static final int GAME_SECONDS = 60;

    //PATHFINDING + DEBUG
    public static final double PROBABILITY_PATH_FIND = 0.8;
    public static final boolean DEBUG_NO_ENEMY = false;





    //Developer Notes:

    // * isPainted Value Correspondence
    // * 0-Empty
    // * 1-Tank + Cannon
    // * 2-Bullet
    // * 3-Border
    // * 4-Big Bullet
    // * 5-Enemy
    // *
    // *
    // * ------ MATS -------
    // * tankMat - 1 is tank + cannon
    // * bulletMat - 1 is bullet, 2 is big bullet
    // * enemyMat - 1 is enemy
    // *
    // *
}
