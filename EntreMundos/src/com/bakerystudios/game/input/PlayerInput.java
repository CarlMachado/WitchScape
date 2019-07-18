package com.bakerystudios.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bakerystudios.entities.Anotacao;
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Esqueleto;
import com.bakerystudios.entities.Player;
import com.bakerystudios.entities.Princesa;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.inventario.Inventario;
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
				for (Entity atual : Game.entities) {
					if (atual instanceof Door) {
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) {
							if(((Door) atual).isChave()) {
								for(int i = 0; i < Inventario.slot.length; i++) {
									if(Inventario.slot[i].getIdentity() == ((Door) atual).getIdentify()) {
										((Door) atual).setAnimation(true);
										Game.uiDoor = false;
									}
								}
							}else {
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
					} else if(atual instanceof Anotacao) {
						if (((Anotacao) atual).isStatusEventoAnotacao()) {
								((Anotacao) atual).setNextPagina(true);
						}
					} else if(atual instanceof Princesa) {
						if(((Princesa) atual).getExistEventPrincesa() && ((Princesa) atual).isEventActivePrincesa()) {
							System.out.println("true next");
							((Princesa) atual).getAnotacao().setNextPaginaSelected(true);
						}
						if(((Princesa) atual).isTryEventActivePrincesa() && !((Princesa) atual).isEventActivePrincesa()) {
							//System.out.println("teste prin");
							Player.inEvent = true;
							((Princesa) atual).setEventActivePrincesa(true);
							((Princesa) atual).setTryEventActivePrincesa(false);
							Game.uiNpc = false;						
						}						
					}else if(atual instanceof Esqueleto) {
						if(((Esqueleto) atual).getExistEventEsqueleto() && ((Esqueleto) atual).isEventActiveEsqueleto()) {
							System.out.println("true next");
							((Esqueleto) atual).getAnotacao().setNextPaginaSelected(true);
						}
						if(((Esqueleto) atual).isTryEventActiveEsqueleto() && !((Esqueleto) atual).isEventActiveEsqueleto()) {
							//System.out.println("teste esq");
							Player.inEvent = true;
							((Esqueleto) atual).setEventActiveEsqueleto(true);
							((Esqueleto) atual).setTryEventActiveEsqueleto(false);
							Game.uiNpc = false;
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
						}else if(atual instanceof Anotacao) {
							if (((Anotacao) atual).isStatusEventoAnotacao()) {
								((Anotacao) atual).setExitSelected(true);
							}
						} else if(atual instanceof Princesa) {
							if(((Princesa) atual).isEventActivePrincesa()) {								
								((Princesa) atual).setEventActivePrincesa(false);
								Player.inEvent = false;
							}
						}else if(atual instanceof Esqueleto) {
							if(((Esqueleto) atual).isEventActiveEsqueleto()) {								
								((Esqueleto) atual).setEventActiveEsqueleto(false);
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

								if (e.getKeyCode() == KeyEvent.VK_Q && !Inventario.focus) { // Foca no inventario
									System.out.println("cheguei");
									Inventario.focus = true;
									((Chest) atual).setFocus(false);
								} else if (e.getKeyCode() == KeyEvent.VK_Q && !((Chest) atual).isFocus()) { // Bau
									Inventario.focus = false;
									((Chest) atual).setFocus(true);
								}
								if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Troca de itens, bau e inventario
									if (Inventario.focus
											&& (!Warehouse.exchangeInventory || !Warehouse.exchangeChest)) {
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
