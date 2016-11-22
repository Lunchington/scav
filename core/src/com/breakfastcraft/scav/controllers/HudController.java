package com.breakfastcraft.scav.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class HudController {
    private Stage stage;

    public HudController(Stage stage) {
        this.stage = stage;
    }


    public void update(float delta) {
        stage.act(delta);
    }
}
