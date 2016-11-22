package com.breakfastcraft.scav.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UITooltip extends Label {
    public UITooltip(String name, Skin skin) {
        super(name, skin);
        setVisible(false);
    }
}
