package com.hatchetstudios.entities;

import java.util.ArrayList;
import java.util.List;

import com.hatchetstudios.engine.Updateble;

public class Diario implements Updateble  {

	public boolean eventActiveLivro = false;
	public int read = 0;
	
	public Anotacao anotacaoDialogue;
	public List<String>[] listAnotacao;
	
	@SuppressWarnings("unchecked")
	public Diario() {
		listAnotacao = new ArrayList[2];
		for (int i = 0; i < listAnotacao.length; i++)
			listAnotacao[i] = new ArrayList<String>();
		listAnotacao[0].add("10 de Janeiro de 1629");
		listAnotacao[0].add("Hoje aconteceu novamente, um daqules moleques");
		listAnotacao[0].add("deixou cair sua bola aqui dentro!");
		listAnotacao[1].add("12 de Janeiro de 1629");
		listAnotacao[1].add("Ahahahah, ainda ou�o o choro daquela");
		listAnotacao[1].add("crian�a la fora! Ahahahahah");
		
		this.anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, listAnotacao);
	}
	
	public void update() {
		if (anotacaoDialogue.isSinalizeExit()) {		
			anotacaoDialogue.setSinalizeExit(false);
			anotacaoDialogue.setExit(false);
			Player.inEvent = false;
			eventActiveLivro = false;
			anotacaoDialogue.setExit(false);
			this.anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, listAnotacao);
		}
	}

	public boolean isEventActiveLivro() {
		return eventActiveLivro;
	}

	public void setEventActiveLivro(boolean eventActiveLivro) {
		this.eventActiveLivro = eventActiveLivro;
	}

	public Anotacao getAnotacaoDialogue() {
		return anotacaoDialogue;
	}

	public void setAnotacaoDialogue(Anotacao anotacaoDialogue) {
		this.anotacaoDialogue = anotacaoDialogue;
	}

}
