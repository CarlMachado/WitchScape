package com.bakerystudios.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.fps.FramesPerSecond;
import com.bakerystudios.gui.menu.MainMenu;
import com.bakerystudios.gui.menu.PauseMenu;
import com.bakerystudios.gui.menu.engine.Menu;
import com.bakerystudios.gui.menu.engine.MenuState;

public class UserInterface implements Renderable, Updateble {

	private FramesPerSecond fps;
	private Menu mainMenu;
	private Menu pauseMenu;
	
	public UserInterface() {
		fps = new FramesPerSecond();
		mainMenu = new MainMenu(false);
		pauseMenu = new PauseMenu(true);
	}
	
	@Override
	public void update() {
		fps.update();
		
		if(GameState.state == GameState.MENU) {
			if(MenuState.state == MenuState.MAIN) {
				mainMenu.update();
			}
			if(MenuState.state == MenuState.PAUSE) {
				pauseMenu.update();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		fps.render(g);
		
		if(Game.uiDoor) {
			for (int i = 0; i < Game.entities.size(); i++) {
				Entity atual = Game.entities.get(i);
				if (atual instanceof Door) {
					g.setColor(Color.white);
					g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));				
					if(!((Door) atual).getOpenDoor()) 
						drawCentralizedString(g, "Aperte ENTER para abrir a porta", Screen.HEIGHT + 500);
					else 
						drawCentralizedString(g, "Aperte ENTER para fechar a porta", Screen.HEIGHT + 500);
					g.dispose();
				}
			}
		}
		
		if(GameState.state == GameState.MENU) {
			if(MenuState.state == MenuState.MAIN) {
				mainMenu.render(g);
			}
			if(MenuState.state == MenuState.PAUSE) {
				pauseMenu.render(g);
			}
		}
	}
	
	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
}
