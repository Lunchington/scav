package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.managers.ArtManager;

public class Ship extends PhysicsObject{

    private boolean brakes;
    private boolean forwardThrust,reverseThrust;

    private boolean lateralThrustLeft,lateralThrustRight;
    private boolean turnThrustLeft,turnThrustRight;

    private float turnSpeed = 50f;
    private float lateralSpeed= 25f;

    private float forwardSpeed= 1f;

    private float currentThrust = 0f;
    private float maxThrust = 50f;


    private float weight;

    private float range;


    public Ship(Vector2 pos, TextureAtlas ships, String player) {
        super(pos, ArtManager.getInstance().getShips(),"player");
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Vector2 facing = Vector2.Zero;


        Vector2 move = Vector2.Zero;
        float turning = 0f;




        if (isForwardThrust()) {
                facing = body.getWorldVector(new Vector2(1,0));
                //move = facing.scl(forwardSpeed);
                move = getMove(facing,forwardSpeed);

        }

        if (isReverseThrust()) {
            facing = body.getWorldVector(new Vector2(1,0));
            move = getMove(facing,-forwardSpeed);
        }


        if (isLateralThrustLeft()) {
            facing = body.getWorldVector(new Vector2(0,1));
            move = getMove(facing,lateralSpeed);
        }

        if (isLateralThrustRight()) {
            facing = body.getWorldVector(new Vector2(0,1));
            move = getMove(facing,-lateralSpeed);
        }

        if (isTurnThrustRight()) {
            turning = -turnSpeed *MathUtils.degreesToRadians;
        }

        if (isTurnThrustLeft()) {
            turning = turnSpeed *MathUtils.degreesToRadians;
        }


        System.out.println(move);

        body.setAngularVelocity(turning);
        body.applyForce(move, body.getWorldCenter(),true);



        if(getCurrentThrust() > maxThrust)
            body.setLinearVelocity(getCurrentVelocity().scl(maxThrust/getCurrentThrust()));


    }


    public Vector2 getMove(Vector2 facing, float speed) {
        facing.nor();
        return facing.scl(speed);
    }

    public void setTurnThrustLeft(boolean turnThrust) {
        this.turnThrustLeft = turnThrust;
    }
    public boolean isTurnThrustLeft() { return turnThrustLeft; }

    public void setTurnThrustRight(boolean turnThrust) {
        this.turnThrustRight = turnThrust;
    }
    public boolean isTurnThrustRight() { return turnThrustRight; }


    public void setLateralThrustLeft(boolean lateralThrust) {
        this.lateralThrustLeft = lateralThrust;
    }
    public boolean isLateralThrustLeft() { return lateralThrustLeft; }

    public void setLateralThrustRight(boolean lateralThrust) {
        this.lateralThrustRight = lateralThrust;
    }
    public boolean isLateralThrustRight() { return lateralThrustRight; }


    public void setForwardThrust(boolean thrust) {
        this.forwardThrust = thrust;
    }
    public boolean isForwardThrust() { return forwardThrust; }

    public void setReverseThrust(boolean thrust) {
        this.reverseThrust = thrust;
    }
    public boolean isReverseThrust() { return reverseThrust; }

    public boolean isBrakes() { return brakes; }
    public void setBrakes(boolean brakes) { this.brakes = brakes; }


    public float getCurrentThrust() {
        return getCurrentVelocity().len();
    }
    public Vector2 getCurrentVelocity() { return body.getLinearVelocity(); }

}
