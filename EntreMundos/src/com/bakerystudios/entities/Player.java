package com.bakerystudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.engine.graphics.engine.World;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public class Player extends Entity implements Renderable, Updateble {

	private int movingFrames = 0, maxMovingFrames = 16;
	private boolean movingRight;
	private boolean movingLeft;
	private boolean movingUp;
	private boolean movingDown;
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 2;
	private boolean moved = false;
	public int rightDir = 0, leftDir = 1, upDir = 2, downDir = 3;
	public int dir = rightDir;
	protected static boolean right, left, up, down;

	private double speed = 1.0;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] upPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];
		
		for(int i = 0; i < 3; i++){
			downPlayer[i] = Game.characters.getSprite(48 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 3; i++){
			leftPlayer[i] = Game.characters.getSprite(48 + (i*16), 16, 16, 16);
		}
		for(int i = 0; i < 3; i++){
			rightPlayer[i] = Game.characters.getSprite(48 + (i*16), 32, 16, 16);
		}
		for(int i = 0; i < 3; i++){
			upPlayer[i] = Game.characters.getSprite(48 + (i*16), 48, 16, 16);
		}
	}
	
	public void movmentChecker() {
		if(up && World.isFree(this.getX(),(int)(y - speed))
				&& !movingDown && !movingLeft && !movingRight) {
			moved = true;
			dir = upDir;
			movingUp = true;
			//y -= speed;
		} else if(down && World.isFree(this.getX(), (int)(y + speed))
				&& !movingUp && !movingLeft && !movingRight) {
			moved = true;
			dir = downDir;
			movingDown = true;
			//y += speed;
		} else if(right && World.isFree((int)(x + speed), this.getY())
				&& !movingDown && !movingLeft && !movingUp) {
			moved = true;
			dir = rightDir;
			movingRight = true;
			//x += speed;
		} else if(left && World.isFree((int)(x - speed), this.getY())
				&& !movingDown && !movingUp && !movingRight) {
			moved = true;
			dir = leftDir;
			movingLeft = true;
			//x -= speed;
		}
	}
	
	public void moving() {
		if(movingRight) {
			movingFrames++;
			x += speed;
			if(movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingRight = false;
				moved = false;
			}
		}
		if(movingLeft) {
			movingFrames++;
			x -= speed;
			if(movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingLeft = false;
				moved = false;
			}
		}
		if(movingUp) {
			movingFrames++;
			y -= speed;
			if(movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingUp = false;
				moved = false;
			}
		}
		if(movingDown) {
			movingFrames++;
			y += speed;
			if(movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingDown = false;
				moved = false;
			}
		}
	}
	
	public void animation() {
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		} else {
			index = 1;
		}
	}

	public void update() {
		movmentChecker();
		moving();
		animation();
		updateCamera();
		checkCollisionAllObjects();
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int) x - Camera.x, (int) y - Camera.y, 16, 16);
		
		if(dir == rightDir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
		} else if(dir == leftDir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
		} else if(dir == upDir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
		} else if(dir == downDir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
		}
	}

	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Screen.WIDTH / 2), 0, World.WIDTH * Tile.SIZE - Screen.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Screen.HEIGHT / 2), 0, World.HEIGHT * Tile.SIZE - Screen.HEIGHT);
	}

	public void checkCollisionAllObjects() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Porta) {
				if(Entity.isColidding(this, atual)) {
					((Porta) atual).setAnimation(true);
					return;
				}								
			} else if (atual instanceof Bau) {
				if(Entity.isColidding(this, atual)) {
					((Bau) atual).setAnimation(true);
					return;	
				}				
			} else if (atual instanceof Anotacao) {
				if(Entity.isColidding(this, atual)) {
					
					return;
				}				
			}
		}
		updateCamera();
	}

	public boolean isRight() {
		return right;
	}

	public static void setUp(boolean up) {
		Player.up = up;
	}

	public static void setDown(boolean down) {
		Player.down = down;
	}

	public static void setLeft(boolean left) {
		Player.left = left;
	}

	public static void setRight(boolean right) {
		Player.right = right;
	}

}
