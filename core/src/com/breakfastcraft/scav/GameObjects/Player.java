package com.breakfastcraft.scav.GameObjects;


import com.badlogic.gdx.math.Vector2;
import com.breakfastcraft.scav.managers.ArtManager;

public class Player extends Ship {

    public Player(Vector2 pos) {
        super(pos, ArtManager.getInstance().getShips(),"player");

    }


}
