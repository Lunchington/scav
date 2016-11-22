package com.breakfastcraft.scav.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.breakfastcraft.scav.managers.ArtManager;
import com.breakfastcraft.scav.managers.MusicManager;
import com.breakfastcraft.scav.managers.PreferencesManager;
import com.breakfastcraft.scav.screens.AbstractScreen;


class BaseMenuScreen extends AbstractScreen {

    Table table;
    Skin skin;

    BaseMenuScreen() {
        skin = ArtManager.getInstance().getGUI();
        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

    }

    @Override
    public void show() {
        super.show();
        table.clear();
        Drawable splashDrawable =  new Image(new Texture(Gdx.files.internal("logo.png"))).getDrawable();
        table.setBackground(splashDrawable);
        if (PreferencesManager.getInstance().isMusicEnabled()) {
            MusicManager.getInstance().play(MusicManager.GameMusic.MENU);
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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