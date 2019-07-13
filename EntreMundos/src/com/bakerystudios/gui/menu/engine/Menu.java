package com.bakerystudios.gui.menu.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.bakerystudios.engine.OptionManager;
import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.menu.MainMenu;

public abstract class Menu implements Renderable, Updateble {

	protected OptionManager option = new OptionManager(6);
	
	protected static boolean up, down, enter;
	
	protected boolean background = false;
	
	public Menu(boolean background) {
		this.background = background;
	}
	
	public abstract void executeOption();

	@Override
	public void update() {
		if(up) {
			up = false;
			option.previousOption();
		}
		if(down) {
			down = false;
			option.nextOption();
		}
		if(enter) {
			enter = false;
			executeOption();
			option.firstOption();
		}
	}
	
	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
	protected void fillCentralizedRect(Graphics g, int y, int width, int height) {
		g.fillRect(Screen.SCALE_WIDTH / 2 - width / 2, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		if(background) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.setColor(new Color(0, 0, 0, 100));
			g2D.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);
		}

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
		
		for(int i = 0; i < option.optionAmount(); i++) {
			String name = option.getOption(i).getName();
			int fontHeight = g.getFontMetrics().getHeight();
			
			if(option.getCurOption() == option.getOption(i)) {
				int fontWidth = g.getFontMetrics().stringWidth(name);
				g.setColor(Color.GRAY);
				fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
			}
			g.setColor(Color.WHITE);
			drawCentralizedString(g, name, y);
			y += fontHeight * 1.5;
		}
		//fillCentralizedRect(g, 1, 100, 30);
		//drawCentralizedString(g, "EXEMPLO", 1);
	}

	public static boolean isUp() {
		return up;
	}

	public static void setUp(boolean up) {
		MainMenu.up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public static void setDown(boolean down) {
		MainMenu.down = down;
	}

	public static boolean isEnter() {
		return enter;
	}

	public static void setEnter(boolean enter) {
		MainMenu.enter = enter;
	}
	
}
