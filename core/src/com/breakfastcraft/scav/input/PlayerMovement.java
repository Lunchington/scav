package com.breakfastcraft.scav.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.GameObjects.Player;

public class PlayerMovement extends InputAdapter {

    private  Player player;

    public PlayerMovement(Player p ) { this.player = p; }

    @Override
    public boolean keyUp(int key) {

        switch (key) {
            case Input.Keys.W:
                player.setForwardThrust(false);
                return true;
            case Input.Keys.S:
                player.setReverseThrust(false);
                return true;

            case Input.Keys.Q:
                player.setLateralThrustLeft(false);
                return true;
            case Input.Keys.E:
                player.setLateralThrustRight(false);
                return true;

            case Input.Keys.A:
                player.setTurnThrustLeft(false);
                return true;
            case Input.Keys.D:
                player.setTurnThrustRight(false);
                return true;
            case Input.Keys.SPACE:
                player.setBrakes(false);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean keyDown(int key) {

        switch (key) {
            case Input.Keys.W:
                player.setForwardThrust(true);
                return true;
            case Input.Keys.S:
                player.setReverseThrust(true);
                return true;

            case Input.Keys.Q:
                player.setLateralThrustLeft(true);
                return true;
            case Input.Keys.E:
                player.setLateralThrustRight(true);
                return true;

            case Input.Keys.A:
                player.setTurnThrustLeft(true);
                return true;
            case Input.Keys.D:
                player.setTurnThrustRight(true);
                return true;

            case Input.Keys.SPACE:
                player.setBrakes(true);
                return true;

            default:
                return false;
        }
    }

    public Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    public Player getPlayer() {
        return player;
    }
}
