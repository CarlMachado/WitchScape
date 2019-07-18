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
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);
	}
	
}
