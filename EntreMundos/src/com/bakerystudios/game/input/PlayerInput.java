package com.bakerystudios.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Skeleton;
import com.bakerystudios.entities.Board;
import com.bakerystudios.entities.Player;
import com.bakerystudios.entities.Princess;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.inventario.Inventory;
import com.bakerystudios.inventario.Warehouse;

public class PlayerInput extends Input {

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameState.state == GameState.PLAYING) {
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

			if (Inventory.status && Inventory.focus) {
				if (e.getKeyCode() == KeyEvent.VK_1 && (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventory.selectedItem = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_2
						&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventory.selectedItem = 1;
				} else if (e.getKeyCode() == KeyEvent.VK_3
						&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
					Inventory.selectedItem = 2;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (Entity atual : Game.entities) { 
					if (atual instanceof Door) {
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) {
							if (((Door) atual).isChave()) {
								for (int i = 0; i < Inventory.slot.length; i++) {
									if (Inventory.slot[i].getIdentity() == ((Door) atual).getIdentify()) {
										((Door) atual).setAnimation(true);
										Game.uiDoor = false;
										break;
									}
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
					} else if (atual instanceof Princess) {
						if (((Princess) atual).getExistEventPrincesa() && ((Princess) atual).isEventActivePrincesa()) {
							if (!((Princess) atual).getAnotacao().isEventIsOver())
								((Princess) atual).getAnotacao().setNextPaginaSelected(true);
							else {
								((Princess) atual).setEventActivePrincesa(false);
								((Princess) atual).setTryEventActivePrincesa(false);
								((Princess) atual).getAnotacao().setExitSelected(true);
								Player.inEvent = false;
							}
						}
						if (((Princess) atual).isTryEventActivePrincesa()
								&& !((Princess) atual).isEventActivePrincesa()) {
							// System.out.println("teste prin");
							Player.inEvent = true;
							((Princess) atual).setEventActivePrincesa(true);
							((Princess) atual).setTryEventActivePrincesa(false);
							Game.uiNpc = false;
						}
					} else if (atual instanceof Skeleton) {
						if (((Skeleton) atual).getExistEventEsqueleto()
								&& ((Skeleton) atual).isEventActiveEsqueleto()) {
							if (!((Skeleton) atual).getAnotacao().isEventIsOver())
								((Skeleton) atual).getAnotacao().setNextPaginaSelected(true);
							else {
								((Skeleton) atual).setEventActiveEsqueleto(false);
								((Skeleton) atual).setTryEventActiveEsqueleto(false);
								((Skeleton) atual).getAnotacao().setExitSelected(true);
								Player.inEvent = false;
							}
						}
						if (((Skeleton) atual).isTryEventActiveEsqueleto()
								&& !((Skeleton) atual).isEventActiveEsqueleto()) {
							// System.out.println("teste esq");
							Player.inEvent = true;
							((Skeleton) atual).setEventActiveEsqueleto(true);
							((Skeleton) atual).setTryEventActiveEsqueleto(false);
							Game.uiNpc = false;
						}
					} else if (atual instanceof Board) {
						if (((Board) atual).isTryEventActivePlaca() && !((Board) atual).isEventActivePlaca()) {
							Player.inEvent = true;
							((Board) atual).setEventActivePlaca(true);
							((Board) atual).setTryEventActivePlaca(false);
							Game.uiPlaca = false;
						} else if (((Board) atual).isEventActivePlaca()) {
							((Board) atual).getAnotacao().setNextPaginaSelected(true);
							if (((Board) atual).getAnotacao().getCurrentPagina() + 1 >= ((Board) atual).getAnotacao()
									.getPaginas()) {
								((Board) atual).setEventActivePlaca(false);
								((Board) atual).getAnotacao().setExitSelected(true);
								Player.inEvent = false;
							}
						}
					}
				}
			}
			if (Player.inEvent) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
						} else if (atual instanceof Princess) {
							if (((Princess) atual).isEventActivePrincesa()) {
								((Princess) atual).setEventActivePrincesa(false);
								Player.inEvent = false;
							}
						} else if (atual instanceof Skeleton) {
							if (((Skeleton) atual).isEventActiveEsqueleto()) {
								((Skeleton) atual).setEventActiveEsqueleto(false);
								Player.inEvent = false;
							}
						} else if (atual instanceof Board) {
							if (((Board) atual).isEventActivePlaca()) {
								((Board) atual).setEventActivePlaca(false);
								((Board) atual).getAnotacao().setExitSelected(true);
								Player.inEvent = false;
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_2
						|| e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_4
						|| e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_6
						|| e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_8
						|| e.getKeyCode() == KeyEvent.VK_9) {
					for (Entity atual : Game.entities)
						if (atual instanceof Chest)
							if (((Chest) atual).isOpenChest() && ((Chest) atual).isFocus()
									&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest))
								((Chest) atual).setselectedItem(Character.getNumericValue(e.getKeyCode()) - 1);
				} else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (Entity atual : Game.entities) {
						if (atual instanceof Chest) {
							if (((Chest) atual).isOpenChest()) {

								if (e.getKeyCode() == KeyEvent.VK_Q && !Inventory.focus) { // Foca no inventario
									System.out.println("cheguei");
									Inventory.focus = true;
									((Chest) atual).setFocus(false);
								} else if (e.getKeyCode() == KeyEvent.VK_Q && !((Chest) atual).isFocus()) { // Bau
									Inventory.focus = false;
									((Chest) atual).setFocus(true);
								}
								if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Troca de itens, bau e inventario
									if (Inventory.focus
											&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
										Warehouse.numExchangeInventory = Inventory.selectedItem;
										Warehouse.temporaryInventory = Inventory.slot[Warehouse.numExchangeInventory];
										Warehouse.exchangeInventory = true;
										((Chest) atual).setFocus(true);
										Inventory.focus = false;
									} else if (((Chest) atual).isFocus()
											&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
										Warehouse.numExchangeChest = ((Chest) atual).getselectedItem();
										Warehouse.temporaryChest = ((Chest) atual).getSlot(Warehouse.numExchangeChest);
										Warehouse.exchangeChest = true;
										Inventory.focus = true;
										((Chest) atual).setFocus(false);
									}
								}
							}
						}
					}
				}
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
