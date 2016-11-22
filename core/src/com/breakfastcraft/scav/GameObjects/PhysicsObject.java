package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lunchington on 11/19/2016.
 */
public class PhysicsObject extends GameObject {
    protected Body body;

    public PhysicsObject(Vector2 pos) {
        super(pos);
    }

    @Override
    public float getX() { return body.getPosition().x; }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getRotation() {
        return (float) Math.toDegrees(body.getAngle());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        this.body.setTransform(x,y,body.getAngle());
    }

    public void setBody(Body body) {
        this.body = body;
    }
    public Body getBody() {
        return body;
    }

    public void setShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth()/2, getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        getBody().createFixture(fixtureDef);
        shape.dispose();
    }

    public void init(World physicsWorld) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        setBody(physicsWorld.createBody(bodyDef));
        setShape();
    }
}
