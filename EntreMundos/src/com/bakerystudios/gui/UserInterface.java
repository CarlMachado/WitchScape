package com.bakerystudios.gui;

import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.GameState;
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
		
		if(GameState.state == GameState.MENU) {
			if(MenuState.state == MenuState.MAIN) {
				mainMenu.render(g);
			}
			if(MenuState.state == MenuState.PAUSE) {
				pauseMenu.render(g);
			}
		}
	}
	
}
