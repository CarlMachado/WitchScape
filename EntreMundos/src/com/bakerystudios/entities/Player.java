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
import com.bakerystudios.inventario.Inventario;
import com.bakerystudios.inventario.Slot;
import com.bakerystudios.inventario.Warehouse;

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

	private boolean nextisDoorUp = false;
	private boolean nextisDoorDown = false;

	private boolean nextisChestUp = false;
	private boolean nextisChestDown = false;
	private boolean nextisChestLeft = false;
	private boolean nextisChestRight = false;

	public static boolean inEvent = false;
	public static boolean isPossibleEvent = true;

	public static int controllerInventory = 1; // 1 para Inventario - 2 para Bau

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];

		for (int i = 0; i < 3; i++) {
			downPlayer[i] = Game.characters.getSprite(48 + (i * 16), 0, 16, 16);
			leftPlayer[i] = Game.characters.getSprite(48 + (i * 16), 16, 16, 16);
			rightPlayer[i] = Game.characters.getSprite(48 + (i * 16), 32, 16, 16);
			upPlayer[i] = Game.characters.getSprite(48 + (i * 16), 48, 16, 16);
		}
	}

	public void movmentChecker() {
		if (up) {
			if (!inEvent && !moved)
				dir = upDir;
			if (!inEvent && !nextisChestUp && !nextisDoorUp && World.isFree(this.getX(), (int) (y - speed))
					&& !movingDown && !movingLeft && !movingRight) {
				moved = true;
				movingUp = true;
				correctCollisionDoor();
				correctCollisionChest();
			}
			// y -= speed;
		} else if (down) {
			if (!inEvent && !moved)
				dir = downDir;
			if (!inEvent && !nextisChestDown && !nextisDoorDown && World.isFree(this.getX(), (int) (y + speed))
					&& !movingUp && !movingLeft && !movingRight) {
				moved = true;
				movingDown = true;
				correctCollisionDoor();
				correctCollisionChest();
			}
			// y += speed;
		} else if (right) {
			if (!inEvent && !moved)
				dir = rightDir;
			if (!inEvent && !nextisChestRight && World.isFree((int) (x + speed), this.getY()) && !movingDown
					&& !movingLeft && !movingUp) {
				moved = true;
				movingRight = true;
				correctCollisionDoor();
				correctCollisionChest();
			}
			// x += speed;
		} else if (left) {
			if (!inEvent && !moved)
				dir = leftDir;
			if (!inEvent && !nextisChestLeft && World.isFree((int) (x - speed), this.getY()) && !movingDown && !movingUp
					&& !movingRight) {
				moved = true;
				movingLeft = true;
				correctCollisionDoor();
				correctCollisionChest();
			}
			// x -= speed;
		}
	}

	public void moving() {
		if (movingRight) {
			movingFrames++;
			x += speed;
			if (movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingRight = false;
				moved = false;
			}
		}
		if (movingLeft) {
			movingFrames++;
			x -= speed;
			if (movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingLeft = false;
				moved = false;
			}
		}
		if (movingUp) {
			movingFrames++;
			y -= speed;
			if (movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingUp = false;
				moved = false;
			}
		}
		if (movingDown) {
			movingFrames++;
			y += speed;
			if (movingFrames >= maxMovingFrames) {
				movingFrames = 0;
				movingDown = false;
				moved = false;
			}
		}
	}

	public void animation() {
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex)
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
		// g.setColor(Color.red);
		// g.fillRect((int) x - Camera.x, (int) y - Camera.y, 16, 16);

		if (dir == rightDir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == leftDir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == upDir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		} else if (dir == downDir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		}
	}

	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Screen.WIDTH / 2), 0, World.WIDTH * Tile.SIZE - Screen.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Screen.HEIGHT / 2), 0, World.HEIGHT * Tile.SIZE - Screen.HEIGHT);
	}

	public void checkCollisionAllObjects() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Door) {
				collisionDoor(atual);
			} else if (atual instanceof Chest) {
				collisionChest(atual);
				if (((Chest) atual).isOpenChest()) {
					if (Warehouse.exchangeChest && Warehouse.exchangeInventory) {
						if (Warehouse.firsSelected == 0) { // Primeiro bau
							for (int j = 0; j < Inventario.slot.length; j++) {
								if (Inventario.slot[j].getIdentity() == "") {
									Warehouse.temporaryNum = j;
									Warehouse.temporayNumExist = true;
									break;
								}
							}
							if (Warehouse.temporayNumExist) { // Possui slot vazio
								System.out.println("troca slot vazio - bau");
								Inventario.slot[Warehouse.temporaryNum] = ((Chest) atual).getSlot(Warehouse.numExchangeChest);
								((Chest) atual).setSlot(new Slot(), Warehouse.numExchangeChest);
								System.out.println(((Chest) atual).getSlot(Warehouse.numExchangeChest).getIdentity());
								System.out.println(Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
							} else {
								System.out.println("troca slot preenchido - bau");
								// System.out.println(Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
								((Chest) atual).setSlot(Inventario.slot[Warehouse.numExchangeInventory],
										Warehouse.numExchangeChest);
								Inventario.slot[Warehouse.numExchangeInventory] = Warehouse.temporary;
								// System.out.println(((Chest)
								// atual).getSlot(Warehouse.numExchangeChest).getIdentity());
							}
							Warehouse.resetWareHouse();
						} else { // Primeiro inventario
							Warehouse.resetWareHouse();
							for (int j = 0; j < Inventario.slot.length; j++) {
								if (((Chest) atual).getSlot(j).getIdentity() == "") {
									Warehouse.temporaryNum = j;
									Warehouse.temporayNumExist = true;
									break;
								}
							}
							if (Warehouse.temporayNumExist) { // Possui slot vazio
								System.out.println("troca slot vazio - inventario");
								((Chest) atual).setSlot(Inventario.slot[Warehouse.numExchangeInventory], Warehouse.temporaryNum);
								Inventario.slot[Warehouse.numExchangeInventory] = new Slot();
								System.out.println(((Chest) atual).getSlot(Warehouse.numExchangeChest).getIdentity());
								System.out.println(Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
							} else {
								System.out.println("troca slot preenchido - inventario");
								// System.out.println(Inventario.slot[Warehouse.numExchangeInventory].getIdentity());

								Inventario.slot[Warehouse.numExchangeInventory] = ((Chest) atual)
										.getSlot(Warehouse.numExchangeChest);
								((Chest) atual).setSlot(Warehouse.temporary, Warehouse.numExchangeChest);
								System.out.println(((Chest) atual).getSlot(Warehouse.numExchangeChest).getIdentity());
								System.out.println(Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
							}
							Warehouse.resetWareHouse();
						}
					}
				}
			} else if (atual instanceof Anotacao) {
				if (Entity.isColidding(this, atual)) {

					return;
				}
			}
		}
	}

	public void collisionDoor(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == upDir)
				((Door) atual).setTryAnimation(true);
			if (!((Door) atual).getAnimation() && ((Door) atual).getTryAnimation())
				Game.uiDoor = true;
			if (!((Door) atual).getOpenDoor())
				nextisDoorUp = true;
			else
				nextisDoorUp = false;
			return;
		} else
			((Door) atual).setTryAnimation(false);
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			if (dir == downDir)
				((Door) atual).setTryAnimation(true);
			if (!((Door) atual).getAnimation() && ((Door) atual).getTryAnimation())
				Game.uiDoor = true;
			if (!((Door) atual).getOpenDoor())
				nextisDoorDown = true;
			else
				nextisDoorDown = false;
			return;
		} else
			((Door) atual).setTryAnimation(false);
	}

	public void collisionChest(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == upDir)
				((Chest) atual).setTryAnimation(true);
			if (!((Chest) atual).isAnimation() && ((Chest) atual).isTryAnimation())
				Game.uiChest = true;
			if (!((Chest) atual).isOpenChest())
				Inventario.visible = false;
			nextisChestUp = true;
			return;
		} else
			((Chest) atual).setTryAnimation(false);
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) // Baixo
			nextisChestDown = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) // Direita
			nextisChestRight = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) // Esquerda
			nextisChestLeft = true;
	}

	public void correctCollisionChest() {
		if (nextisChestUp)
			nextisChestUp = false;
		if (nextisChestDown)
			nextisChestDown = false;
		if (nextisChestLeft)
			nextisChestLeft = false;
		if (nextisChestRight)
			nextisChestRight = false;
		Game.uiChest = false;
		Inventario.visible = true;
	}

	public void correctCollisionDoor() {
		if (nextisDoorDown)
			nextisDoorDown = false;
		if (nextisDoorUp)
			nextisDoorUp = false;
		Game.uiDoor = false;
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

	public boolean isNextisChestUp() {
		return nextisChestUp;
	}

	public void setNextisChestUp(boolean nextisChestUp) {
		this.nextisChestUp = nextisChestUp;
	}

	public boolean isNextisChestDown() {
		return nextisChestDown;
	}

	public void setNextisChestDown(boolean nextisChestDown) {
		this.nextisChestDown = nextisChestDown;
	}

	public boolean isNextisChestRight() {
		return nextisChestRight;
	}

	public void setNextisChestRight(boolean nextisChestRight) {
		this.nextisChestRight = nextisChestRight;
	}

	public boolean isNextisChestLeft() {
		return nextisChestLeft;
	}

	public void setNextisChestLeft(boolean nextisChestLeft) {
		this.nextisChestLeft = nextisChestLeft;
	}

}
