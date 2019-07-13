package com.bakerystudios.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.camera.Camera;

public class Tile {

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
