package com.breakfastcraft.scav.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.breakfastcraft.scav.managers.ScreenManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends AbstractScreen {


    public SplashScreen( ) {}

    @Override
    public void show() {

        Image splashImage = new Image(new Texture(Gdx.files.internal("pafSplash.png")));
        splashImage.setFillParent(true);

        splashImage.getColor().a = 0f;

        splashImage.addAction(sequence(fadeIn(0.75f), delay(1.75f), fadeOut(0.75f), new Action() {
            @Override
            public boolean act(float delta) {
                ScreenManager.getInstance().setScreen(ScreenManager.STATE.MAIN_MENU);
                return true;
            }
        }));
        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
