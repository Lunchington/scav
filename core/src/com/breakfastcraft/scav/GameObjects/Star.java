package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.Global;
import com.breakfastcraft.scav.managers.ArtManager;

import static com.breakfastcraft.scav.Global.*;


public class Star extends GameObject {

    public Star(Vector2 pos) {
        super(pos, ArtManager.getInstance().getStars(),"star");

        float starSize = MathUtils.random(0.1f , 0.5f);

        this.setScale(starSize);

    }



}
