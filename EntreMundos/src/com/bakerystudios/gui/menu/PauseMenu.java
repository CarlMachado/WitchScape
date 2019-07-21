package com.bakerystudios.gui.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;

public class PauseMenu implements Updateble, Renderable {
	
	public static boolean option1;
	public static boolean option2;
	public static boolean click;

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
		int widthMenu = 400;
		int heightMenu = 200;
		
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);

		g.setFont(Game.boxFont);
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, widthMenu, heightMenu);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, widthMenu - 10, heightMenu - 10);
		
		g.setFont(Game.boxFont);
		g.setColor(Color.BLACK);
		drawCentralizedString(g, "CRIADO POR:", 320);
		drawCentralizedString(g, "CARLOS E HERIKC", 360);
		
		if(option1) {
			int x = 465, y = 395, width = 160, height = 50;
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, y, width, height, 10, 10);
			g.setColor(Color.GREEN);
			g.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Continuar", x + 15, y + 32);
		} else {
			int x = 470, y = 400, width = 150, height = 40;
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, y, width, height, 10, 10);
			g.setColor(Color.GREEN);
			g.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Continuar", x + 10, y + 27);
		}
		
		if(option2) {
			int x = 655, y = 395, width = 160, height = 50;
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, y, width, height, 10, 10);
			g.setColor(Color.RED);
			g.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Sair", x + 54, y + 32);
		} else {
			int x = 660, y = 400, width = 150, height = 40;
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, y, width, height, 10, 10);
			g.setColor(Color.RED);
			g.fillRoundRect(x + 5, y + 5, width - 10, height - 10, 10, 10);
			g.setColor(Color.WHITE);
			g.drawString("Sair", x + 49, y + 27);
		}
	}
	
}
