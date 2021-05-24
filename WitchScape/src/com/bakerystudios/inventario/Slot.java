package com.bakerystudios.inventario;

import java.awt.image.BufferedImage;

public class Slot {
	
	private int amount = 0;
	private String identity = "";
	private String shortName = "Vazio";
	private BufferedImage imageSlot;	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public BufferedImage getImageSlot() {
		return imageSlot;
	}

	public void setImageSlot(BufferedImage imageSlot) {
		this.imageSlot = imageSlot;
	}
	
}
