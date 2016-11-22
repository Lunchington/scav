package com.breakfastcraft.scav.managers;


import com.breakfastcraft.scav.Main;
import com.breakfastcraft.scav.screens.*;
import com.breakfastcraft.scav.screens.menus.*;

import java.util.HashMap;

public class ScreenManager {
    private static  ScreenManager INSTANCE;

    public static ScreenManager getInstance() {
        return INSTANCE;
    }

    public static void init(Main game) { INSTANCE = new ScreenManager(game);}

    private Main game;
    private HashMap<STATE, AbstractScreen> screens;

    public enum STATE {
        SPLASH,
        LOADING,
        MAIN_MENU,
        NEW_GAME,
        OPTIONS,
        GAME
    }

    private ScreenManager(Main game) {
        this.game = game;
        this.screens = new HashMap<STATE, AbstractScreen>();

        setScreen(STATE.GAME);

    }
    
    private void createScreen(STATE newScreen) {
        switch (newScreen){
            case SPLASH:
                this.screens.put(STATE.SPLASH, new SplashScreen());
                return;

            case LOADING:
                this.screens.put(STATE.LOADING, new LoadingScreen());
                return;

            case MAIN_MENU:
                this.screens.put(STATE.MAIN_MENU, new MenuScreen());
                return;
            case NEW_GAME:
                this.screens.put(STATE.NEW_GAME, new NewGameScreen());
                return;

            case OPTIONS:
                this.screens.put(STATE.OPTIONS, new OptionsScreen());
                return;

            case GAME:
                this.screens.put(STATE.GAME, new GamePlayScreen());
        }

    }

    public void setScreen(STATE nextScreen) {
        if (!screens.containsKey(nextScreen))
            createScreen(nextScreen);
        game.setScreen(screens.get(nextScreen));
    }


    public void dispose(STATE disposeScreen) {
        if (this.screens.containsKey(disposeScreen)) {
            this.screens.get(disposeScreen).dispose();
            this.screens.remove(disposeScreen);
        }
    }

    public void dispose(){
        for(STATE s: STATE.values()) {
            dispose(s);
        }
    }


    public boolean isScreen(STATE isScreen) {
        return (screens.containsKey(isScreen));
    }

}

