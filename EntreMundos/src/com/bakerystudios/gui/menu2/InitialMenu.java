package com.bakerystudios.gui.menu2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.screen.Screen;

public class InitialMenu implements Updateble, Renderable {
	
	private static boolean option1;
	//private static boolean option2;
	//private static boolean option3;
	//private static boolean click;
	
	public InitialMenu(boolean background) {
		
	}
	
	public void executeOption() {
		
	}

	@Override
	public void update() {

	}
	
	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
	protected void fillCentralizedRect(Graphics g, int y, int width, int height) {
		g.fillRect(Screen.SCALE_WIDTH / 2 - width / 2, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		int y = (int) (Screen.SCALE_WIDTH * 0.11);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.05)));
		

		// GAME TITLE ----------------------------------------------------------
		drawCentralizedString(g, "BKS GAME ENGINE", y);
		y += g.getFontMetrics().getHeight() * 1.7;
		
		// MENU OPTIONS ----------------------------------------------------------
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.03)));
		//System.out.println("Fonte: "+(int) (Screen.SCALE_WIDTH * 0.03));
		
		String name = "";
		int fontHeight = g.getFontMetrics().getHeight();
		int fontWidth = g.getFontMetrics().stringWidth(name);
		
		if(option1) {
			g.setColor(Color.GRAY);
			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
		}
		g.setColor(Color.WHITE);
		drawCentralizedString(g, name, y);
		y += fontHeight * 1.5;
		if(option1) {
			g.setColor(Color.GRAY);
			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
		}
		g.setColor(Color.WHITE);
		drawCentralizedString(g, name, y);
		y += fontHeight * 1.5;
		if(option1) {
			g.setColor(Color.GRAY);
			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
		}
		g.setColor(Color.WHITE);
		drawCentralizedString(g, name, y);
		y += fontHeight * 1.5;
			
		//fillCentralizedRect(g, 1, 100, 30);
		//drawCentralizedString(g, "EXEMPLO", 1);
	}

}
