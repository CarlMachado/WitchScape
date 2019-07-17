package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;

public class Princesa extends Entity implements Renderable, Updateble {

	private int currentAnimation = 0;
	private int maxAnimation = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	private BufferedImage[] sprites;

	private boolean event = false;

	protected boolean existEvent = false;
	protected boolean tryEventActive = false;
	protected boolean eventActive = false;

	public Princesa(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEvent) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		sprites = spriteList;
		maxAnimation = sprites.length - 1;
		this.existEvent = existEvent;
	}

	public void update() {
		animation();
		if (eventActive)
			event = true;
		else
			event = false;
	}

	public void animation() {
		currentFrame++;
		if (currentFrame == maxFrame) {
			currentFrame = 0;
			currentAnimation++;
			if (currentAnimation >= maxAnimation)
				currentAnimation = 0;
		} else
			currentAnimation = 0;
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimation], this.getX() - Camera.x, this.getY() - Camera.y, null);
		if (event) {
			System.out.println("evento");
		}
	}

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public boolean isExistEvent() {
		return existEvent;
	}

	public void setExistEvent(boolean existEvent) {
		this.existEvent = existEvent;
	}

	public boolean isEventActive() {
		return eventActive;
	}

	public void setEventActive(boolean eventActive) {
		this.eventActive = eventActive;
	}

	public boolean isTryEventActive() {
		return tryEventActive;
	}

	public void setTryEventActive(boolean tryEventActive) {
		this.tryEventActive = tryEventActive;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

}
