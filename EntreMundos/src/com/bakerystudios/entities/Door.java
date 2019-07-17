package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.game.Game;

public class Door extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean tryAnimation = false;
	private boolean animation = false;

	private boolean openDoor = false;
	private boolean openDoorLocale = false;
	
	private String identify;
	private boolean chave;
	
	private boolean choose = false;

	private int currentAnimacao = 0;
	private int maxAnimacao = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	public Door(int x, int y, int width, int height, BufferedImage sprite, String identify, boolean chave) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = Game.doors.getSprite(0, 16 * i, Tile.SIZE, Tile.SIZE);
		}
		this.maxAnimacao = sprites.length;
		this.setIdentify(identify);
		this.chave = chave;
	}

	@Override
	public void update() {
		if (animation) {
			tryAnimation = false;
			if (!openDoorLocale) { // Abre porta
				currentFrame++;
				if (currentFrame >= maxFrame) {
					currentFrame = 0;
					currentAnimacao++;
				}
				if (currentAnimacao >= maxAnimacao) {
					currentFrame = 0;
					animation = false;
					openDoorLocale = true;
					openDoor = true;
					//choose = false;
					currentAnimacao = maxAnimacao - 1;
				}
			} else { // Fecha porta
				currentFrame++;
				openDoor = false;
				if (currentFrame >= maxFrame) {
					currentFrame = 0;
					currentAnimacao--;
				}
				if (currentAnimacao <= 0) {
					currentFrame = 0;
					openDoorLocale = false;
					openDoor = false;
					//choose = false;
					animation = false;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {		
		g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean getOpenDoor() {
		return openDoor;
	}

	public void setOpenDoor(boolean value) {
		openDoor = value;
	}

	public boolean getTryAnimation() {
		return tryAnimation;
	}

	public void setTryAnimation(boolean value) {
		tryAnimation = value;
	}

	public boolean getAnimation() {
		return animation;
	}

	public void setAnimation(boolean value) {
		animation = value;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public boolean isChave() {
		return chave;
	}

	public void setChave(boolean chave) {
		this.chave = chave;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

}
