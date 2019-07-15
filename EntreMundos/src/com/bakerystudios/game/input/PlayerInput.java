package com.bakerystudios.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bakerystudios.entities.Anotacao;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Player;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;

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

			if (Anotacao.statusEventoAnotacao) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Anotacao.nextPaginaSelected = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Anotacao.exitSelected = true;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (Entity atual : Game.entities) {
					if (atual instanceof Door) {
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) {					
							((Door) atual).setAnimation(true);
							Game.uiDoor = false;
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

			if (Anotacao.statusEventoAnotacao) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Anotacao.nextPaginaSelected = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Anotacao.exitSelected = false;
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				for (int i = 0; i < Game.entities.size(); i++) {
					Entity atual = Game.entities.get(i);
					if (atual instanceof Door) 
						if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) 
								((Door) atual).setAnimation(false);
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
