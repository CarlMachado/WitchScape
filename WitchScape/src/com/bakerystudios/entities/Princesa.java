package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;

public class Princesa extends Entity implements Renderable, Updateble {

	private int currentAnimation = 0;
	private int maxAnimation = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	private BufferedImage[] sprites;

	protected boolean existEventPrincesa = false;
	protected boolean tryEventActivePrincesa = false;
	protected boolean eventActivePrincesa = false;
	
	private List<String>[] princesaDialogue;
	private Anotacao anotacaoDialogue;
	
	private boolean choose = false;

	public Princesa(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEvent) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		sprites = spriteList;
		maxAnimation = sprites.length - 1;
		this.existEventPrincesa = existEvent;
		
		princesaDialogue = new ArrayList[4];
		for(int i = 0; i < princesaDialogue.length; i++)
			princesaDialogue[i] = new ArrayList<String>();
		princesaDialogue[0].add("Olá Herikc, tudo bem?");
		princesaDialogue[0].add("Antes que pergunte, eu estou perfeitamente bem");		
		princesaDialogue[1].add("Você poderia me fazer um favor?");
		princesaDialogue[1].add("Eu preciso que alguém resgate o meu namorado corno");
		princesaDialogue[2].add("Vai se fude mermao BABACA");
		princesaDialogue[2].add("Vai se fude mermao BABACA AAAAA");
		princesaDialogue[3].add("Testes servem pra fude a nossa vida");
		princesaDialogue[3].add("por que sempre encontramos bugs nesse bostas");
		anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, princesaDialogue);
	}

	public void update() {
		animation();
		if (eventActivePrincesa) {
			Game.uiNpc = true;
			anotacaoDialogue.setStatus(true);
			choose = true;
			if (anotacaoDialogue.isSinalizeExit()) {
				System.out.println("teste");
				eventActivePrincesa = false;
				tryEventActivePrincesa = false;
				anotacaoDialogue.setSinalizeExit(false);
				anotacaoDialogue.setExit(false);
				Game.uiNpc = false;
				Player.inEvent = false;
				
				this.anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, princesaDialogue);
			}
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

	public boolean isExistEventPrincesa() {
		return existEventPrincesa;
	}

	public void setExistEventPrincesa(boolean existEventPrincesa) {
		this.existEventPrincesa = existEventPrincesa;
	}

	public boolean isEventActivePrincesa() {
		return eventActivePrincesa;
	}

	public void setEventActivePrincesa(boolean eventActivePrincesa) {
		this.eventActivePrincesa = eventActivePrincesa;
	}

	public boolean isTryEventActivePrincesa() {
		return tryEventActivePrincesa;
	}

	public void setTryEventActivePrincesa(boolean tryEventActivePrincesa) {
		this.tryEventActivePrincesa = tryEventActivePrincesa;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
	
	public Anotacao getAnotacao() {
		return anotacaoDialogue;
	}
	
	public boolean getExistEventPrincesa() {
		return existEventPrincesa;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

}
