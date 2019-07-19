package com.bakerystudios.teleport;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.engine.graphics.engine.World;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.game.Game;

public class Teleport extends Entity implements Updateble {

	private int x;
	private int y;
	private Tile destiny;
	private String mapPath;
	private String colPath;

	public Teleport(int x, int y, int width, int height, Tile destiny) {
		super(x, y, width, height, null);
		this.x = x;
		this.y = y;
		this.destiny = destiny;
	}
	
	public Teleport(int x, int y, int width, int height, String  mapPath, String colPath) {
		super(x, y, width, height, null);
		this.x = x;
		this.y = y;
		this.mapPath = mapPath;
		this.colPath = colPath;
	}

	@Override
	public void update() {
		if(Game.player.getX() == x && Game.player.getY() == y) {
			if(destiny != null) {
				Game.player.setX(destiny.getX());
				Game.player.setY(destiny.getY());
			}
			if(mapPath != null) {
				Game.world = new World(mapPath, colPath);
			}
		}
	}

	public Tile getDestiny() {
		return destiny;
	}

	public void setDestiny(Tile destiny) {
		this.destiny = destiny;
	}

}
