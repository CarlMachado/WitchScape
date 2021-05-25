package com.hatchetstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hatchetstudios.engine.Renderable;
import com.hatchetstudios.engine.Updateble;
import com.hatchetstudios.engine.camera.Camera;
import com.hatchetstudios.engine.graphics.engine.Tile;
import com.hatchetstudios.game.Game;
import com.hatchetstudios.game.screen.Screen;
import com.hatchetstudios.inventario.Inventario;
import com.hatchetstudios.inventario.Slot;
import com.hatchetstudios.inventario.Warehouse;

public class Chest extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean tryAnimation = false;
	private boolean animation = false;

	private int currentAnimacao = 0;
	private int maxAnimacao = 0;
	private int currentFrame = 0;
	private int maxFrame = 2;

	private boolean openChest = false;

	private int numSlots = 9;
	private int numSlotsperLine = 3;
	private int widthSlot = 60;
	private int heightSlot = 50;
	private int widthInventario = numSlots * widthSlot;
	private int initialPosition = (Screen.SCALE_WIDTH / 2) - (widthInventario / 2);
	private int heightPosition = (Screen.SCALE_HEIGHT / 2) - (heightSlot / 2);

	private Slot[] slot;

	private int selectedItem = 0;

	private boolean focus = true;

	public Chest(int x, int y, int width, int height, BufferedImage sprite, Slot[] slotExterno, int numSlots) {
		super(x, y, width, height, sprite);

		sprites = new BufferedImage[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = Game.doors.getSprite(96, 16 * i, Tile.SIZE, Tile.SIZE);
		}
		this.maxAnimacao = sprites.length;
		this.numSlots = numSlots;
		this.slot = new Slot[numSlots];
		for (int i = 0; i < this.slot.length; i++)
			this.slot[i] = new Slot();
		this.slot = slotExterno;
	}

	public void update() {
		if (animation) {
			if (openChest) { // Anima para fechar
				currentFrame++;
				if (currentFrame >= maxFrame) {
					currentFrame = 0;
					currentAnimacao--;
				}
				if (currentAnimacao == 0) {
					Inventario.visible = true;
					Inventario.focus = true;
					Inventario.selectedItem = 0;
					Warehouse.exchangeChest = false;
					Warehouse.exchangeInventory = false;
					openChest = false;
					animation = false;
					currentFrame = 0;
					currentFrame = 0;
					currentAnimacao = 0;
				}
			} else { // Anima para abrir
				currentFrame++;
				if (currentFrame >= maxFrame) {
					currentFrame = 0;
					currentAnimacao++;
				}
				if (currentAnimacao >= maxAnimacao) {
					Inventario.visible = true;
					Inventario.focus = false;
					Warehouse.exchangeChest = false;
					Warehouse.exchangeInventory = false;
					openChest = true;
					animation = false;
					selectedItem = 0;
					currentFrame = 0;
					currentFrame = 0;
					currentAnimacao = maxAnimacao - 1;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public boolean isTryAnimation() {
		return tryAnimation;
	}

	public void setTryAnimation(boolean tryAnimation) {
		this.tryAnimation = tryAnimation;
	}

	public boolean isOpenChest() {
		return openChest;
	}

	public void setOpenChest(boolean openChest) {
		this.openChest = openChest;
	}

	public int getNumSlots() {
		return numSlots;
	}

	public void setNumSlots(int value) {
		numSlots = value;
	}

	public int getwidthSlot() {
		return widthSlot;
	}

	public void setwidthSlot(int value) {
		widthSlot = value;
	}

	public int getwidthInventario() {
		return widthInventario;
	}

	public void setwidthInventario(int value) {
		widthInventario = value;
	}

	public int getinitialPosition() {
		return initialPosition;
	}

	public void setinitialPosition(int value) {
		initialPosition = value;
	}

	public int getselectedItem() {
		return selectedItem;
	}

	public void setselectedItem(int value) {
		selectedItem = value;
	}

	public Slot getSlot(int i) {
		return slot[i];
	}

	public void setSlot(Slot slot, int i) {
		this.slot[i] = slot;
	}

	public int getHeightSlot() {
		return heightSlot;
	}

	public void setHeightSlot(int heightSlot) {
		this.heightSlot = heightSlot;
	}

	public int getHeightPosition() {
		return heightPosition;
	}

	public void setHeightPosition(int heightPosition) {
		this.heightPosition = heightPosition;
	}

	public int getNumSlotsperLine() {
		return numSlotsperLine;
	}

	public void setNumSlotsperLine(int numSlotsperLine) {
		this.numSlotsperLine = numSlotsperLine;
	}

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

}
