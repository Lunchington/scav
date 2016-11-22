package com.breakfastcraft.scav.Map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.GameObjects.GameObject;
import com.breakfastcraft.scav.GameObjects.Star;
import com.breakfastcraft.scav.Global;

import javax.xml.bind.annotation.XmlElementDecl;
import java.util.ArrayList;

/**
 * Created by Lunchington on 11/21/2016.
 */
public class Chunk {

    //position of topleft of chunk in the world.
    private Vector2 worldPos;
    private Vector2 chunkNum;

    private ArrayList<GameObject> gameObjects =new ArrayList<GameObject>();
    private ArrayList<Star> starObjects =new ArrayList<Star>();


    public  Chunk(int minStars, int maxStars, Vector2 chunkNum) {
        this.chunkNum = chunkNum;
        this.worldPos = new Vector2(chunkNum.x * Global.CHUNKWIDTH, chunkNum.y * Global.CHUNKHEIGHT);
        generateStarFieldChunk(minStars,maxStars);
    }

    public void generateStarFieldChunk(int minStars, int maxStars) {
        int numstars = MathUtils.random(minStars,maxStars);


        for (int i =0; i < numstars; i++) {
            float startX= worldPos.x;
            float endX = worldPos.x + Global.CHUNKWIDTH;

            float startY = worldPos.y;
            float endY = worldPos.y  + Global.CHUNKHEIGHT;

            float rX = MathUtils.random(startX, endX);
            float rY = MathUtils.random(startY, endY);

            Vector2 pos = new Vector2(rX,rY);

            addStar(new Star(pos));
        }
    }

    private void addStar(Star s) {
        starObjects.add(s);
    }


    public Vector2 getChunkNum() {
        return  chunkNum;
    }

    public Vector2 getObjChunkPostionFromWorld(Vector2 pos) {
        float x = pos.x - chunkNum.x * Global.CHUNKWIDTH;
        float y= pos.y - chunkNum.y * Global.HEIGHT;

        return  new Vector2(x,y);
    }

    public Vector2 getChunkWorldCenter() {
        return new Vector2(worldPos.x + Global.CHUNKWIDTH/2, worldPos.y  +Global.CHUNKHEIGHT/2);
    }

        public String toString() {
            return "Chunk Num: " + chunkNum + " worldPos: " + worldPos + " worldCenter: " + getChunkWorldCenter();
        }


    public ArrayList<Star> getStars() { return starObjects; }
    public ArrayList<GameObject> getObjects() { return gameObjects; }

}
