package com.bakerystudios.gui;

import java.awt.Color;
import java.awt.Font;
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
	
	public static void showPopUp(Graphics g, Font font, int y, String t1, String t2) {
		if(t1 == null) return;
		g.setFont(font);
		
		int fontHeight = g.getFontMetrics().getHeight();
		int fontWidth1 = g.getFontMetrics().stringWidth(t1);
		int fontWidth2 = t2 == null ? 0 : g.getFontMetrics().stringWidth(t2);
		
		int width = t2 == null ? 
				fontWidth1 + 20 : fontWidth1 > fontWidth2 ? 
						fontWidth1 + 20 : fontWidth2 + 20;
		int height = (int) (t2 == null ?
				fontHeight + 20 : fontHeight * 2 + 20);
		
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);

		g.setColor(Color.BLACK);
		if(t1 != null) drawCentralizedString(g, t1, y + 32);
		if(t2 != null) drawCentralizedString(g, t2, y + 62);
	}
	
	public static void showDialog(Graphics g, Font font, String t1, String t2, String t3, boolean esc , boolean enter) {
		g.setFont(font);
		
		int y = 500;
		int width = 700;
		int height = 200;
		
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);
		
		g.setColor(Color.BLACK);
		if(t1 != null) drawCentralizedString(g, t1, 580);
		if(t2 != null) drawCentralizedString(g, t2, 610);
		if(t3 != null) drawCentralizedString(g, t3, 640);

		g.setColor(Color.BLACK);
		if(enter) g.drawString("ENTER", 900, y + 190);
		if(esc) g.drawString("ESC", 300, y + 190);
	}
	
}
