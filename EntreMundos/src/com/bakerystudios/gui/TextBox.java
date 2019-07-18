package com.bakerystudios.gui;

import java.awt.Color;
import java.awt.Graphics;

import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public class TextBox {

	protected static void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
	protected static void fillCentralizedRect(Graphics g, int y, int width, int height) {
		g.fillRect(Screen.SCALE_WIDTH / 2 - width / 2, y, width, height);
	}
	
	public static void showPopUp(Graphics g, String t1, String t2) {
		int y = 470;
		
		g.setFont(Game.boxFont);
		
		if(t1 == null) return;
		
		int width = t2 == null ? 
				g.getFontMetrics().stringWidth(t1) + 20 : g.getFontMetrics().stringWidth(t1) > g.getFontMetrics().stringWidth(t2) ? 
							g.getFontMetrics().stringWidth(t1) + 20 : g.getFontMetrics().stringWidth(t2) + 20;
		int height = t2 == null ? 50 : 80;

		g.setFont(Game.boxFont);
		
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);

		g.setColor(Color.BLACK);
		if(t1 != null) drawCentralizedString(g, t1, y + 32);
		if(t2 != null) drawCentralizedString(g, t2, y + 62);
		
		g.setColor(Color.BLACK);
	}
	
	public static void showDialog(Graphics g, String t1, String t2, String t3) {
		int y = 500;
		int width = 700;
		int height = 200;
		
		g.setFont(Game.boxFont);
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);

		g.setColor(Color.BLACK);
		if(t1 != null) drawCentralizedString(g, t1, 550);
		if(t2 != null) drawCentralizedString(g, t2, 600);
		if(t3 != null) drawCentralizedString(g, t3, 650);

		g.setColor(Color.BLACK);
		g.drawString("ENTER", 900, y + 190);
		g.drawString("ESC", 300, y + 190);
	}
	
}
