package com.hatchetstudios.gui;

import java.awt.Graphics;

import com.hatchetstudios.engine.Renderable;
import com.hatchetstudios.engine.Updateble;

public class GraphicUserInterface implements Renderable, Updateble {

	private UserInterface ui;
	private HeadUpDisplay hud;
	
	public GraphicUserInterface() {
		ui = new UserInterface(); 
		hud = new HeadUpDisplay();
	}
	
	@Override
	public void update() {
		ui.update();
		hud.update();
	}

	@Override
	public void render(Graphics g) {
		ui.render(g);
		hud.render(g);
	}

}
