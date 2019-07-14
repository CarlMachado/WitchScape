package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;

public class Porta extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean animation = false;

	private int currentAnimacao = 0;
	private int maxAnimacao = sprites.length;
	private int currentFrame = 0;
	private int maxFrame = 30;

	public Porta(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void update() {
		if (animation) {
			currentFrame++;
			if (currentFrame >= maxFrame) {
				currentAnimacao++;
			}
			if (currentAnimacao >= maxAnimacao) {
				currentAnimacao = 0;
				animation = false;
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

}
