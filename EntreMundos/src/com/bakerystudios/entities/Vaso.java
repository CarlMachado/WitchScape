package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.game.Game;

public class Vaso extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean animation = false;

	private boolean tryEventActiveVaso = false;
	private boolean eventActiveVaso = false;
	private boolean broken = false;

	private boolean choose = false;

	private boolean drope = false;
	private String idDrop;
	private String shortNameDrop;
	private BufferedImage imageDrop;

	private int currentAnimacao = 0;
	private int maxAnimacao = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	public Vaso(int x, int y, int width, int height, BufferedImage sprite, String idDrop, String shortNameDrop,
			BufferedImage imageDrop) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = Game.doors.getSprite(144, 16 * i, Tile.SIZE, Tile.SIZE);
		}
		this.maxAnimacao = sprites.length;
		this.idDrop = idDrop;
		this.shortNameDrop = shortNameDrop;
		this.imageDrop = imageDrop;
		sprite = sprites[currentAnimacao];
	}

	public void update() {
		if (animation) {
			tryEventActiveVaso = false;
			currentFrame++;
			if (currentFrame >= maxFrame) {
				currentFrame = 0;
				currentAnimacao++;
				if (currentAnimacao >= 0 && currentAnimacao < maxAnimacao)
					sprite = sprites[currentAnimacao];
			}
			if (currentAnimacao >= maxAnimacao) {
				currentFrame = 0;
				animation = false;
				choose = false;
				broken = true;
				currentAnimacao = maxAnimacao - 1;
				sprite = sprites[currentAnimacao];
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public int getCurrentAnimacao() {
		return currentAnimacao;
	}

	public void setCurrentAnimacao(int currentAnimacao) {
		this.currentAnimacao = currentAnimacao;
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public int getMaxAnimacao() {
		return maxAnimacao;
	}

	public void setMaxAnimacao(int maxAnimacao) {
		this.maxAnimacao = maxAnimacao;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public boolean isDrope() {
		return drope;
	}

	public void setDrope(boolean drope) {
		this.drope = drope;
	}

	public boolean isTryEventActiveVaso() {
		return tryEventActiveVaso;
	}

	public void setTryEventActiveVaso(boolean tryEventActiveVaso) {
		this.tryEventActiveVaso = tryEventActiveVaso;
	}

	public boolean isEventActiveVaso() {
		return eventActiveVaso;
	}

	public void setEventActiveVaso(boolean eventActiveVaso) {
		this.eventActiveVaso = eventActiveVaso;
	}

	public String getIdDrop() {
		return idDrop;
	}

	public void setIdDrop(String idDrop) {
		this.idDrop = idDrop;
	}

	public String getShortNameDrop() {
		return shortNameDrop;
	}

	public void setShortNameDrop(String shortNameDrop) {
		this.shortNameDrop = shortNameDrop;
	}

	public BufferedImage getImageDrop() {
		return imageDrop;
	}

	public void setImageDrop(BufferedImage imageDrop) {
		this.imageDrop = imageDrop;
	}

}
