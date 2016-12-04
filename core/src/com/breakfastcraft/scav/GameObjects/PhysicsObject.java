package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.breakfastcraft.scav.Global;
import com.breakfastcraft.scav.managers.ArtManager;

/**
 * Created by Lunchington on 11/19/2016.
 */
public class PhysicsObject extends GameObject {
    protected Body body;

    public PhysicsObject(Vector2 pos, TextureAtlas atlas, String sprite) {
        super(pos, ArtManager.getInstance().getShips(),"player");
    }


    @Override
    public void update(float delta) {
        setPosition(    body.getPosition().x, body.getPosition().y);
        setRotation(body.getAngle());
    }

    public void setBody(Body body) {
        this.body = body;
    }
    public Body getBody() {
        return body;
    }

    public void setShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2 / Global.PPM, getHeight()/2 / Global.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.1f;

        getBody().createFixture(fixtureDef);
        shape.dispose();
    }

    public void init(World physicsWorld) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());
        bodyDef.linearDamping = 0f;
        setBody(physicsWorld.createBody(bodyDef));
        setShape();
    }


}
