package com.bakerystudios.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Esqueleto;
import com.bakerystudios.entities.Placa;
import com.bakerystudios.entities.Player;
import com.bakerystudios.entities.Princesa;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.fps.FramesPerSecond;
import com.bakerystudios.gui.menu.MainMenu;
import com.bakerystudios.gui.menu.MenuState;
import com.bakerystudios.gui.menu.PauseMenu;
import com.bakerystudios.inventario.Warehouse;

public class UserInterface implements Renderable, Updateble {

	private FramesPerSecond fps;
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;

	public UserInterface() {
		fps = new FramesPerSecond();
		mainMenu = new MainMenu();
		pauseMenu = new PauseMenu();
	}

	@Override
	public void update() {
		fps.update();

		if (GameState.state == GameState.MENU) {
			if (MenuState.state == MenuState.MAIN) {
				mainMenu.update();
			}
			if (MenuState.state == MenuState.PAUSE) {
				pauseMenu.update();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		fps.render(g);

		if (GameState.state == GameState.MENU) {
			if (MenuState.state == MenuState.MAIN) {
				mainMenu.render(g);
			}
			if (MenuState.state == MenuState.PAUSE) {
				pauseMenu.render(g);
			}
		}

		if (Game.uiDoor || Game.uiChest || Game.uiNpc || Game.uiPlaca) {
			for (int j = 0; j < Game.entities.size(); j++) {
				Entity atual = Game.entities.get(j);
				if (atual instanceof Door && Game.uiDoor) {
					g.setColor(Color.white);
					g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
					if (!((Door) atual).getOpenDoor() && ((Door) atual).isChoose()) { // PORTA ABERTA
						if (((Door) atual).isChave()) {
							//drawCentralizedString(g, "Aperte ENTER para abrir a porta", Screen.HEIGHT + 500);
							//drawCentralizedString(g, "Você irá precisar de uma chave", Screen.HEIGHT + 540);
							if(((Door) atual).pressedEnter()) {
								TextBox.show(g, "Está trancada.", null, null);
							}
							return;
						} else {
							//drawCentralizedString(g, "Aperte ENTER para abrir a porta", Screen.HEIGHT + 500);
							return;
						}
					} else if (((Door) atual).getOpenDoor() && ((Door) atual).isChoose()) { // PORTA FECHADA
						//drawCentralizedString(g, "Aperte ENTER para fechar a porta", Screen.HEIGHT + 500);
						return;
					}
				}
				if (atual instanceof Chest && Game.uiChest && !((Chest) atual).isOpenChest()
						&& ((Chest) atual).isTryAnimation()) {
					g.setColor(Color.white);
					g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
					drawCentralizedString(g, "Aperte ENTER para abrir o bau", Screen.HEIGHT + 500);
				}
				if (atual instanceof Chest) {
					Chest chest = (Chest) atual;
					if (chest.isOpenChest()) {
						for (int i = 0; i < chest.getNumSlots(); i++) {
							g.setColor(Color.RED);
							g.drawRect(chest.getinitialPosition() + i * chest.getwidthSlot(), chest.getHeightPosition(),
									chest.getwidthSlot(), chest.getwidthSlot());
							g.setColor(Color.GRAY);
							g.fillRect(chest.getinitialPosition() + i * chest.getwidthSlot() + 1,
									chest.getHeightPosition() + 1, chest.getwidthSlot() - 1, chest.getwidthSlot() - 1);

							g.setColor(Color.WHITE);
							g.setFont(new Font("Arial", Font.BOLD, 18));
							g.drawString(Integer.toString(chest.getSlot(i).getAmount()),
									chest.getinitialPosition() + i * chest.getwidthSlot() + chest.getwidthSlot() - 12,
									chest.getHeightPosition() + chest.getwidthSlot() - 4);
						}
						// ITEM SELECIONADO
						if (chest.isFocus()) {
							g.setColor(Color.BLUE);
							g.drawRect(chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot(),
									chest.getHeightPosition(), chest.getwidthSlot(), chest.getwidthSlot());
							g.setColor(Color.GRAY);
							g.fillRect(chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot() + 1,
									chest.getHeightPosition() + 1, chest.getwidthSlot() - 1, chest.getwidthSlot() - 1);
							g.setColor(Color.WHITE);
							g.drawString(Integer.toString(chest.getSlot(chest.getselectedItem()).getAmount()),
									chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot()
											+ chest.getwidthSlot() - 12,
									chest.getHeightPosition() + chest.getwidthSlot() - 4);
						}
						if (Warehouse.exchangeChest) {
							g.setColor(Color.GREEN);
							g.drawRect(chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot(),
									chest.getHeightPosition(), chest.getwidthSlot(), chest.getwidthSlot());
							g.setColor(Color.GRAY);
							g.fillRect(chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot() + 1,
									chest.getHeightPosition() + 1, chest.getwidthSlot() - 1, chest.getwidthSlot() - 1);
							g.setColor(Color.WHITE);
							g.drawString(Integer.toString(chest.getSlot(chest.getselectedItem()).getAmount()),
									chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot()
											+ chest.getwidthSlot() - 12,
									chest.getHeightPosition() + chest.getwidthSlot() - 4);
						}
					}
				} else if ((Player.typeIsNpc(atual)) && (Game.uiNpc)) {							
					if(atual instanceof Princesa) {						
						if(((Princesa) atual).isEventActivePrincesa() && ((Princesa) atual).isChoose()) {
							((Princesa) atual).getAnotacao().eventoAnotacao(g);
						} else if(!((Princesa) atual).isEventActivePrincesa() && ((Princesa) atual).isChoose()) {
							g.setColor(Color.white);
							g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
							drawCentralizedString(g, "Aperte ENTER para interagir com o NPC", Screen.HEIGHT + 500);	
						}
					}
					if(atual instanceof Esqueleto) {
						if(((Esqueleto) atual).isEventActiveEsqueleto() && ((Esqueleto) atual).isChoose()) {
							((Esqueleto) atual).getAnotacao().eventoAnotacao(g);
							return;
						} else if(!((Esqueleto) atual).isEventActiveEsqueleto() && ((Esqueleto) atual).isChoose()) {
							g.setColor(Color.white);
							g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
							drawCentralizedString(g, "Aperte ENTER para interagir com o NPC", Screen.HEIGHT + 500);	
						}
					} 
				}else if(atual instanceof Placa && Game.uiPlaca) {
					if(((Placa) atual).isEventActivePlaca() && ((Placa) atual).isChoose()) {
						((Placa) atual).getAnotacao().eventoAnotacao(g);
						return;
					}
					if(!((Placa) atual).isEventActivePlaca() && ((Placa) atual).isChoose()) {
						g.setColor(Color.white);
						g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
						drawCentralizedString(g, "Aperte ENTER para interagir com a placa", Screen.HEIGHT + 500);
					}
				}
			}
		}
	}

	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}

}
