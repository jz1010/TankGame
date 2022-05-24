import java.awt.*;

public class Settings {
    /*
    * isPainted Value Correspondence
    * 0-Empty
    * 1-Tank + Cannon
    * 2-Bullet
    * 3-Border
    * 4-Big Bullet
    * 5-Enemy
    *
    *
    * ------ MATS -------
    * tankMat - 1 is tank + cannon
    * bulletMat - 1 is bullet, 2 is big bullet
    * enemyMat - 1 is enemy
    *
    * */

    public static final Color BORDER_COLOR = Color.GRAY;
    public static final Color PLAYER_COLOR = Color.BLUE;
    public static final Color ENEMY_COLOR = new Color(0, 255, 0);
    public static final Color CANNON_COLOR = Color.GREEN;
    public static final Color SMALL_BULLET_COLOR = Color.BLACK;
    public static final Color BIG_BULLET_COLOR = Color.ORANGE;
    public static final Color PLAYER_CORNER_COLOR = Color.RED;//Corners for cannon detection, will only apply is SHOW_CORNERS = true

    public static final int DRAW_PANEL_DIMS = 600;
    public static final boolean SHOW_CORNERS = false;

    public static final int SECONDARY_FIRE = 32;//Keycode for Space Bar, secondary fire
    public static final int PLAYER_MOVE_RIGHT = 68;//D
    public static final int PLAYER_MOVE_LEFT = 65;
    public static final int PLAYER_MOVE_UP = 87;
    public static final int PLAYER_MOVE_DOWN = 83;
    public static final int ESCAPE= 27;//Escape key

    public static final int BASE_SECOND_AMMO = 80;
    public static final int RELOAD_TIME_SECONDS = 3;
    public static final int MAIN_RELOAD_TIME_SECONDS = 5;
    public static final int PLAYER_STARTING_HEALTH = 100;
    public static final int BIG_BULLET_DAMAGE_RADIUS = 80;
    public static final int BULLET_DMG = 20;
    public static final double PROBABILITY_PATHFIND = 1;
    public static final int BULLET_ADD_RATE = 5;//lower means more bullets
    public static final int GAME_SECONDS = 60;
    public static final int ENEMY_DAMAGE = 25;
    public static final boolean DEBUG_NO_ENEMY = false;



}
