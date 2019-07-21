package com.bakerystudios.entities;

import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.Game;

public class Diario implements Updateble  {

	public boolean eventActiveLivro = false;
	
	public Anotacao anotacaoDialogue;
	public List<String>[] listAnotacao;
	
	public Diario() {
		listAnotacao = new ArrayList[3];
		for (int i = 0; i < listAnotacao.length; i++)
			listAnotacao[i] = new ArrayList<String>();
		listAnotacao[0].add("diario que eu amo cagar");
		listAnotacao[0].add("funcione plz nunca pedi nada");
		listAnotacao[1].add("piroca tuts pao");
		listAnotacao[1].add("teste testante testador testmaster god tester");
		listAnotacao[2].add("nada pa falar tuts pao");
		listAnotacao[2].add("testes são topzes");
		listAnotacao[2].add("baduuuuuuuuuuuuuuuuuunts");
		
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
