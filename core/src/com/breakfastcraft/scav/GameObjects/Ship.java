package com.breakfastcraft.scav.GameObjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ship extends PhysicsObject{

    private boolean brakes;
    private boolean forwardThrust,reverseThrust;

    private boolean lateralThrustLeft,lateralThrustRight;
    private boolean turnThrustLeft,turnThrustRight;

    private float turnSpeed = 1f;
    private float lateralSpeed= 100f;

    private float forwardSpeed= 5f;

    private float currentThrust = 0f;
    private float maxThrust =2f;

    private float acceleration;

    private float weight;

    private float range;


    public Ship(Vector2 pos) {
        super(pos);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Vector2 facing;


        Vector2 move = Vector2.Zero;
        float turning = 0f;

        if (isBrakes())
            body.setLinearDamping(0.05f);
        else
            body.setLinearDamping(0);

        if (isForwardThrust()) {
                facing = body.getWorldVector(new Vector2(1,0));
                move = facing.scl(forwardSpeed);
        }

        if (isReverseThrust()) {
            facing = body.getWorldVector(new Vector2(1,0));
            move = facing.scl(-forwardSpeed);
        }


        if (isLateralThrustLeft()) {
            facing = body.getWorldVector(new Vector2(0,1));
            move = facing.scl(lateralSpeed);
        }

        if (isLateralThrustRight()) {
            facing = body.getWorldVector(new Vector2(0,1));
            move = facing.scl(-lateralSpeed);
        }

        if (isTurnThrustRight()) {
            turning = -turnSpeed *MathUtils.degreesToRadians;
        }

        if (isTurnThrustRight()) {
            turning = -turnSpeed *MathUtils.degreesToRadians;
        }


        if (isTurnThrustLeft()) {
            turning = turnSpeed *MathUtils.degreesToRadians;
        }

        if (isTurnThrustRight()) {
            turning = -turnSpeed *MathUtils.degreesToRadians;
        }

        move.nor();

        body.setAngularVelocity(turning);
        body.applyLinearImpulse(move, body.getPosition(), true);

        Vector2 velocity = body.getLinearVelocity();
        float speed = velocity.len();

        if(speed > maxThrust)
            body.setLinearVelocity(velocity.scl(maxThrust / speed));


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



}
