package com.bakerystudios.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;

public class Tile {
	
	public static int tileSize = 16;
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0,0,tileSize,tileSize);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16,0,tileSize,tileSize);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
