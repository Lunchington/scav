package com.breakfastcraft.scav.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.breakfastcraft.scav.utils.LRUCache;
import com.breakfastcraft.scav.utils.LRUCache.CacheEntryRemovedListener;


public final class SoundManager implements CacheEntryRemovedListener<SoundManager.GameSound, Sound>, Disposable {
    private static  SoundManager INSTANCE;

    public static SoundManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SoundManager();
        }
        return INSTANCE;
    }

    private SoundManager() {
        soundCache = new LRUCache<SoundManager.GameSound, Sound>(10);
        soundCache.setEntryRemovedListener(this);
    }

    private float volume = 1f;
    private boolean enabled = true;
    private final LRUCache<GameSound, Sound> soundCache;

    public enum GameSound {
        CLICK("sounds/click.wav");

        private final String fileName;

        GameSound(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }



    public void play(GameSound sound) {
        // check if the sound is enabled
        if (!enabled) return;

        // try and get the sound from the cache
        Sound soundToPlay = soundCache.get(sound);
        if (soundToPlay == null) {
            FileHandle soundFile = Gdx.files.internal(sound.getFileName());
            soundToPlay = Gdx.audio.newSound(soundFile);
            soundCache.add(sound, soundToPlay);
        }

        soundToPlay.play(volume);
    }

    public void setVolume(float volume) {

        if (volume < 0 || volume > 1f) { throw new IllegalArgumentException("The volume must be inside the range: [0,1]"); }
        this.volume = volume;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void notifyEntryRemoved(GameSound key, Sound value) {
        value.dispose();
    }

    public void dispose() {
        for (Sound sound : soundCache.retrieveAll()) {
            sound.stop();
            sound.dispose();
        }
    }
}
