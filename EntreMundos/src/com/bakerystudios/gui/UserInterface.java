package com.bakerystudios.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.entities.Anotacao;
import com.bakerystudios.entities.Boy;
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Diario;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Esqueleto;
import com.bakerystudios.entities.Livro;
import com.bakerystudios.entities.Placa;
import com.bakerystudios.entities.Player;
import com.bakerystudios.entities.Princesa;
import com.bakerystudios.entities.Witch;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.fps.FramesPerSecond;
import com.bakerystudios.gui.menu.MainMenu;
import com.bakerystudios.gui.menu.MenuState;
import com.bakerystudios.gui.menu.PauseMenu;
import com.bakerystudios.inventario.Warehouse;

public class UserInterface implements Renderable, Updateble {

	//private FramesPerSecond fps;
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;

	public UserInterface() {
		//fps = new FramesPerSecond();
		mainMenu = new MainMenu();
		pauseMenu = new PauseMenu();
	}

	@Override
	public void update() {
		//fps.update();

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
		//fps.render(g);

		if (GameState.state == GameState.MENU) {
			if (MenuState.state == MenuState.MAIN) {
				mainMenu.render(g);
			}
			if (MenuState.state == MenuState.PAUSE) {
				pauseMenu.render(g);
			}
		}

		if (Game.uiDoor || Game.uiChest || Game.uiNpc || Game.uiPlaca || Game.uiLivro || Game.diario.eventActiveLivro || Boy.event || Witch.event) {
			if(Boy.event) {
				Boy.anotacaoDialogue.eventoAnotacao(g);
				return;
			}
			
			if(Witch.event) {
				Witch.anotacaoDialogue.eventoAnotacao(g);
				return;
			}
			
			if(Game.diario.eventActiveLivro) {
				Game.diario.anotacaoDialogue.eventoAnotacao(g);
				return;
			}
			
			for (int j = 0; j < Game.entities.size(); j++) {
				Entity atual = Game.entities.get(j);
				if (atual instanceof Door && Game.uiDoor) {
					// g.setColor(Color.white);
					// g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
					if (!((Door) atual).getOpenDoor() && ((Door) atual).isChoose()) { // PORTA ABERTA
						if (((Door) atual).isChave()) {
							TextBox.showPopUp(g, Game.boxFont, 470, "Aperte ENTER para abrir a porta.",
									"Você irá precisar de uma chave.");
							return;
						} else {
							TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para abrir a porta.", null);
							return;
						}
					} else if (((Door) atual).getOpenDoor() && ((Door) atual).isChoose()) { // PORTA FECHADA
						TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para fechar a porta.", null);
						return;
					}
				}
				if (atual instanceof Chest && Game.uiChest && !((Chest) atual).isOpenChest()
						&& ((Chest) atual).isTryAnimation()) {
					// g.setColor(Color.white);
					// g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
					TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para abrir o baú.", null);
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
							if (((Chest) atual).getSlot(i).getIdentity() != "") {
								g.drawImage(chest.getSlot(i).getImageSlot(),
										chest.getinitialPosition() + i * chest.getwidthSlot() + 1,
										chest.getHeightPosition() - 8, chest.getwidthSlot(), chest.getwidthSlot(),
										null);
							}
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
							if (((Chest) atual).getSlot(chest.getselectedItem()).getIdentity() != "") {
								g.drawImage(chest.getSlot(chest.getselectedItem()).getImageSlot(),
										chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot() + 1,
										chest.getHeightPosition() - 8, chest.getwidthSlot(), chest.getwidthSlot(),
										null);
							}
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
							if (((Chest) atual).getSlot(chest.getselectedItem()).getIdentity() != "") {
								g.drawImage(chest.getSlot(chest.getselectedItem()).getImageSlot(),
										chest.getinitialPosition() + chest.getselectedItem() * chest.getwidthSlot() + 1,
										chest.getHeightPosition() - 8, chest.getwidthSlot(), chest.getwidthSlot(),
										null);
							}
						}
					}
				} else if ((Player.typeIsNpc(atual)) && (Game.uiNpc)) {
					if (atual instanceof Princesa) {
						if (((Princesa) atual).isEventActivePrincesa() && ((Princesa) atual).isChoose()) {
							{
								((Princesa) atual).getAnotacao().eventoAnotacao(g);
							}
						} else if (!((Princesa) atual).isEventActivePrincesa() && ((Princesa) atual).isChoose()) {
							g.setColor(Color.white);
							g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
							TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para conversar com o NPC.", null);
						}
					}
					if (atual instanceof Esqueleto) {
						if (((Esqueleto) atual).isEventActiveEsqueleto() && ((Esqueleto) atual).isChoose()) {
							((Esqueleto) atual).getAnotacao().eventoAnotacao(g);
							return;
						} else if (!((Esqueleto) atual).isEventActiveEsqueleto() && ((Esqueleto) atual).isChoose()) {
							g.setColor(Color.white);
							g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
							TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para conversar com o NPC.", null);
						}
					}
				} else if (atual instanceof Placa && Game.uiPlaca) {
					if (((Placa) atual).isEventActivePlaca() && ((Placa) atual).isChoose()) {						
						((Placa) atual).getAnotacao().eventoAnotacao(g);
						return;
					}
					if (!((Placa) atual).isEventActivePlaca() && ((Placa) atual).isChoose()) {
						g.setColor(Color.white);
						g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.030)));
						TextBox.showPopUp(g, Game.boxFont, 510, "Aperte ENTER para ler a placa.", null);
					}
				} else if (atual instanceof Livro && Game.uiLivro) {
					
					if (((Livro) atual).isEventActiveLivro() && ((Livro) atual).isChoose()) {
						//System.out.println("teste1");
						((Livro) atual).getAnotacaoDialogue().eventoAnotacao(g);
						return;
					}
				}
			}
		}
	}

	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}

}
