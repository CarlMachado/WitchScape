package com.bakerystudios.teleports;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.game.Game;

public class Teleport extends Entity implements Updateble {

	private int x;
	private int y;
	private Tile destiny;
	
	public Teleport(int x, int y, int width, int height, Tile destiny) {
		super(x, y, width, height, null);
		this.x = x;
		this.y = y;
		this.destiny = destiny;
	}

	@Override
	public void update() {
		if(Game.player.getX() == x && Game.player.getY() == y) {
			Game.player.setX(destiny.getX());
			Game.player.setY(destiny.getY());
		}
	}

	public Tile getDestiny() {
		return destiny;
	}

	public void setDestiny(Tile destiny) {
		this.destiny = destiny;
	}
	
}
