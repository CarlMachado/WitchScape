package com.bakerystudios.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;

public class Boy extends Entity implements Updateble {
	
	public static boolean enter;
	public static boolean event;
	
	public static ArrayList[] esqueletoDialogue;
	public static Anotacao anotacaoDialogue;

	@SuppressWarnings("unchecked")
	public Boy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		ArrayList[] esqueletoDialogue = new ArrayList[3];
		for (int i = 0; i < esqueletoDialogue.length; i++)
			esqueletoDialogue[i] = new ArrayList<String>();
		esqueletoDialogue[0].add("Oi moço!");
		esqueletoDialogue[0].add("Você poderia me ajduar?");
		esqueletoDialogue[1].add("Eu estava brincando aqui com minha bola,");
		esqueletoDialogue[1].add("Mas sem querer deixei ela cair no cercado");
		esqueletoDialogue[1].add("dessa senhora, e ela não quer me devolver...");
		esqueletoDialogue[2].add("Nossa, você é muito gentil!!!");
		esqueletoDialogue[2].add("Vou ficar esperando aqui.");
		setAnotacaoDialogue(new Anotacao(0, 600, 0, 0, null, true, esqueletoDialogue));
	}

	@Override
	public void update() {
		if(x == Game.player.getX() && y + 16 == Game.player.getY() && Game.player.getDir() == Game.player.UP_DIR) {
			if(enter) {
				event = true;
				Game.gameEvent = true;
			}
		}
		
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public ArrayList[] getEsqueletoDialogue() {
		return esqueletoDialogue;
	}

	public void setEsqueletoDialogue(ArrayList[] esqueletoDialogue) {
		this.esqueletoDialogue = esqueletoDialogue;
	}

	public Anotacao getAnotacaoDialogue() {
		return anotacaoDialogue;
	}

	public void setAnotacaoDialogue(Anotacao anotacaoDialogue) {
		this.anotacaoDialogue = anotacaoDialogue;
	}
	
}
