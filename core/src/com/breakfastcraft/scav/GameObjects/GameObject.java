package com.breakfastcraft.scav.GameObjects;


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
    protected float scale;


    protected float width;
    protected float height;
    
    private TextureRegion texture;



    public GameObject( Vector2 pos) {
        this.position = pos;
        setTexture(ArtManager.getInstance().getDefault().findRegion("default"));
    }

    public float getX() { return position.x; }
    public float getY() {
        return position.y;
    }

    public float getWidth() {return width; }
    public float getHeight() {return height; }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x,y);
    }
    public Vector2 getPosition() { return position; }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public float getRotation() { return rotation; }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionWidth();
        this.rotation = 0;
        this.scale = 1;
    }

    public float getRight() { return getX() + getWidth(); }
    public float getTop() { return getY() + getHeight(); }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,getX()- getWidth()/2,getY()- getHeight()/2,getWidth()/2,getHeight()/2,getWidth(),getHeight(),1,1,getRotation());
    }

    @Override
    public void update(float delta){}




}
