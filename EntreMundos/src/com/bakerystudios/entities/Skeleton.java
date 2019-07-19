package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;

public class Skeleton extends Entity implements Renderable, Updateble {

	private int currentAnimation = 0;
	private int maxAnimation = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	private BufferedImage[] sprites;

	protected boolean existEventEsqueleto = false;
	protected boolean tryEventActiveEsqueleto = false;
	protected boolean eventActiveEsqueleto = false;
	
	private List<String>[] esqueletoDialogue;
	private Annotation anotacaoDialogue;
	
	private boolean choose = false;

	public Skeleton(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEventEsqueleto) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		sprites = spriteList;
		maxAnimation = sprites.length - 1;
		this.existEventEsqueleto = existEventEsqueleto;
		
		esqueletoDialogue = new ArrayList[2];
		for(int i = 0; i < esqueletoDialogue.length; i++)
			esqueletoDialogue[i] = new ArrayList<String>();
		esqueletoDialogue[0].add("Eu sou um esqueleto de bosta");
		esqueletoDialogue[0].add("só sirvo para testes e depois serei descartado");		
		esqueletoDialogue[1].add("Mas sabe qual a parte boa, humano?");
		esqueletoDialogue[1].add("que após tudo isso, eu irei lhe matar");
		esqueletoDialogue[1].add("hehe brincaeira, ou não... quem sabe né");
		anotacaoDialogue = new Annotation(0, 600, 0, 0, null, true, esqueletoDialogue);
	}
 
	public void update() {
		animation();
		if (eventActiveEsqueleto) {
			anotacaoDialogue.setStatusEventoAnotacao(true);
			setChoose(true);
		}
		else {
			anotacaoDialogue.setStatusEventoAnotacao(false);
		}
	}

	public void animation() {
		currentFrame++;
		if (currentFrame == maxFrame) {
			currentFrame = 0;
			currentAnimation++;
			if (currentAnimation >= maxAnimation)
				currentAnimation = 0;
		} else
			currentAnimation = 0;
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimation], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public boolean isExistEventEsqueleto() {
		return existEventEsqueleto;
	}

	public void setExistEventEsqueleto(boolean existEventEsqueleto) {
		this.existEventEsqueleto = existEventEsqueleto;
	}

	public boolean isEventActiveEsqueleto() {
		return eventActiveEsqueleto;
	}

	public void setEventActiveEsqueleto(boolean eventActiveEsqueleto) {
		this.eventActiveEsqueleto = eventActiveEsqueleto;
	}

	public boolean isTryEventActiveEsqueleto() {
		return tryEventActiveEsqueleto;
	}

	public void setTryEventActiveEsqueleto(boolean tryEventActiveEsqueleto) {
		this.tryEventActiveEsqueleto = tryEventActiveEsqueleto;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public List<String>[] getEsqueletoDialogue() {
		return esqueletoDialogue;
	}

	public void setEsqueletoDialogue(List<String>[] esqueletoDialogue) {
		this.esqueletoDialogue = esqueletoDialogue;
	}
	
	public Annotation getAnotacao() {
		return anotacaoDialogue;
	}
	
	public boolean getExistEventEsqueleto() {
		return existEventEsqueleto;
	}

}
