package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;

public class Witch extends Entity implements Updateble, Renderable {

	private boolean isHunting = true;
	private boolean seeingPlayer = false;
	
	private boolean moved = true;
	private int waitFrames = 0, maxWaitFrames = 60;
	private double speed = 0.5;
	public static final int RIGHT_DIR = 0, LEFT_DIR = 1, UP_DIR = 2, DOWN_DIR = 3;
	private int dir = LEFT_DIR;
	protected static boolean right, left = true, up, down;
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 2;

	private BufferedImage[] rightSprite;
	private BufferedImage[] leftSprite;
	private BufferedImage[] downSprite;
	private BufferedImage[] upSprite;

	public Witch(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightSprite = new BufferedImage[3];
		leftSprite = new BufferedImage[3];
		downSprite = new BufferedImage[3];
		upSprite = new BufferedImage[3];
		
		for (int i = 0; i < 3; i++) {
			downSprite[i] = Game.characters.getSprite(i * 16, 128, 16, 16);
			leftSprite[i] = Game.characters.getSprite(i * 16, 144, 16, 16);
			rightSprite[i] = Game.characters.getSprite(i * 16, 160, 16, 16);
			upSprite[i] = Game.characters.getSprite(i * 16, 176, 16, 16);
		}
	}
	
	private void hunting() {
		animation();
		walk();
		if(x == 160) {
			moved = false;
			left = false;
			waitToWalk(RIGHT_DIR);
		}
		if(x == 272) {
			moved = false;
			right = false;
			waitToWalk(LEFT_DIR);
		}
		hunt();
	}
	
	private void walk() {
		if(right) {
			x += speed;
		} else if(left) {
			x -= speed;
		}
	}

	private void animation() {
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		} else {
			index = 1;
		}
	}
	
	private void waitToWalk(int dir) {
		waitFrames++;
		if (waitFrames == maxWaitFrames) {
			waitFrames = 0;
			this.dir = dir;
			moved = true;
			if(dir == RIGHT_DIR) right = true;
			if(dir == LEFT_DIR) left = true;
		}
	}
	
	
	
	private void hunt() {
		//visao andando para a direita
		if(x <= 256  && x >= 192 && right &&
				Game.player.getX() <= 256 && Game.player.getX() >= 208 &&
				Game.player.getY() <= 336 && Game.player.getY() >= 209) {
			seeingPlayer = true;
			System.out.println("viu");
		}
	}

	@Override
	public void update() {
		if(isHunting) {
			hunting();
		}

	}

	@Override
	public void render(Graphics g) {
		if (dir == RIGHT_DIR) {
			g.drawImage(rightSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == LEFT_DIR) {
			g.drawImage(leftSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == UP_DIR) {
			g.drawImage(upSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == DOWN_DIR) {
			g.drawImage(downSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		}
	}
	
}
