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

	private boolean eventPrincesa = false;

	protected boolean existEventPrincesa = false;
	protected boolean tryEventActivePrincesa = false;
	protected boolean eventActivePrincesa = false;

	public Princesa(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEvent) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		sprites = spriteList;
		maxAnimation = sprites.length - 1;
		this.existEventPrincesa = existEvent;
	}

	public void update() {
		animation();
		if (eventActivePrincesa)
			eventPrincesa = true;
		else
			eventPrincesa = false;
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
		if (eventPrincesa) {
			System.out.println("eventoPrincesa");
		}
	}

	public boolean isEventPrincesa() {
		return eventPrincesa;
	}

	public void setEventPrincesa(boolean eventPrincesa) {
		this.eventPrincesa = eventPrincesa;
	}

	public boolean isExistEventPrincesa() {
		return existEventPrincesa;
	}

	public void setExistEventPrincesa(boolean existEventPrincesa) {
		this.existEventPrincesa = existEventPrincesa;
	}

	public boolean isEventActivePrincesa() {
		return eventActivePrincesa;
	}

	public void setEventActivePrincesa(boolean eventActivePrincesa) {
		this.eventActivePrincesa = eventActivePrincesa;
	}

	public boolean isTryEventActivePrincesa() {
		return tryEventActivePrincesa;
	}

	public void setTryEventActivePrincesa(boolean tryEventActivePrincesa) {
		this.tryEventActivePrincesa = tryEventActivePrincesa;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

}
