package com.breakfastcraft.scav;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breakfastcraft.scav.managers.*;

public class Main extends Game {
	public static boolean DEV_MODE = false;


	public void create() {

		ArtManager.getInstance().load();

		MusicManager.getInstance().setVolume(PreferencesManager.getInstance().getMusicVolume());
		MusicManager.getInstance().setEnabled(PreferencesManager.getInstance().isMusicEnabled());

		SoundManager.getInstance().setVolume(PreferencesManager.getInstance().getSoundVolume());
		SoundManager.getInstance().setEnabled(PreferencesManager.getInstance().isSoundEnabled());

		ScreenManager.init(this);
	}

	@Override
	public void dispose() {
		super.dispose();

		MusicManager.getInstance().dispose();
		SoundManager.getInstance().dispose();
		ScreenManager.getInstance().dispose();
		ArtManager.getInstance().dispose();

	}

	public static void toggleDevMode() {
		DEV_MODE = !DEV_MODE;
	}

}

