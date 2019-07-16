package com.bakerystudios.inventario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.GameState;
import com.bakerystudios.game.screen.Screen;

public class Inventario implements Renderable, Updateble {

	private int numSlots = 3;
	private int widthSlot = 64;
	private int widthInventario = numSlots * widthSlot;
	private int initialPosition = (Screen.SCALE_WIDTH / 2) - (widthInventario / 2);

	public static Slot[] slot;

	public static int selectedItem = 0;
	public static boolean status = true;
	public static boolean visible = true;
	public static boolean focus = true;

	public Inventario() {
		slot = new Slot[numSlots];
		for (int i = 0; i < slot.length; i++)
			slot[i] = new Slot();
		slot[0].setIdentity("a");
		slot[1].setIdentity("b");
		slot[2].setIdentity("c");
	}

	public void update() {

	}

	public void render(Graphics g) {
		if (GameState.state == GameState.PLAYING && !Game.uiDoor && visible) {
			int numberMagic = 100;
			for (int i = 0; i < numSlots; i++) {
				g.setColor(Color.RED);
				g.drawRect(initialPosition + i * widthSlot, Screen.SCALE_HEIGHT - numberMagic, widthSlot, widthSlot);
				g.setColor(Color.GRAY);
				g.fillRect(initialPosition + i * widthSlot + 1, Screen.SCALE_HEIGHT - numberMagic + 1, widthSlot - 1,
						widthSlot - 1);

				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD, 18));
				g.drawString(Integer.toString(slot[i].getAmount()), initialPosition + i * widthSlot + widthSlot - 12,
						Screen.SCALE_HEIGHT - numberMagic + widthSlot - 4);
			}
			// ITEM SELECIONADO
			if(focus) {
				g.setColor(Color.BLUE);
				g.drawRect(initialPosition + selectedItem * widthSlot, Screen.SCALE_HEIGHT - numberMagic, widthSlot, widthSlot);
				g.setColor(Color.GRAY);
				g.fillRect(initialPosition + selectedItem * widthSlot + 1, Screen.SCALE_HEIGHT - numberMagic + 1, widthSlot - 1,
						widthSlot - 1);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(slot[selectedItem].getAmount()), initialPosition + selectedItem * widthSlot + widthSlot - 12,
						Screen.SCALE_HEIGHT - numberMagic + widthSlot - 4);
			}
			if(Warehouse.exchangeInventory) {
				g.setColor(Color.GREEN);
				g.drawRect(initialPosition + selectedItem * widthSlot, Screen.SCALE_HEIGHT - numberMagic, widthSlot, widthSlot);
				g.setColor(Color.GRAY);
				g.fillRect(initialPosition + selectedItem * widthSlot + 1, Screen.SCALE_HEIGHT - numberMagic + 1, widthSlot - 1,
						widthSlot - 1);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(slot[selectedItem].getAmount()), initialPosition + selectedItem * widthSlot + widthSlot - 12,
						Screen.SCALE_HEIGHT - numberMagic + widthSlot - 4);
			}
		}

	}

}
