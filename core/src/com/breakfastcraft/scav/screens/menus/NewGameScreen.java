package com.breakfastcraft.scav.screens.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.breakfastcraft.scav.managers.ScreenManager;
import com.breakfastcraft.scav.managers.SoundManager;
import com.breakfastcraft.scav.managers.ScreenManager.STATE;

public class NewGameScreen extends BaseMenuScreen {


    public NewGameScreen() {}

    @Override
    public void show() {
        super.show();
        table.defaults().spaceBottom(10).width(300).height(45);

        TextButton newWorld = new TextButton("New Map", skin);
        newWorld.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                ScreenManager.getInstance().dispose(STATE.GAME);
                ScreenManager.getInstance().setScreen(STATE.GAME);
            }
        });
        table.add(newWorld);

        table.row();

        TextButton backButton = new TextButton("Back to main menu", skin);
        backButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                ScreenManager.getInstance().setScreen(STATE.MAIN_MENU);

            }
        });
        table.row();
        table.add(backButton);
    }

}
