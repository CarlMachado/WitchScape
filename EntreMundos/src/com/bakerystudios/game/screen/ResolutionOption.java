package com.bakerystudios.game.screen;

import com.bakerystudios.engine.Option;

public class ResolutionOption extends Option {
	
	private int WIDTH;
	private int HEIGHT;

	public ResolutionOption(String name, int index, int WIDTH, int HEIGHT) {
		super(name, index);
		this.setWIDTH(WIDTH);
		this.setHEIGHT(HEIGHT);
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

}
