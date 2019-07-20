package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;

public class Livro extends Entity implements Renderable, Updateble {

	private boolean animation = false;

	private boolean open = false;

	private int currentAnimation = 2;
	private int maxAnimation = 0;
	private int currentFrame = 0;
	private int maxFrame = 10;

	private BufferedImage[] sprites;

	private boolean existEventLivro = false;
	private boolean tryEventActiveLivro = false;
	private boolean eventActiveLivro = false;

	private Anotacao anotacaoDialogue;

	private boolean choose = false;

	public Livro(int x, int y, int width, int height, BufferedImage sprite, BufferedImage[] spriteList,
			boolean existEvent, List<String>[] listAnotacao) {
		super(x, y, width, height, sprite);
		sprites = new BufferedImage[spriteList.length];
		this.sprites = spriteList;
		this.existEventLivro = existEvent;
		this.anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, listAnotacao);
		maxAnimation = sprites.length - 1;
	}

	public void update() {
		//System.out.println("active: " + eventActiveLivro);
		if (eventActiveLivro && !anotacaoDialogue.isSinalizeExit()) {		
			Game.uiLivro = true;
			anotacaoDialogue.setStatus(true);
			setChoose(true);
			
		}
		if (anotacaoDialogue.isSinalizeExit()) {
			System.out.println("teste 300");			
			tryEventActiveLivro = false;
			anotacaoDialogue.setSinalizeExit(false);
			anotacaoDialogue.setExit(false);
			Game.uiLivro = false;
			animation = false;
			choose = false;
			Player.inEvent = false;
			eventActiveLivro = false;
		}
		//System.out.println("animation: " + animation);
		if (animation) {
			tryEventActiveLivro = false;
			currentFrame++;
			if (currentFrame >= maxFrame) {
				currentFrame = 0;
				currentAnimation--;
				if (currentAnimation >= 0 && currentAnimation < maxAnimation)
					sprite = sprites[currentAnimation];
			}
			if (currentAnimation <= 0) {
				System.out.println("teste alado");
				currentFrame = 0;
				animation = false;
				choose = false;
				open = true;
				currentAnimation = 0;
				sprite = sprites[currentAnimation];
				eventActiveLivro = true;
				Game.uiLivro = true;
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(sprites[currentAnimation], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public int getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public int getMaxAnimation() {
		return maxAnimation;
	}

	public void setMaxAnimation(int maxAnimation) {
		this.maxAnimation = maxAnimation;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public Anotacao getAnotacaoDialogue() {
		return anotacaoDialogue;
	}

	public void setAnotacaoDialogue(Anotacao anotacaoDialogue) {
		this.anotacaoDialogue = anotacaoDialogue;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public boolean isExistEventLivro() {
		return existEventLivro;
	}

	public void setExistEventLivro(boolean existEventLivro) {
		this.existEventLivro = existEventLivro;
	}

	public boolean isTryEventActiveLivro() {
		return tryEventActiveLivro;
	}

	public void setTryEventActiveLivro(boolean tryEventActiveLivro) {
		this.tryEventActiveLivro = tryEventActiveLivro;
	}

	public boolean isEventActiveLivro() {
		return eventActiveLivro;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

}
