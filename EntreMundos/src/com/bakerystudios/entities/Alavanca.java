package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;

public class Alavanca extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean tryAnimation = false;
	private boolean animation = false;
	private boolean active = false;

	private String identify;
	private boolean chave;

	private int currentAnimacao = 1;
	private int maxAnimacao = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	public Alavanca(int x, int y, int width, int height, BufferedImage sprite, boolean chave, String identify,
			BufferedImage[] sprites) {
		super(x, y, width, height, sprite);
		this.chave = chave;
		this.identify = identify;
		this.sprites = sprites;
		this.maxAnimacao = this.sprites.length;
	}

	public void update() {
		if (animation) {
			tryAnimation = false;
			currentFrame++;
			if (currentFrame >= maxFrame) {
				currentFrame = 0;
				currentAnimacao--;
				if (currentAnimacao >= 0 && currentAnimacao < maxAnimacao)
					sprite = sprites[currentAnimacao];
			}
			if (currentAnimacao >= 0) {
				currentFrame = 0;
				animation = false;
				active = true;
				evento();
				currentAnimacao = 0;
				sprite = sprites[currentAnimacao];
			}
		}
	}
	
	public void evento() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Door) {
				if(((Door) atual).getIdEquipament() == "switch") {
					((Door) atual).setIdEquipament("");
					((Door) atual).setNeedEquipament(false);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean isTryAnimation() {
		return tryAnimation;
	}

	public void setTryAnimation(boolean tryAnimation) {
		this.tryAnimation = tryAnimation;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public int getCurrentAnimacao() {
		return currentAnimacao;
	}

	public void setCurrentAnimacao(int currentAnimacao) {
		this.currentAnimacao = currentAnimacao;
	}

	public int getMaxAnimacao() {
		return maxAnimacao;
	}

	public void setMaxAnimacao(int maxAnimacao) {
		this.maxAnimacao = maxAnimacao;
	}

	public boolean isChave() {
		return chave;
	}

	public void setChave(boolean chave) {
		this.chave = chave;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
