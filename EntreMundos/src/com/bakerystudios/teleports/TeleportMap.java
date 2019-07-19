package com.bakerystudios.teleports;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;

public class TeleportMap implements Updateble {

	private int x;
	private int y;
	private int xDestiny;
	private int yDestiny;
	
	public TeleportMap(int x, int y, int xDestiny, int yDestiny) {
		this.x = x;
		this.y = y;
		this.xDestiny = xDestiny;
		this.yDestiny = yDestiny;
	}

	@Override
	public void update() {
		if(Game.player.getX() == x && Game.player.getY() == y) {
			Game.player.setX(xDestiny);
			Game.player.setY(yDestiny);
		}
	}

}
