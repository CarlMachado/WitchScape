package com.bakerystudios.sound;

import com.bakerystudios.engine.Updateble;

public class AudioManager implements Updateble {
	
	private boolean music = true;
	private boolean sound = true;
	
	private Audio musicBackground;
	private boolean playingBk = false;
	
	public AudioManager() {
		musicBackground = new Music("/audios/music.wav");
	}
	
	@Override
	public void update() {
		if(music) {
			if(!playingBk) {
				musicBackground.loop();
				playingBk = true;
			}
		}
		if(sound) {
			
		}
	}
	
}
