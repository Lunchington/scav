package com.breakfastcraft.scav.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.breakfastcraft.scav.Global;

public final class ArtManager extends AssetManager {
    private static  ArtManager INSTANCE;

    public static ArtManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArtManager();
        }
        return INSTANCE;
    }



    private Skin GUI_SKIN;
    private TextureAtlas PLAYER;
    private TextureAtlas DEFAULT;

    public void load(){
        load(Global.PLAYER_ATLAS, TextureAtlas.class);
        load(Global.DEFAULT_ATLAS, TextureAtlas.class);

        while(!update())
        {
            System.out.println("Loaded: " + getProgress() *100 + "%");
        }
        init();
    }

    private void init() {
        PLAYER = get(Global.PLAYER_ATLAS, TextureAtlas.class);
        DEFAULT = get(Global.DEFAULT_ATLAS, TextureAtlas.class);

        GUI_SKIN = new Skin();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Global.font));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 16;
        BitmapFont font16 = generator.generateFont(parameter); // font size 12 pixels

        parameter.size = 12;
        BitmapFont font10 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        GUI_SKIN.add("default-font", font16, BitmapFont.class);
        GUI_SKIN.add("small-font", font10, BitmapFont.class);

        GUI_SKIN.addRegions(new TextureAtlas(Gdx.files.internal(Global.GUI_ATLAS)));
        GUI_SKIN.load(Gdx.files.internal(Global.GUI));
    }

    public Skin getGUI() { return GUI_SKIN; }

    public TextureAtlas getPlayer() { return PLAYER; }

    public TextureAtlas getDefault() { return DEFAULT; }
}

