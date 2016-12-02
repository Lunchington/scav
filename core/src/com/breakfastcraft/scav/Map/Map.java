package com.breakfastcraft.scav.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.breakfastcraft.scav.GameObjects.GameObject;
import com.breakfastcraft.scav.GameObjects.PhysicsObject;
import com.breakfastcraft.scav.GameObjects.Player;
import com.breakfastcraft.scav.GameObjects.Star;
import com.breakfastcraft.scav.Global;


import java.util.ArrayList;

public class Map {

    private World physicsWorld;
    private float accumulator = 0;

    private int mapChunksHeight;
    private int mapChunksWidth;

    private Chunk[][] chunks;

    private ArrayList<GameObject> gameObjects =new ArrayList<GameObject>();
    private ArrayList<Star> starObjects =new ArrayList<Star>();

    private Player player;

    private Camera camera;

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) {
        this.player = player;
        player.init(getPhysicsWorld());
    }

    public int getMapChunksHeight() { return  mapChunksHeight; }
    public int getMapChunksWidth() { return  mapChunksWidth; }

    public float getMapWorldHeight() { return  mapChunksHeight * Global.CHUNKHEIGHT; }
    public float getMapWorldWidth() { return  mapChunksWidth * Global.CHUNKWIDTH; }


    public Map(int cHeight, int cWidth, Camera camera) {
        this.camera = camera;

        this.mapChunksWidth = cWidth;
        this.mapChunksHeight = cHeight;
        chunks = new Chunk[cWidth][cHeight];

        physicsWorld =  new World( Vector2.Zero, true);
/*
        float sX = camera.position.x - camera.viewportWidth/2;
        float sY = camera.position.y - camera.viewportHeight/2;

        float eX = camera.position.x + camera.viewportWidth/2;
        float eY = camera.position.y + camera.viewportHeight/2;

        Vector2 start = new Vector2( sX, sY);
        Vector2 end  = new Vector2( eX, eY);
*/

        for (int x = 0; x < chunks.length; x++) {
            for (int y = 0; y < chunks[x].length; y++) {
               chunks[x][y] = new Chunk(50*9,100*9,new Vector2(x,y));
            }
        }
    }

    public void update(float delta ) {
        step(delta);

        player.update(delta);

        for (GameObject e : gameObjects) {
            e.update(delta);
        }

    }


    public void step(float delta) {
        float frameTime = Math.min(delta, 0.25f);

        accumulator += frameTime;

        while (accumulator >= Global.TIME_STEP) {
            physicsWorld.step(Global.TIME_STEP, Global.VELOCITY_ITERATIONS, Global.POSITION_ITERATIONS);
            accumulator -= Global.TIME_STEP;
        }
    }


    public void render(SpriteBatch batch) {

        float startX = (int)(camera.position.x - camera.viewportWidth/2);
        if (startX < 0) startX = 0;

        float startY = (int)(camera.position.y - camera.viewportHeight/2);
        if (startY < 0) startY = 0;

        float endX = (int)(camera.position.x + camera.viewportWidth/2 );
        if (endX > getMapWorldWidth()) endX = getMapWorldWidth();

        float endY = (int)(camera.position.y + camera.viewportHeight/2 );
        if (endY >getMapWorldHeight()) endY = getMapWorldHeight();

        for (Chunk[] chunk : chunks) {
            for (Chunk aChunk : chunk) {
                for (Star e : aChunk.getStars())
                    if (e.getRight() >= startX && e.getX() <= endX && e.getBottom() >= startY && e.getY() <= endY)
                        e.render(batch);
            }
        }

        for (GameObject e : gameObjects) {
            if (e.getRight() >= startX && e.getX() <= endX && e.getBottom() >= startY && e.getY() <= endY)
                e.render(batch);
        }

        player.render(batch);
    }

    public void add(GameObject e) {
        if (e instanceof PhysicsObject)
            ((PhysicsObject) e).init(physicsWorld);

        gameObjects.add(e);
    }

    public void addStar(Star s) {
        starObjects.add(s);
    }


    public void dispose() {
        physicsWorld.dispose();
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }



    public Chunk getChunkFromWorldPos(Vector2 pos) {
        float x = (float) Math.floor(pos.x/Global.CHUNKWIDTH);
        float y = (float) Math.floor(pos.y/Global.CHUNKHEIGHT);

        for (Chunk[] chunk : chunks) {
            for (Chunk aChunk : chunk) {
                Vector2 c = aChunk.getChunkNum();
                if (c.x == x && c.y == y)
                    return aChunk;
            }
        }
        return null;
    }

    public Chunk getChunk(int x , int y ) {
        return chunks[x][y];
    }



}
