package com.breakfastcraft.scav.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.breakfastcraft.scav.GameObjects.Player;
import javafx.scene.Camera;

public class PlayerMovement extends InputAdapter {

    private  Player player;

    private OrthographicCamera camera;
    private Vector2 prevMouse;
    private Vector2 currentMouse;

    public PlayerMovement(Player p, OrthographicCamera camera ) {
        this.player = p;
        this.camera = camera ;

        Vector3 m3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        prevMouse = new Vector2(m3.x, m3.y);
        currentMouse = new Vector2(m3.x, m3.y);
    }

    @Override
    public boolean keyUp(int key) {
        switch (key) {
            default:
                return false;
        }
    }

    @Override
    public boolean keyDown(int key) {
        switch (key) {
            default:
                return false;
        }
    }


    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {



        setPlayerAngle(screenX, screenY);

        player.setThrust(true);

        return false;
    }

    private void setPlayerAngle(int screenX, int screenY) {
        Vector3 m3 = camera.unproject(new Vector3(screenX,screenY,0));
        Vector2 mouse = new Vector2(m3.x, m3.y);

        float targetAngle = mouse.sub(player.getPosition()).angleRad();

        player.getBody().setLinearDamping(0f);
        player.getBody().setTransform(player.getPosition(), targetAngle);
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        prevMouse = currentMouse;
        Vector3 m3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        currentMouse = new Vector2(m3.x, m3.y);
        player.getBody().setLinearDamping(0.5f);

        player.setThrust(false);

        return false;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        setPlayerAngle(screenX, screenY);

        return false;
    }


    public Player getPlayer() {
        return player;
    }


}
