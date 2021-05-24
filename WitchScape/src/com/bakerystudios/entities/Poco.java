package com.bakerystudios.entities;

import java.awt.image.BufferedImage;

public class Poco extends Entity {

	private boolean tryEventActivePoco = false;
	private boolean eventActivePoco = false;
	private boolean used = false;
	
	private boolean choose = false;
	
	private String idDrop;
	private String shortNameDrop;
	private BufferedImage imageDrop;

	public Poco(int x, int y, int width, int height, BufferedImage sprite, String idDrop, String shortNameDrop,
			BufferedImage imageDrop) {
		super(x, y, width, height, sprite);
		
		this.idDrop = idDrop;
		this.shortNameDrop = shortNameDrop;
		this.imageDrop = imageDrop;
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

	public boolean isTryEventActivePoco() {
		return tryEventActivePoco;
	}

	public void setTryEventActivePoco(boolean tryEventActivePoco) {
		this.tryEventActivePoco = tryEventActivePoco;
	}

	public boolean isEventActivePoco() {
		return eventActivePoco;
	}

	public void setEventActivePoco(boolean eventActivePoco) {
		this.eventActivePoco = eventActivePoco;
	}

}
