package com.bakerystudios.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bakerystudios.entities.Alavanca;
import com.bakerystudios.entities.Boy;
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Esqueleto;
import com.bakerystudios.entities.Livro;
import com.bakerystudios.entities.Placa;
import com.bakerystudios.entities.Player;
import com.bakerystudios.entities.Poco;
import com.bakerystudios.entities.Princesa;
import com.bakerystudios.entities.Vaso;
import com.bakerystudios.entities.Witch;
import com.bakerystudios.events.eventBlock;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.inventario.Inventario;
import com.bakerystudios.inventario.Warehouse;

public class PlayerInput extends Input {

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameState.state == GameState.PLAYING) {
			if (!Game.gameEvent) {

				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					Player.setRight(true);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					Player.setLeft(true);
				}

				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					Player.setUp(true);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					Player.setDown(true);
				}

			}
			if (Inventario.status && Inventario.focus) {
				if (e.getKeyCode() == KeyEvent.VK_1 && (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventario.selectedItem = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_2
						&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventario.selectedItem = 1;
				} else if (e.getKeyCode() == KeyEvent.VK_3
						&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventario.selectedItem = 2;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (Inventario.slot[Inventario.selectedItem].getIdentity() == "Diario" && !Game.diario.eventActiveLivro
						&& !Player.tryActiveEvent) {
					Game.diario.eventActiveLivro = true;
					Game.diario.anotacaoDialogue.setStatus(true);
					Game.diario.read++;
					Player.inEvent = true;
				} else if (Inventario.slot[Inventario.selectedItem].getIdentity() == "Diario"
						&& Game.diario.eventActiveLivro && !Player.tryActiveEvent) {
					if (!Game.diario.getAnotacaoDialogue().isLestPage())
						Game.diario.getAnotacaoDialogue().setNextPaginaSelected(true);
					else
						Game.diario.getAnotacaoDialogue().setExit(true);
				}

				for (Entity atual : Game.entities) {
					if (atual instanceof Door) {
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) {
							if (((Door) atual).isChave() && !((Door) atual).isNeedEquipament()) {
								for (int i = 0; i < Inventario.slot.length; i++) {
									if (Inventario.slot[i].getIdentity() == ((Door) atual).getIdentify()) {
										((Door) atual).setAnimation(true);
										Game.uiDoor = false;
									}
								}
							} else if (!((Door) atual).isChave() && ((Door) atual).isNeedEquipament()) {
								for (int i = 0; i < Inventario.slot.length; i++) {
									if (Inventario.slot[i].getIdentity() == ((Door) atual).getIdEquipament()) {
										((Door) atual).setAnimation(true);
										Game.uiDoor = false;
									}
								}
							} else if (((Door) atual).isChave() && ((Door) atual).isNeedEquipament()) {
								boolean chave = false, equipament = false;
								for (int i = 0; i < Inventario.slot.length; i++) {
									if (Inventario.slot[i].getIdentity() == ((Door) atual).getIdEquipament())
										equipament = true;
									else if (Inventario.slot[i].getIdentity() == ((Door) atual).getIdentify())
										chave = true;
								}
								if (chave && equipament) {
									((Door) atual).setAnimation(true);
									Game.uiDoor = false;
								}
							} else {
								((Door) atual).setAnimation(true);
								Game.uiDoor = false;
							}
						}
					} else if (atual instanceof Chest) {
						if (((Chest) atual).isTryAnimation() && !((Chest) atual).isAnimation()
								&& !((Chest) atual).isOpenChest()) {
							((Chest) atual).setAnimation(true);
							Player.inEvent = true;
							Game.uiChest = false;
						}
					} else if (atual instanceof Princesa) {
						if (((Princesa) atual).getExistEventPrincesa() && ((Princesa) atual).isEventActivePrincesa()) {
							if (!((Princesa) atual).getAnotacao().isLestPage())
								((Princesa) atual).getAnotacao().setNextPaginaSelected(true);
							else {
								// ((Princesa) atual).setEventActivePrincesa(false);
								// ((Princesa) atual).setTryEventActivePrincesa(false);
								((Princesa) atual).getAnotacao().setExit(true);
								Player.inEvent = false;
							}
						}
						if (((Princesa) atual).isTryEventActivePrincesa()
								&& !((Princesa) atual).isEventActivePrincesa()) {
							// System.out.println("teste prin");
							Player.inEvent = true;
							((Princesa) atual).setEventActivePrincesa(true);
							((Princesa) atual).setTryEventActivePrincesa(false);
							Game.uiNpc = false;
						}
					} else if (atual instanceof Esqueleto) {
						if (((Esqueleto) atual).getExistEventEsqueleto()
								&& ((Esqueleto) atual).isEventActiveEsqueleto()) {
							if (!((Esqueleto) atual).getAnotacao().isLestPage())
								((Esqueleto) atual).getAnotacao().setNextPaginaSelected(true);
							else {
								// ((Esqueleto) atual).setEventActiveEsqueleto(false);
								// ((Esqueleto) atual).setTryEventActiveEsqueleto(false);
								((Esqueleto) atual).getAnotacao().setExit(true);
								Player.inEvent = false;
							}
						}
						if (((Esqueleto) atual).isTryEventActiveEsqueleto()
								&& !((Esqueleto) atual).isEventActiveEsqueleto()) {
							// System.out.println("teste esq");
							Player.inEvent = true;
							((Esqueleto) atual).setEventActiveEsqueleto(true);
							((Esqueleto) atual).setTryEventActiveEsqueleto(false);
							Game.uiNpc = false;
						}
					} else if (atual instanceof Placa) {
						if (((Placa) atual).isTryEventActivePlaca() && !((Placa) atual).isEventActivePlaca()) {
							Player.inEvent = true;
							((Placa) atual).setEventActivePlaca(true);
							((Placa) atual).setTryEventActivePlaca(false);

							Game.uiPlaca = true;
							((Placa) atual).getAnotacao().setStatus(true);
							((Placa) atual).setChoose(true);
						} else if (((Placa) atual).isEventActivePlaca()) {
							if (!((Placa) atual).getAnotacao().isLestPage())
								((Placa) atual).getAnotacao().setNextPaginaSelected(true);
							else {
								// ((Placa) atual).setEventActivePlaca(false);
								((Placa) atual).getAnotacao().setExit(true);
								// Player.inEvent = false;
							}
						}
					} else if (atual instanceof Vaso) {
						if (((Vaso) atual).isTryEventActiveVaso() && !((Vaso) atual).isEventActiveVaso()) {
							((Vaso) atual).setEventActiveVaso(true);
							((Vaso) atual).setTryEventActiveVaso(false);
						}
						if (((Vaso) atual).isEventActiveVaso()) {
							if (((Vaso) atual).isBroken() && ((Vaso) atual).isChoose() && !((Vaso) atual).isDrope()) { // PEGA
								for (int j = 0; j < Inventario.slot.length; j++) {
									if (Inventario.slot[j].getIdentity() == "") { // Achou slot vazio
										Inventario.slot[j].setAmount(1);
										Inventario.slot[j].setIdentity(((Vaso) atual).getIdDrop());
										Inventario.slot[j].setShortName(((Vaso) atual).getShortNameDrop());
										Inventario.slot[j].setImageSlot(((Vaso) atual).getImageDrop());
										((Vaso) atual).setDrope(true);
										break;
									}
								}
							} else { // QUEBRA VASO
								((Vaso) atual).setAnimation(true);
								((Vaso) atual).setEventActiveVaso(false);
							}
						}
					} else if (atual instanceof Livro) {
						if (((Livro) atual).isTryEventActiveLivro() && !((Livro) atual).isEventActiveLivro()
								&& ((Livro) atual).isChoose()) {
							// System.out.println("vai pro inferno");
							((Livro) atual).setAnimation(true);
							((Livro) atual).setTryEventActiveLivro(false);

							Game.uiLivro = true;
							((Livro) atual).getAnotacaoDialogue().setStatus(true);
							((Livro) atual).setChoose(true);
							Player.inEvent = true;
						} else if (((Livro) atual).isEventActiveLivro()) {
							if (!((Livro) atual).getAnotacaoDialogue().isLestPage())
								((Livro) atual).getAnotacaoDialogue().setNextPaginaSelected(true);
							else {
								((Livro) atual).getAnotacaoDialogue().setExit(true);
							}
						}
					} else if (atual instanceof eventBlock) {
						if (((eventBlock) atual).isTryActive() && !((eventBlock) atual).isActive()
								&& !((eventBlock) atual).isUsed() && ((eventBlock) atual).isChoose()) {
							((eventBlock) atual).setTryActive(false);
							((eventBlock) atual).setActive(true);
						}
						if (((eventBlock) atual).isActive() && !((eventBlock) atual).isUsed()
								&& ((eventBlock) atual).isChoose()) {
							((eventBlock) atual).setActive(false);
							for (int j = 0; j < Inventario.slot.length; j++) {
								if (Inventario.slot[j].getIdentity() == "") { // Achou slot vazio
									Inventario.slot[j].setAmount(1);
									Inventario.slot[j].setIdentity(((eventBlock) atual).getIdDrop());
									Inventario.slot[j].setShortName(((eventBlock) atual).getShortNameDrop());
									Inventario.slot[j].setImageSlot(((eventBlock) atual).getImageDrop());
									((eventBlock) atual).setUsed(true);
									break;
								}
							}
						}
					} else if (atual instanceof Poco) {
						if (((Poco) atual).isTryEventActivePoco() && !((Poco) atual).isEventActivePoco()
								&& !((Poco) atual).isUsed() && Game.diario.read >= 2) {
							((Poco) atual).setTryEventActivePoco(false);
							((Poco) atual).setEventActivePoco(true);
						}
						if (((Poco) atual).isEventActivePoco() && !((Poco) atual).isUsed()) {
							((Poco) atual).setEventActivePoco(false);
							for (int j = 0; j < Inventario.slot.length; j++) {
								if (Inventario.slot[j].getIdentity() == "") { // Achou slot vazio
									Inventario.slot[j].setAmount(1);
									Inventario.slot[j].setIdentity(((Poco) atual).getIdDrop());
									Inventario.slot[j].setShortName(((Poco) atual).getShortNameDrop());
									Inventario.slot[j].setImageSlot(((Poco) atual).getImageDrop());
									((Poco) atual).setUsed(true);
									break;
								}
							}
						}
					} else if (atual instanceof Alavanca) {
						if (((Alavanca) atual).isTryAnimation() && !Player.usedAlavanca
								&& !((Alavanca) atual).isActive()) {
							for (int j = 0; j < Inventario.slot.length; j++) {
								if (Inventario.slot[j].getIdentity() == ((Alavanca) atual).getIdentify()) { // Usa
																											// alavanca
									((Alavanca) atual).setAnimation(true);
									Player.usedAlavanca = true;
									break;
								}
							}
						}
					}
				}

			}
			if (Player.inEvent) {
				if (Inventario.status && Inventario.focus) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (Game.inventario.getSelectedItem() + 1 < Game.inventario.getNumSlots())
							Game.inventario.setSelectedItem(Game.inventario.getSelectedItem() + 1);
					}

					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (Game.inventario.getSelectedItem() - 1 >= 0)
							Game.inventario.setSelectedItem(Game.inventario.getSelectedItem() - 1);
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (Inventario.slot[Inventario.selectedItem].getIdentity() == "Diario"
							&& Game.diario.eventActiveLivro)
						Game.diario.getAnotacaoDialogue().setExit(true);

					for (Entity atual : Game.entities) {
						if (atual instanceof Chest) {
							if (((Chest) atual).isOpenChest()) {
								if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
									((Chest) atual).setAnimation(true);
									((Chest) atual).setTryAnimation(false);
									Game.uiChest = false;
									Player.inEvent = false;
								}

							}
						} else if (atual instanceof Princesa) {
							if (((Princesa) atual).isEventActivePrincesa()) {
								((Princesa) atual).setEventActivePrincesa(false);
								Player.inEvent = false;
							}
						} else if (atual instanceof Esqueleto) {
							if (((Esqueleto) atual).isEventActiveEsqueleto()) {
								((Esqueleto) atual).setEventActiveEsqueleto(false);
								Player.inEvent = false;
							}
						} else if (atual instanceof Placa) {
							if (((Placa) atual).isEventActivePlaca()) {
								((Placa) atual).getAnotacao().setExit(true);
							}
						} else if (atual instanceof Livro) {
							if (((Livro) atual).isEventActiveLivro() && ((Livro) atual).isOpen()) {
								((Livro) atual).getAnotacaoDialogue().setExit(true);
							}
						}
					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_3
					|| e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_5
					|| e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_7
					|| e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_9) {
				// System.out.println("key " + e.getKeyCode());
				for (Entity atual : Game.entities)
					if (atual instanceof Chest)
						if (((Chest) atual).isOpenChest() && ((Chest) atual).isFocus()
								&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest))
							((Chest) atual).setselectedItem(Character.getNumericValue(e.getKeyCode()) - 1);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
				for (Entity atual : Game.entities) {
					if (atual instanceof Chest) {
						if (((Chest) atual).isOpenChest() && ((Chest) atual).isFocus()
								&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)
								&& e.getKeyCode() == KeyEvent.VK_RIGHT) {
							if (((Chest) atual).getselectedItem() + 1 < ((Chest) atual).getNumSlots())
								((Chest) atual).setselectedItem(((Chest) atual).getselectedItem() + 1);
							else
								((Chest) atual).setselectedItem(((Chest) atual).getNumSlots() - 1);
						} else if (((Chest) atual).isOpenChest() && ((Chest) atual).isFocus()
								&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)
								&& e.getKeyCode() == KeyEvent.VK_LEFT) {
							if (((Chest) atual).getselectedItem() - 1 > 0)
								((Chest) atual).setselectedItem(((Chest) atual).getselectedItem() - 1);
							else
								((Chest) atual).setselectedItem(0);
						}
					}
				}
			} else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (Entity atual : Game.entities) {
					if (atual instanceof Chest) {
						if (((Chest) atual).isOpenChest()) {

							if (e.getKeyCode() == KeyEvent.VK_Q && !Inventario.focus) { // Foca no inventario
								Inventario.focus = true;
								((Chest) atual).setFocus(false);
							} else if (e.getKeyCode() == KeyEvent.VK_Q && !((Chest) atual).isFocus()) { // Bau
								Inventario.focus = false;
								((Chest) atual).setFocus(true);
							}
							if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Troca de itens, bau e inventario
								if (Inventario.focus && (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
									Warehouse.numExchangeInventory = Inventario.selectedItem;
									Warehouse.temporaryInventory = Inventario.slot[Warehouse.numExchangeInventory];
									Warehouse.exchangeInventory = true;
									((Chest) atual).setFocus(true);
									Inventario.focus = false;
								} else if (((Chest) atual).isFocus()
										&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
									Warehouse.numExchangeChest = ((Chest) atual).getselectedItem();
									Warehouse.temporaryChest = ((Chest) atual).getSlot(Warehouse.numExchangeChest);
									Warehouse.exchangeChest = true;
									Inventario.focus = true;
									((Chest) atual).setFocus(false);
								}
							}
						}
					}
				}
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(Witch.end) {
				Game.EXIT = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Witch) {
						((Witch) entity).setEnter(true);
						// System.out.println("pressed");
						break;
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Witch) {
					if (Witch.event) {
						Witch.esc = true;
						break;
					}
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Boy) {
					Boy.esc = true;
					break;
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Boy) {
					((Boy) entity).setEnter(true);
					break;
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (Game.player.florestEvent) {
				Game.player.florestEvent = false;
				Game.gameEvent = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (GameState.state == GameState.INTRO) {
				Game.enter = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (GameState.state == GameState.INTRO) {
				Game.enter = false;
			}
		}

		if (GameState.state == GameState.PLAYING) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				Player.setRight(false);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				Player.setLeft(false);
			}

			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				Player.setUp(false);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				Player.setDown(false);
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (int i = 0; i < Game.entities.size(); i++) {
					Entity atual = Game.entities.get(i);
					if (atual instanceof Door) {
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation())
							((Door) atual).setAnimation(false);
					} else if (atual instanceof Chest) {
						if (((Chest) atual).isTryAnimation() && !((Chest) atual).isAnimation())
							((Chest) atual).setAnimation(false);
					}
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Witch) {
					((Witch) entity).setEnter(false);
					// System.out.println("relesead");
					break;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			for (Entity entity : Game.entities) {
				if (entity instanceof Witch) {
					Witch.esc = false;
					// System.out.println("relesead");
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
