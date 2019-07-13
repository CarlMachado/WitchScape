package com.bakerystudios.sound;

import java.applet.Applet;
import java.applet.AudioClip;

@SuppressWarnings("deprecation")
public abstract class Audio {

	private AudioClip clip;
	
	public Audio(String name) {
		try {
			clip = Applet.newAudioClip(Audio.class.getResource(name));
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			new Thread() {
				public void run() {
					clip.stop();
				}
			}.start();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

	public AudioClip getClip() {
		return clip;
	}

}
