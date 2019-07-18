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
	
	public static void show(Graphics g, String t1, String t2, String t3) {
		g.setFont(Game.boxFont);
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, 520, 700, 200);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, 525, 690, 190);
		if(t1 != null) drawCentralizedString(g, t1, 550);
		if(t2 != null) drawCentralizedString(g, t2, 600);
		if(t3 != null) drawCentralizedString(g, t3, 650);
		g.setColor(Color.BLACK);
		g.drawString("ENTER", 900, 710);
		g.drawString("ESC", 300, 710);
	}
	
}
