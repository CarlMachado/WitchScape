package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.TextBox;

public class Witch extends Entity implements Updateble, Renderable {

	private boolean enter;
	
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
		Game.gameEvent = seeingPlayer == true? true : false;
		if(teleporting) {
			// eixo x
			Game.player.setX(480);
			// eixo y
			Game.player.setY(624);
			Game.player.setDir(RIGHT_DIR);
			teleporting = false;
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
			//System.out.println("dir");
		}
	}

	@Override
	public void update() {
		if(isHunting && !seeingPlayer) {
			hunting();
		}

		if(enter) {
			if(seeingPlayer) {
				seeingPlayer = false;
				teleporting = true;
			}
		}
	}
	
	protected static void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}
	
	protected static void fillCentralizedRect(Graphics g, int y, int width, int height) {
		g.fillRect(Screen.SCALE_WIDTH / 2 - width / 2, y, width, height);
	}
	
	public static void showDialog(Graphics g, String t1, String t2, String t3, boolean esc , boolean enter) {
		int y = 500;
		int width = 700;
		int height = 200;
		
		g.setFont(Game.boxFont);
		g.setColor(new Color(111, 83, 39));
		fillCentralizedRect(g, y, width, height);
		g.setColor(new Color(190, 163, 115));
		fillCentralizedRect(g, y + 5, width - 10, height - 10);
		
		g.setColor(Color.BLACK);
		if(t1 != null) drawCentralizedString(g, t1, 550);
		if(t2 != null) drawCentralizedString(g, t2, 600);
		if(t3 != null) drawCentralizedString(g, t3, 650);

		g.setColor(Color.BLACK);
		if(enter) g.drawString("ENTER", 900, y + 190);
		if(esc) g.drawString("ESC", 300, y + 190);
		
		System.out.println("desenhou");
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
