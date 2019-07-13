package com.bakerystudios.sound;

import java.applet.Applet;
import java.applet.AudioClip;

import com.bakerystudios.engine.TimeCounter;

@SuppressWarnings("deprecation")
public abstract class Audio {

	private AudioClip clip;
	private boolean playing;
	private TimeCounter tc;
	
	public Audio(String name, long condition) {
		try {
			clip = Applet.newAudioClip(Audio.class.getResource(name));
			tc = new TimeCounter(condition);
			playing = false;
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public boolean end() {
		if(tc.satisfied()) {
			playing = false;
			return true;
		}
		return false;
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
		tc.count();
		playing = true;
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

	public void setClip(AudioClip clip) {
		this.clip = clip;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public TimeCounter getTc() {
		return tc;
	}

	public void setTc(TimeCounter tc) {
		this.tc = tc;
	}
}
