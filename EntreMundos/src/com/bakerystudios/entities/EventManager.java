package com.bakerystudios.entities;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;

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
				if (atual instanceof Annotation) {
					if(((Annotation) atual).isStatusEventoAnotacao()) {
						((Annotation) atual).setStatusEventoAnotacao(false);
						break;
					}
				}
				if(atual instanceof Princess) {
					if(((Princess) atual).isTryEventActivePrincesa()) {
						((Princess) atual).setTryEventActivePrincesa(false);
					}
				}
				if(atual instanceof Skeleton) {
					if(((Skeleton) atual).isTryEventActiveEsqueleto()) {
						((Skeleton) atual).setTryEventActiveEsqueleto(false);
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
