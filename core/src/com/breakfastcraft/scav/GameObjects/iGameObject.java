package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Lunchington on 11/19/2016.
 */
public interface iGameObject {

    void update(float delta);
    void render(SpriteBatch batch);

}
