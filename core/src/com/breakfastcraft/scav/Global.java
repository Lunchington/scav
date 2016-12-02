package com.breakfastcraft.scav;

public class Global {
    public static final String TITLE = "SCAV";
    public static final String VERSION ="0.02 pre alpha";

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final float PPM = 12f;
    public static final float V_WIDTH = WIDTH / PPM;
    public static final float V_HEIGHT = HEIGHT / PPM;

    public static final float CHUNKWIDTH = V_WIDTH*3;
    public static final float CHUNKHEIGHT = V_HEIGHT*3 ;

    public static final String font = "fonts/rimouski.ttf";
    public static final String GUI = "gui/gui.json";
    public static final String GUI_ATLAS = "gui/gui.atlas";

    public static final String PLAYER_ATLAS = "player.atlas";
    public static final String DEFAULT_ATLAS = "default.atlas";

    public static final String STAR_ATLAS = "stars.atlas";


    public static final float TIME_STEP = 1f/60f ;

    public static final int VELOCITY_ITERATIONS = 6;

    public static final int POSITION_ITERATIONS = 2;
}
