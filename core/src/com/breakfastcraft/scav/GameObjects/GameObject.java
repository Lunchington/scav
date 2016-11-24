package com.breakfastcraft.scav.GameObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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

    public float getOriginX() { return  sprite.getWidth() /2; }
    public float getOriginY() { return  sprite.getHeight() /2; }

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
    public float getTop() { return getY() + getHeight(); }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite,getX()- getWidth()/2,getY()- getHeight()/2,getOriginX(),getOriginY(), getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void update(float delta){}




}
