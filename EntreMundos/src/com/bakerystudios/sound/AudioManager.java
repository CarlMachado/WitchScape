package com.bakerystudios.sound;

import com.bakerystudios.engine.Updateble;

public class AudioManager implements Updateble {
	
	private boolean music = true;
	private boolean sound = true;
	
	private Audio musicBackground;
	
	public AudioManager() {
		musicBackground = new Music("/audios/music.wav", 1000);
	}
	
	private void newBackground() {
		// TODO: criar a logica de troca de background
		musicBackground = new Music("/audios/music.wav", 1000);
	}

	@Override
	public void update() {
		if(music) {
			if(!musicBackground.isPlaying()) {
				musicBackground.play();
			}
			if(musicBackground.end()) {
				newBackground();
			}
		}
		if(sound) {
			
		}
	}
	
}
