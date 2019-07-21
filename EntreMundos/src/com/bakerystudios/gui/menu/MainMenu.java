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
import com.bakerystudios.gui.TextBox;

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
		
		
		TextBox.showPopUp(g, Game.menuFont, 550, "Aperte ENTER para jogar", null);
		
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);
		

		
	}

}
