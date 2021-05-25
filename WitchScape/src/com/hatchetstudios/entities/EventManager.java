package com.hatchetstudios.entities;

import com.hatchetstudios.engine.Updateble;
import com.hatchetstudios.game.Game;

public class EventManager implements Updateble {

	public static boolean esc;
	
	public static void eventCheck() {
		if(esc) {
			for (Entity atual : Game.entities) {
				if (atual instanceof Door) {
					if (((Door) atual).getTryAnimation() && !((Door) atual).getAnimation()) {
						((Door) atual).setAnimation(false);
						((Door) atual).setTryAnimation(false);							
						break;
					}
				}
				if (atual instanceof Chest) {
					if (((Chest) atual).isTryAnimation() && !((Chest) atual).isAnimation()) {
						((Chest) atual).setAnimation(false);
						((Chest) atual).setTryAnimation(false);							
						break;
					}
				}
				if (atual instanceof Anotacao) {
					//if(((Anotacao) atual).isStatusEventoAnotacao()) {
					//	((Anotacao) atual).setStatusEventoAnotacao(false);
					//	break;
					//}
				}
				if(atual instanceof Princesa) {
					if(((Princesa) atual).isTryEventActivePrincesa()) {
						((Princesa) atual).setTryEventActivePrincesa(false);
					}
				}
				if(atual instanceof Esqueleto) {
					if(((Esqueleto) atual).isTryEventActiveEsqueleto()) {
						((Esqueleto) atual).setTryEventActiveEsqueleto(false);
					}
				}
			}
			Game.uiDoor = false;
			Game.uiChest = false;
			Game.uiNpc = false;
		}
	}

	@Override
	public void update() {
		eventCheck();
	}
	
}
