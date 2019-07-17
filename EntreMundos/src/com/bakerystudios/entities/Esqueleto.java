package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;

public class Esqueleto extends Entity implements Renderable, Updateble {

	private int currentAnimation = 0;
	private int maxAnimation = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	private BufferedImage[] sprites;

	private boolean eventEsqueleto = false;

	protected boolean existEventEsqueleto = false;
	protected boolean tryEventActiveEsqueleto = false;
	protected boolean eventActiveEsqueleto = false;

	public Esqueleto(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEventEsqueleto) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		sprites = spriteList;
		maxAnimation = sprites.length - 1;
		this.existEventEsqueleto = existEventEsqueleto;
	}

	public void update() {
		animation();
		if (eventActiveEsqueleto)
			eventEsqueleto = true;
		else
			eventEsqueleto = false;
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
		if (eventEsqueleto) {
			System.out.println("eventoEsqueleto");
		}
	}

	public boolean isEventEsqueleto() {
		return eventEsqueleto;
	}

	public void setEventEsqueleto(boolean eventEsqueleto) {
		this.eventEsqueleto = eventEsqueleto;
	}

	public boolean isExistEventEsqueleto() {
		return existEventEsqueleto;
	}

	public void setExistEventEsqueleto(boolean existEventEsqueleto) {
		this.existEventEsqueleto = existEventEsqueleto;
	}

	public boolean isEventActiveEsqueleto() {
		return eventActiveEsqueleto;
	}

	public void setEventActiveEsqueleto(boolean eventActiveEsqueleto) {
		this.eventActiveEsqueleto = eventActiveEsqueleto;
	}

	public boolean isTryEventActiveEsqueleto() {
		return tryEventActiveEsqueleto;
	}

	public void setTryEventActiveEsqueleto(boolean tryEventActiveEsqueleto) {
		this.tryEventActiveEsqueleto = tryEventActiveEsqueleto;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

}
