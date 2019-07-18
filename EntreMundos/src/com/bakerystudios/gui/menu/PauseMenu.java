package com.bakerystudios.gui.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public class PauseMenu implements Updateble, Renderable {

	protected static void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
	protected static void fillCentralizedRect(Graphics g, int width, int height) {
		g.fillRect(Screen.SCALE_WIDTH / 2 - width / 2, Screen.SCALE_HEIGHT / 2 - height / 2, width, height);
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render(Graphics g) {		
		int y = 220;
		int width = 700;
		int height = 400;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);

		g.setFont(Game.boxFont);
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, width - 10, height - 10);
	}
	
}
