package com.hatchetstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.hatchetstudios.engine.Renderable;
import com.hatchetstudios.engine.Updateble;
import com.hatchetstudios.game.Game;

public class Placa extends Entity implements Renderable, Updateble {

	private List<String>[] placaDialogue;
	private Anotacao anotacaoDialogue;

	protected boolean tryEventActivePlaca = false;
	protected boolean eventActivePlaca = false;

	private boolean choose = false;

	public Placa(int x, int y, int width, int height, BufferedImage sprite, List<String>[] placaDialogue) {
		super(x, y, width, height, sprite);

		this.placaDialogue = placaDialogue;
		anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, placaDialogue);
	}

	public void update() {
		if (anotacaoDialogue.isSinalizeExit()) {
			tryEventActivePlaca = false;
			anotacaoDialogue.setSinalizeExit(false);
			anotacaoDialogue.setExit(false);
			Game.uiPlaca = false;
			choose = false;
			Player.inEvent = false;
			eventActivePlaca = false;
			anotacaoDialogue.setExit(false);
			this.anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, placaDialogue);
		}
	}

	public void render(Graphics g) {
		super.render(g);
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public boolean isEventActivePlaca() {
		return eventActivePlaca;
	}

	public void setEventActivePlaca(boolean eventActivePlaca) {
		this.eventActivePlaca = eventActivePlaca;
	}

	public boolean isTryEventActivePlaca() {
		return tryEventActivePlaca;
	}

	public void setTryEventActivePlaca(boolean tryEventActivePlaca) {
		this.tryEventActivePlaca = tryEventActivePlaca;
	}

	public Anotacao getAnotacao() {
		return anotacaoDialogue;
	}

}
