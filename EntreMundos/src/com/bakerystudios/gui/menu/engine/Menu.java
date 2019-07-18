package com.bakerystudios.gui.menu.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bakerystudios.engine.OptionManager;
import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public abstract class Menu implements Renderable, Updateble {

	protected OptionManager option = new OptionManager(6);
	public static boolean up;
	public static boolean down;
	public static boolean enter;
	private BufferedImage background;
	private boolean rgbaBk = false;
	
	public Menu(boolean rgbaBk) {
		this.rgbaBk = rgbaBk;
		try {
			background = ImageIO.read(getClass().getResource("/sprites/tittle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if(rgbaBk) {
			Graphics2D g2D = (Graphics2D) g;
			g2D.setColor(new Color(0, 0, 0, 100));
			g2D.fillRect(0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT);
		}

		int y = (int) (Screen.SCALE_WIDTH * 0.11);
		
		g.setColor(Color.WHITE);
		g.setFont(Game.menuFont);
		
		g.drawImage(background, 0, 0, null);
		
		// GAME TITLE ----------------------------------------------------------
		drawCentralizedString(g, "BKS GAME ENGINE", y);
		y += g.getFontMetrics().getHeight() * 1.7;
		
		// MENU OPTIONS ----------------------------------------------------------
		g.setColor(Color.WHITE);
		g.setFont(Game.menuFont);
		
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
	
}
