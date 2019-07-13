package com.bakerystudios.gui.menu;

import com.bakerystudios.game.GameState;
import com.bakerystudios.gui.menu.engine.Menu;
import com.bakerystudios.gui.menu.engine.MenuOption;

public class PauseMenu extends Menu {

	public PauseMenu(boolean background) {
		super(background);
		option.addOption(new MenuOption("CONTINUAR", 0));
		option.addOption(new MenuOption("SALVAR", 1));
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
			// TODO: Salvar o jogo.
		} else if(option.getCurOption() == option.getOption(2)) {
			// EXIT
			// TODO: Criar um popup e perguntar:
			// VOLTAR AO MENU INICIAL OU SAIR PARA A ÁREA DE TRABALHO?
			// MENU INICIAL : ÁREA DE TRABALHO
		}
	}

}
