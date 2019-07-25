package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.Main;
import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.TextBox;
import com.bakerystudios.inventario.Inventario;

public class Witch extends Entity implements Updateble, Renderable {

	public static boolean enter = false, esc = false, event = false, tryEevent = false, activeOne = false, end = false;
	
	private boolean isHunting = true;
	private boolean seeingPlayer = false;
	private boolean teleporting = false;
	
	private boolean moved = true;
	private int waitFrames = 0, maxWaitFrames = 60;
	private double speed = 0.5;
	public static final int RIGHT_DIR = 0, LEFT_DIR = 1, UP_DIR = 2, DOWN_DIR = 3;
	private int dir = LEFT_DIR;
	protected static boolean right, left = true, up, down;
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 2;

	private BufferedImage[] rightSprite;
	private BufferedImage[] leftSprite;
	private BufferedImage[] downSprite;
	private BufferedImage[] upSprite;
	
	public static Anotacao anotacaoDialogue;

	public Witch(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightSprite = new BufferedImage[3];
		leftSprite = new BufferedImage[3];
		downSprite = new BufferedImage[3];
		upSprite = new BufferedImage[3];
		
		for (int i = 0; i < 3; i++) {
			downSprite[i] = Game.characters.getSprite(i * 16, 128, 16, 16);
			leftSprite[i] = Game.characters.getSprite(i * 16, 144, 16, 16);
			rightSprite[i] = Game.characters.getSprite(i * 16, 160, 16, 16);
			upSprite[i] = Game.characters.getSprite(i * 16, 176, 16, 16);
		}
		
		ArrayList[] esqueletoDialogue = new ArrayList[3];
		for (int i = 0; i < esqueletoDialogue.length; i++)
			esqueletoDialogue[i] = new ArrayList<String>();
		esqueletoDialogue[0].add("Ahahahahah!");
		esqueletoDialogue[0].add("Você caiu direitinho na minha armadilha!");
		esqueletoDialogue[1].add("Minha despensa ja estava ficando vazia,");
		esqueletoDialogue[1].add("você chegou na hora certa!");
		esqueletoDialogue[2].add("Bom... Se não se importa,");
		esqueletoDialogue[2].add("agora é hora do jantar. Muahahahah!");
		anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, esqueletoDialogue);
	}
	
	private void hunting() {
		animation();
		walk();
		vision();
		if(x == 160) {
			moved = false;
			left = false;
			waitToWalk(RIGHT_DIR);
		}
		if(x == 272) {
			moved = false;
			right = false;
			waitToWalk(LEFT_DIR);
		}
		
		if(teleporting) {
			boolean hasKey = false;
			for(int i = 0; i < Inventario.slot.length; i++) {
				if(Inventario.slot[i].getIdentity() == "chave1") {
					hasKey = true;
				}
			}
			if(hasKey) {
				// eixo x
				Game.player.setX(480);
				// eixo y
				Game.player.setY(624);
				Game.player.setDir(RIGHT_DIR);
				teleporting = false;
				Game.gameEvent = false;
			} else {
				for (Entity entity : Game.entities) {
					if (entity instanceof Vaso) {
						entity = new Vaso(368, 592, Tile.SIZE, Tile.SIZE, null, "chave1", "Chave da Porta", Game.doors.getSprite(0, 160, Tile.SIZE, Tile.SIZE));
						// eixo x
						Game.player.setX(480);
						// eixo y
						Game.player.setY(624);
						Game.player.setDir(RIGHT_DIR);
						teleporting = false;
						Game.gameEvent = false;
						break;
					}
				}
			}
		}
	}
	
	private void walk() {
		if(right) {
			x += speed;
		} else if(left) {
			x -= speed;
		}
	}

	private void animation() {
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		} else {
			index = 1;
		}
	}
	
	private void waitToWalk(int dir) {
		waitFrames++;
		if (waitFrames == maxWaitFrames) {
			waitFrames = 0;
			this.dir = dir;
			moved = true;
			if(dir == RIGHT_DIR) right = true;
			if(dir == LEFT_DIR) left = true;
		}
	}
	
	private void vision() {
		// visao andando para a direita e esquerda em baixo e em cima
		// limitador da bruxa
		if(x <= 256  && x >= 192 && right &&
				// eixo x
				Game.player.getX() > 192 && Game.player.getX() < 256 &&
				// eixo y
				Game.player.getY() >= 208 && Game.player.getY() <= 336) {
			
			seeingPlayer = true;
			Game.gameEvent = true;
			//System.out.println("viu dir");
		}
		// visao andando para a direita e esquerda em baixo e em cima
		// limitador da bruxa
		if(x <= 256  && x >= 192 && left &&
				// limitador player eixo x
				Game.player.getX() > 192 && Game.player.getX() < 256 &&
				// limitador player eixo y
				Game.player.getY() >= 208 && Game.player.getY() <= 336) {
			
			seeingPlayer = true;
			Game.gameEvent = true;
			//System.out.println("viu esq");
		}
		// visao da esquerda
		// limitador da bruxa
		if(left &&
				// limitador player eixo x
				Game.player.getX() >= 128 && Game.player.getX() <= 272 &&
				// limitador player eixo y
				Game.player.getY() >= 256 && Game.player.getY() <= 288) {
			
			seeingPlayer = true;
			Game.gameEvent = true;
			//System.out.println("esq");
		}
		// visao da esquerda
		// limitador da bruxa
		if(right &&
				// limitador player eixo x
				Game.player.getX() >= 160 && Game.player.getX() <= 272 &&
				// limitador player eixo y
				Game.player.getY() >= 256 && Game.player.getY() <= 288) {
			
			seeingPlayer = true;
			Game.gameEvent = true;
			//System.out.println("dir");
		}
	}

	@Override
	public void update() {
		if(isHunting && !seeingPlayer) {
			hunting();
		}
		
		if(Game.player.getX() >= 528 && Game.player.getX() <= 608 && Game.player.getY() >= 128) {
			for(int i = 0; i < Inventario.slot.length; i++) {
				if(Inventario.slot[i].getIdentity() == "Bola") {
					//System.out.println("teste");
					isHunting = false;
					tryEevent = true;
					dir = UP_DIR;
					x = 528;
					y = 160;
				}
			}
			if(!event && tryEevent) {
				activeOne = true;
				enter = false;
				event = true;
				Game.gameEvent = true;
				anotacaoDialogue.setStatus(true);
				Player.inEvent = true;
			}
			if(enter && event && tryEevent) {
				enter = false;
				
				if (!anotacaoDialogue.isLestPage()) 
					anotacaoDialogue.setNextPaginaSelected(true);
				else 
					anotacaoDialogue.setExit(true);		
			}
			if(esc && tryEevent) {
				esc = false;
				anotacaoDialogue.setExit(true);
			}
			if (anotacaoDialogue.isSinalizeExit() && tryEevent) {
				event = false;
				esc = false;
				enter = false;
				anotacaoDialogue.setSinalizeExit(false);
				anotacaoDialogue.setExit(false);
				Player.inEvent = false;
				Game.gameEvent = false;
				
				ArrayList[] esqueletoDialogue = new ArrayList[3];
				for (int i = 0; i < esqueletoDialogue.length; i++)
					esqueletoDialogue[i] = new ArrayList<String>();
				esqueletoDialogue[0].add("Ahahahahah!");
				esqueletoDialogue[0].add("Você caiu direitinho na minha armadilha!");
				esqueletoDialogue[1].add("Minha despensa ja estava ficando vazia,");
				esqueletoDialogue[1].add("você chegou na hora certa!");
				esqueletoDialogue[2].add("Bom... Se não se importa,");
				esqueletoDialogue[2].add("agora é hora do jantar. Muahahahah!");
				anotacaoDialogue = new Anotacao(0, 600, 0, 0, null, true, esqueletoDialogue);
				
				end = true;
			}
		}

		if(enter) {
			if(seeingPlayer) {
				seeingPlayer = false;
				teleporting = true;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (dir == RIGHT_DIR) {
			g.drawImage(rightSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == LEFT_DIR) {
			g.drawImage(leftSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == UP_DIR) {
			g.drawImage(upSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == DOWN_DIR) {
			g.drawImage(downSprite[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		}
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isSeeingPlayer() {
		return seeingPlayer;
	}

	public void setSeeingPlayer(boolean seeingPlayer) {
		this.seeingPlayer = seeingPlayer;
	}
	
}
