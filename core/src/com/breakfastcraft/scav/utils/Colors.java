package com.breakfastcraft.scav.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ObjectMap;

public final class Colors {

    private static final ObjectMap<String, Color> fallbackMap = new ObjectMap<String, Color>();
    static {
        // set predefined colors
    }
    private static final ObjectMap<String, Color> map = new ObjectMap<String, Color>();
    public static Color get(String name) {
        Color color = map.get(name);
        return color == null ? fallbackMap.get(name) : color;
    }
    public static void put(String name, Color color) {
        map.put(name, color);
    }
    public static void clear() {
        map.clear();
    }

    // add here all public methods of ObjectMap but with the static modifier
}