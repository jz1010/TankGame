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
    * */

    public static final Color BORDER_COLOR = Color.GRAY;
    public static final Color PLAYER_COLOR = Color.BLUE;
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

    public static final int BASE_SECOND_AMMO = 80;
    public static final int RELOAD_TIME_SECONDS = 3;
    public static final int MAIN_RELOAD_TIME_SECONDS = 5;
    public static final int PLAYER_STARTING_HEALTH = 100;
    public static final int BIG_BULLET_DAMAGE_RADIUS = 10;


}
