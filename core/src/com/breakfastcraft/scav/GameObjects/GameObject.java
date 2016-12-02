package com.breakfastcraft.scav.GameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.Global;
import com.breakfastcraft.scav.managers.ArtManager;


public abstract class GameObject implements iGameObject {
    protected int ID;
    protected String name;
    protected Vector2 position;
    protected float rotation;

    protected Sprite sprite;

    public GameObject( Vector2 pos, TextureAtlas atlas, String sprite) {
        this(pos);
        this.sprite = new Sprite(atlas.findRegion(sprite).getTexture());
    }

    public GameObject(Vector2 pos) {
        this.position = pos;
        this.rotation = 0;
    }

    public float getX() { return position.x; }
    public float getY() {
        return position.y;
    }

    public float getWidth() {return sprite.getWidth(); }
    public float getHeight() {return sprite.getHeight(); }

    public float getWorldWidth() {return sprite.getWidth() / Global.PPM ; }
    public float getWorldHeight() {return sprite.getHeight() / Global.PPM; }

    public float getOriginX() { return  sprite.getOriginX(); }
    public float getOriginY() { return  sprite.getOriginY(); }

    public float getScaleX() { return  sprite.getScaleX(); }
    public float getScaleY() { return  sprite.getScaleY(); }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x,y);
    }
    public Vector2 getPosition() { return position; }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public float getRotation() { return rotation; }

    public float getRight() { return getX() + getWidth(); }
    public float getBottom() { return getY() + getHeight(); }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite,
                getX() -getWidth() /2 / Global.PPM,
                getY()- getHeight()/2 / Global.PPM ,
                getOriginX()/ Global.PPM ,
                getOriginY() / Global.PPM,
                getWorldWidth(),
                getWorldHeight(),
                getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void update(float delta){}


    public void setScale(float scale) {
        this.sprite.setScale(scale);
    }
}
