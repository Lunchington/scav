package com.breakfastcraft.scav.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.Main;
import com.breakfastcraft.scav.managers.ScreenManager;
import com.breakfastcraft.scav.screens.GamePlayScreen;

public class GameInput extends InputAdapter {
    private GamePlayScreen screen;

    public GameInput( GamePlayScreen screen) {
        this.screen = screen;
    }


    @Override
    public boolean keyUp(int key) {
        switch (key) {
            case Input.Keys.F1:
                ScreenManager.getInstance().setScreen(ScreenManager.STATE.MAIN_MENU);
                return true;
            case Input.Keys.F5:
                Main.toggleDevMode();
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean keyDown(int key) {
        switch (key) {
            default:
                return false;
        }
    }

}
