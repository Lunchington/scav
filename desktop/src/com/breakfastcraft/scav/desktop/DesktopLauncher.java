package com.breakfastcraft.scav.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.breakfastcraft.scav.Global;
import com.breakfastcraft.scav.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Global.TITLE + " v" + Global.VERSION;

		config.width = Global.WIDTH;
		config.height = Global.HEIGHT;

		config.vSyncEnabled = false; // Setting to false disables vertical sync
		config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
		config.backgroundFPS = 0; // Setting to 0 disables background fps throttling

		config.addIcon("icon32x32.png", Files.FileType.Internal);
		config.addIcon("icon64x64.png", Files.FileType.Internal);
		config.addIcon("icon128x128.png", Files.FileType.Internal);

		new LwjglApplication(new Main(), config);
	}
}
