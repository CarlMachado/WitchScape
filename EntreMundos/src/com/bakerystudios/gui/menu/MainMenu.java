package com.bakerystudios.gui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;

public class MainMenu implements Updateble, Renderable {
	
	//private static boolean option1;
	//private static boolean option2;
	//private static boolean option3;
	//private static boolean click;
	public static boolean enter;
	private BufferedImage background;
	
	public MainMenu() {
		try {
			background = ImageIO.read(getClass().getResource("/sprites/tittle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if(enter) {
			GameState.state = GameState.PLAYING;
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
		//int y = (int) (Screen.SCALE_WIDTH * 0.11);
		int y = 100;
		int width = 400;
		int height = 200;
		
		g.setColor(Color.BLACK);
		g.setFont(Game.menuFont);
		g.drawImage(background, 0, 0, null);
		drawCentralizedString(g, "Aperte ENTER para jogar", 600);
		
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);
		

		// GAME TITLE ----------------------------------------------------------
		//drawCentralizedString(g, "BKS GAME ENGINE", y);
		//y += g.getFontMetrics().getHeight() * 1.7;
		
		// MENU OPTIONS ----------------------------------------------------------
		//g.setColor(Color.ORANGE);
		//g.setFont(Game.menuFont);
		//System.out.println("Fonte: "+(int) (Screen.SCALE_WIDTH * 0.03));
		
		//String name = "";
		//int fontHeight = g.getFontMetrics().getHeight();
		//int fontWidth = g.getFontMetrics().stringWidth(name);
		
//		if(option1) {
//			g.setColor(Color.GRAY);
//			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
//		}
//		g.setColor(Color.WHITE);
//		drawCentralizedString(g, name, y);
//		y += fontHeight * 1.5;
//		if(option1) {
//			g.setColor(Color.GRAY);
//			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
//		}
//		g.setColor(Color.WHITE);
//		drawCentralizedString(g, name, y);
//		y += fontHeight * 1.5;
//		if(option1) {
//			g.setColor(Color.GRAY);
//			fillCentralizedRect(g, y - (int) (fontHeight * 0.85), (int) (fontWidth * 1.1), (int) (fontHeight * 1.1));
//		}
//		g.setColor(Color.WHITE);
//		drawCentralizedString(g, name, y);
//		y += fontHeight * 1.5;
			
		//fillCentralizedRect(g, 1, 100, 30);
		//drawCentralizedString(g, "EXEMPLO", 1);
	}

}
