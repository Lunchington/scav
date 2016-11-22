package com.breakfastcraft.scav.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public final class PreferencesManager {
    private static  PreferencesManager INSTANCE;

    public static PreferencesManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesManager();
        }
        return INSTANCE;
    }
    private PreferencesManager() {}


    private static final String PREF_MUSIC_VOLUME = "music.volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_VOLUME = "sound.volume";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREFS_NAME = "scav";

    private Preferences prefs =  Gdx.app.getPreferences(PREFS_NAME);

    public boolean isSoundEnabled() {
        return prefs.getBoolean(PREF_SOUND_ENABLED, true);
    }
    public void setSoundEnabled(boolean soundEffectsEnabled) {
        prefs.putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        prefs.flush();
    }

    public boolean isMusicEnabled() {
        return prefs.getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        prefs.putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        prefs.flush();
    }

    public float getMusicVolume() {
        return prefs.getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    public void setMusicVolume(float volume) {
        prefs.putFloat(PREF_MUSIC_VOLUME, volume);
        prefs.flush();
    }
    
    public float getSoundVolume() {
        return prefs.getFloat(PREF_SOUND_VOLUME, 0.5f);
    }

    public void setSoundVolume(float volume) {
        prefs.putFloat(PREF_SOUND_VOLUME, volume);
        prefs.flush();
    }

}
