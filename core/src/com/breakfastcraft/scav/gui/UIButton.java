package com.breakfastcraft.scav.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.breakfastcraft.scav.Main;
import com.breakfastcraft.scav.managers.SoundManager;


public class UIButton extends ImageButton {
    private Main game;

    private Window window;
    private UITooltip toolTip;

    public UIButton(final Main game, Skin skin, String type, String name) {
        super(skin.get(type,ImageButton.ImageButtonStyle.class));
        this.game = game;
        this.setName(name);
        this.toolTip = new UITooltip(name,skin);

        window = new Window("",skin);
        window.setVisible(false);
        window.setPosition(getX() ,getHeight() +5f);
        addActor(window);
        addActor(toolTip);
        init();
    }


    private void init() {
        addListener(new TooltipListener(toolTip,true));
        addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                SoundManager.getInstance().play(SoundManager.GameSound.CLICK);
                window.setVisible(!window.isVisible());
            }
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

            }
        });
    }

}
