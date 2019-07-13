package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.Tile;
import com.bakerystudios.engine.world.World;
import com.bakerystudios.game.screen.Screen;

public class Player extends Entity implements Renderable {

	protected static boolean right, left, up, down;

	private int speed = 2;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void update() {
		if (right && World.isFree((int) (x + speed), this.getY())) {
			x += speed;
		} else if (left && World.isFree((int) (x - speed), this.getY())) {
			x -= speed;
		}

		if (up && World.isFree(this.getX(), (int) (y - speed))) {
			y -= speed;
		} else if (down && World.isFree(this.getX(), (int) (y + speed))) {
			y += speed;
		}
		updateCamera();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x - Camera.x, (int) y - Camera.y, Tile.tileSize, Tile.tileSize);
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Screen.WIDTH/2),0,World.WIDTH*Tile.tileSize - Screen.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Screen.HEIGHT/2),0,World.HEIGHT*Tile.tileSize - Screen.HEIGHT);
	}

	public boolean isRight() {
		return right;
	}
	
	public static void setUp(boolean up) {
		Player.up = up;
	}
	
	public static void setDown(boolean down) {
		Player.down = down;
	}
	
	public static void setLeft(boolean left) {
		Player.left = left;
	}
	
	public static void setRight(boolean right) {
		Player.right = right;
	}

}
