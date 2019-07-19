package com.bakerystudios.teleports;

import com.bakerystudios.engine.Updateble;

public class TeleportManager implements Updateble {
	
	private final int HOUSE_SECFLOOR = 0;
	private final int HOUSE_DUNGEON  = 1;
	private final int SECFLOOR_HOUSE = 2;
	private final int DUNGEON_HOUSE  = 3;
	

	Teleport[] teleport;
	
	public TeleportManager() {
		teleport = new Teleport[4];
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	
}
