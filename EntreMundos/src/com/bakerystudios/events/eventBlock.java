package com.bakerystudios.events;

import java.awt.image.BufferedImage;

import com.bakerystudios.entities.Entity;

public class eventBlock extends Entity {

	private boolean active = false;
	private boolean tryActive = false;
	
	private boolean choose = false;
	
	public eventBlock(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isTryActive() {
		return tryActive;
	}

	public void setTryActive(boolean tryActive) {
		this.tryActive = tryActive;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

}
