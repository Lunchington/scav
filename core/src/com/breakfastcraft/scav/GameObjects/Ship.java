package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.managers.ArtManager;

public class Ship extends PhysicsObject{

    private boolean brakes;
    private boolean thrust;


    private float forwardSpeed= 1f;

    private float maxThrust = 50f;




    public Ship(Vector2 pos, TextureAtlas ships, String player) {
        super(pos, ArtManager.getInstance().getShips(),"player");
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Vector2 facing = Vector2.Zero;


        Vector2 move = Vector2.Zero;
        float turning = 0f;


        if (isThrust()) {
                facing = body.getWorldVector(new Vector2(1,0));
                //move = facing.scl(forwardSpeed);
                move = getMove(facing,forwardSpeed);

        }


        body.setAngularVelocity(turning);
        body.applyForce(move, body.getWorldCenter(),true);

        if(getCurrentThrust() > maxThrust)
            body.setLinearVelocity(getCurrentVelocity().scl(maxThrust/getCurrentThrust()));


    }


    public Vector2 getMove(Vector2 facing, float speed) {
        return facing.scl(speed);
    }

    public void setThrust(boolean thrust) {
        this.thrust = thrust;
    }
    public boolean isThrust() { return thrust; }

    public float getCurrentThrust() {
        return getCurrentVelocity().len();
    }
    public Vector2 getCurrentVelocity() { return body.getLinearVelocity(); }

}
