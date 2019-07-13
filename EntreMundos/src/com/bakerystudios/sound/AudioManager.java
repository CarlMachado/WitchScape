package com.bakerystudios.sound;

import com.bakerystudios.engine.Updateble;

public class AudioManager implements Updateble {
	
	private boolean music = true;
	private boolean sound = true;
	
	private final Audio musicBackground;
	
	public AudioManager() {
		musicBackground = new Music("/music.wav", 1000);
	}

	@Override
	public void update() {
		if(music) {
			if(!musicBackground.isPlaying()) {
				musicBackground.play();
			}
			musicBackground.getTc().count();
			if(musicBackground.getTc().satisfied()) {
				System.out.println("deu o tempo");
			}
		}
		if(sound) {
			
		}
	}
	
}
