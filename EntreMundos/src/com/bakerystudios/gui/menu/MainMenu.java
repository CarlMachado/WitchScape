package com.bakerystudios.gui.menu;

import com.bakerystudios.game.GameState;
import com.bakerystudios.gui.menu.engine.Menu;
import com.bakerystudios.gui.menu.engine.MenuOption;

public class MainMenu extends Menu {

	public MainMenu(boolean background) {
		super(background);
		option.addOption(new MenuOption("NOVO JOGO", 0));
		option.addOption(new MenuOption("CARREGAR", 1));
		option.addOption(new MenuOption("SAIR", 2));
		option.firstOption();
	}

	@Override
	public void executeOption() {
		if(option.getCurOption() == option.getOption(0)) {
			// NEW GAME
			GameState.state = GameState.PLAYING;
		} else if(option.getCurOption() == option.getOption(1)) {
			// LOAD
			// TODO: Carregar o jogo.
			GameState.state = GameState.PLAYING;
		} else if(option.getCurOption() == option.getOption(2)) {
			// EXIT
			System.exit(0);
		}
	}

}
