package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Star extends GameObject {
    private Texture t;

    public Star(Vector2 pos) {
        super(pos);
        int starSize = MathUtils.random(4, 16);
        Pixmap pixmap = new Pixmap(starSize, starSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.SLATE);
        pixmap.fillCircle(starSize /2, starSize /2, starSize /2/2);

        t = new Texture(pixmap);
        this.width = starSize;
        this.height = starSize;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(t,getX(),getY());
    }

}
