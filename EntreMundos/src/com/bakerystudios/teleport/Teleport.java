package com.bakerystudios.teleport;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.engine.graphics.engine.World;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.game.Game;

public class Teleport extends Entity implements Updateble {

	private int destinyMap;
	private int sourceMap;
	private int dir;
	private int x;
	private int y;
	private int xDestiny;
	private int yDestiny;
	
	public Teleport(int x, int y, int width, int height, 
			int xDestiny, int yDestiny, int sourceMap, int destinyMap, int dir) {
		super(x, y, width, height, null);
		this.destinyMap = destinyMap;
		this.sourceMap = sourceMap;
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.xDestiny = xDestiny;
		this.yDestiny = yDestiny;
	}

	@Override
	public void update() {
		if(Game.player.getX() == x && Game.player.getY() == y
				&& Game.CUR_MAP == sourceMap) {
			Game.CUR_MAP = destinyMap;
			Game.player.setX(xDestiny);
			Game.player.setY(yDestiny);
			Game.player.setDir(dir);
		}
	}

}
