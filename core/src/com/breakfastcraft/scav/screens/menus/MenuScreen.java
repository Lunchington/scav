package com.breakfastcraft.scav.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.breakfastcraft.scav.managers.ScreenManager;
import com.breakfastcraft.scav.managers.ScreenManager.STATE;
import com.breakfastcraft.scav.managers.SoundManager;


public class MenuScreen extends BaseMenuScreen {


    public MenuScreen( ) {}

    @Override
    public void show() {
        super.show();
        table.defaults().spaceBottom(10).width(300).height(45);

        if (ScreenManager.getInstance().isScreen(STATE.GAME)) {
            TextButton testButton = new TextButton("Return to game", skin);
            testButton.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                    ScreenManager.getInstance().setScreen(STATE.GAME);
                }
            });
            table.add(testButton);
            table.row();
        }

        TextButton newGameButton = new TextButton("New game", skin);
        newGameButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                ScreenManager.getInstance().setScreen(STATE.NEW_GAME);
            }
        });
        table.add(newGameButton);
        table.row();

        TextButton loadGameButton = new TextButton("Load Game", skin);
        table.add(loadGameButton);
        table.row();

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                ScreenManager.getInstance().setScreen(STATE.OPTIONS);
            }
        });
        table.add(optionsButton);
        table.row();

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                Gdx.app.exit();
            }
        });
        table.add(exitButton);

    }

}
