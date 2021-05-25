package com.hatchetstudios.events;

import java.awt.image.BufferedImage;

import com.hatchetstudios.entities.Entity;

public class eventBlock extends Entity {

	private boolean active = false;
	private boolean tryActive = false;

	private boolean used = false;

	private boolean drope = false;
	private String idDrop;
	private String shortNameDrop;
	private BufferedImage imageDrop;

	private boolean choose = false;

	public eventBlock(int x, int y, int width, int height, BufferedImage sprite, boolean drope, String idDrop,
			String shortNameDrop, BufferedImage imageDrop) {
		super(x, y, width, height, sprite);
		
		this.drope = drope;
		this.idDrop = idDrop;
		this.shortNameDrop = shortNameDrop;
		this.imageDrop = imageDrop;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isTryActive() {
		return tryActive;
	}

	public void setTryActive(boolean tryActive) {
		this.tryActive = tryActive;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isDrope() {
		return drope;
	}

	public void setDrope(boolean drope) {
		this.drope = drope;
	}

	public String getIdDrop() {
		return idDrop;
	}

	public void setIdDrop(String idDrop) {
		this.idDrop = idDrop;
	}

	public String getShortNameDrop() {
		return shortNameDrop;
	}

	public void setShortNameDrop(String shortNameDrop) {
		this.shortNameDrop = shortNameDrop;
	}

	public BufferedImage getImageDrop() {
		return imageDrop;
	}

	public void setImageDrop(BufferedImage imageDrop) {
		this.imageDrop = imageDrop;
	}

}
