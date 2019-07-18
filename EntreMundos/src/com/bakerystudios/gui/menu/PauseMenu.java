package com.bakerystudios.gui.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.screen.Screen;

public class PauseMenu implements Updateble, Renderable {

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(new Color(0, 0, 0, 100));
		g2D.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);
	}
	
}
