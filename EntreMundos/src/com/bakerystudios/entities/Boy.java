package com.bakerystudios.entities;

import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;

public class Boy extends Entity implements Updateble {

	public boolean enter;

	public Boy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	}

	@Override
	public void update() {
		if(x == Game.player.getX() && y + 16 == Game.player.getY() && Game.player.getDir() == Game.player.UP_DIR) {
			if(enter) {
				
			}
		}
	}
	
}
