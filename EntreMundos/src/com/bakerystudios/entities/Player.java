package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.events.eventBlock;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.inventario.Inventario;
import com.bakerystudios.inventario.Warehouse;

public class Player extends Entity implements Renderable, Updateble {

	private int movingFrames = 0, maxMovingFrames = 16;
	private boolean movingRight;
	private boolean movingLeft;
	private boolean movingUp;
	private boolean movingDown;

	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 2;
	private boolean moved = false;
	public static final int RIGHT_DIR = 0, LEFT_DIR = 1, UP_DIR = 2, DOWN_DIR = 3;
	private int dir = LEFT_DIR;
	protected static boolean right, left, up, down;

	public static boolean usedAlavanca = false;
	public boolean florestEvent;

	private double speed = 1.0;

	private BufferedImage[] rightSprite;
	private BufferedImage[] leftSprite;
	private BufferedImage[] downSprite;
	private BufferedImage[] upSprite;

	private boolean nextisDoorUp = false;
	private boolean nextisDoorDown = false;

	private boolean nextisChestUp = false;
	private boolean nextisChestDown = false;
	private boolean nextisChestLeft = false;
	private boolean nextisChestRight = false;

	private boolean nextisAlavancaUp = false;
	private boolean nextisAlavancaDown = false;
	private boolean nextisAlavancaLeft = false;
	private boolean nextisAlavancaRight = false;

	private boolean nextisPoco = false;

	private boolean nextisNpcUp = false;
	private boolean nextisNpcDown = false;
	private boolean nextisNpcLeft = false;
	private boolean nextisNpcRight = false;

	private boolean nextisPlacaUp = false;
	private boolean nextisPlacaDown = false;
	private boolean nextisPlacaLeft = false;
	private boolean nextisPlacaRight = false;

	private boolean nextisVaso = false;
	private boolean nextisLivro = false;

	public static boolean inEvent = false;
	public static boolean tryActiveEvent = false;

	public static int controllerInventory = 1; // 1 para Inventario - 2 para Bau

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightSprite = new BufferedImage[3];
		leftSprite = new BufferedImage[3];
		downSprite = new BufferedImage[3];
		upSprite = new BufferedImage[3];

		for (int i = 0; i < 3; i++) {
			downSprite[i] = Game.characters.getSprite(48 + (i * 16), 0, 16, 16);
			leftSprite[i] = Game.characters.getSprite(48 + (i * 16), 16, 16, 16);
			rightSprite[i] = Game.characters.getSprite(48 + (i * 16), 32, 16, 16);
			upSprite[i] = Game.characters.getSprite(48 + (i * 16), 48, 16, 16);
		}
	}

	public void movmentChecker() {
		if (up && !Game.gameEvent) {
			if (!inEvent && !moved)
				dir = UP_DIR;
			if (!nextisPlacaUp && !nextisNpcUp && !inEvent && !nextisChestUp && !nextisDoorUp
					&& Game.world.get(Game.CUR_MAP).isFree(this.getX(), (int) (y - speed)) && !movingDown && !movingLeft
					&& !movingRight) {
				moved = true;
				movingUp = true;
				correctCollisionGeneral();
			}
			// y -= speed;
		} else if (down && !Game.gameEvent) {
			if (!inEvent && !moved)
				dir = DOWN_DIR;
			if (!nextisPlacaDown && !nextisNpcDown && !inEvent && !nextisChestDown && !nextisDoorDown
					&& Game.world.get(Game.CUR_MAP).isFree(this.getX(), (int) (y + speed)) && !movingUp && !movingLeft
					&& !movingRight) {
				moved = true;
				movingDown = true;
				correctCollisionGeneral();
			}
			// y += speed;
		} else if (right && !Game.gameEvent) {
			if (!inEvent && !moved)
				dir = RIGHT_DIR;
			if (!nextisPlacaRight && !nextisNpcRight && !inEvent && !nextisChestRight
					&& Game.world.get(Game.CUR_MAP).isFree((int) (x + speed), this.getY()) && !movingDown && !movingLeft
					&& !movingUp) {
				moved = true;
				movingRight = true;
				correctCollisionGeneral();
			}
			// x += speed;
		} else if (left && !Game.gameEvent) {
			if (!inEvent && !moved)
				dir = LEFT_DIR;
			if (!nextisPlacaLeft && !nextisNpcLeft && !inEvent && !nextisChestLeft
					&& Game.world.get(Game.CUR_MAP).isFree((int) (x - speed), this.getY()) && !movingDown && !movingUp
					&& !movingRight) {
				moved = true;
				movingLeft = true;
				correctCollisionGeneral();
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
		
		if(x == 896 && y == 624) {
			florestEvent = true;
			movingLeft = true;
			moved = true;
			dir = LEFT_DIR;
			Game.gameEvent = true;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x - Camera.x, (int) y - Camera.y, 16, 16);
		
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

	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Screen.WIDTH / 2), 0,
				Game.world.get(Game.CUR_MAP).WIDTH * Tile.SIZE - Screen.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Screen.HEIGHT / 2), 0,
				Game.world.get(Game.CUR_MAP).HEIGHT * Tile.SIZE - Screen.HEIGHT);
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
						// System.out.println("ANTIGO:");
						// System.out.println("Inventario:" +
						// Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
						// System.out.println("Bau:" + ((Chest)
						// atual).getSlot(Warehouse.numExchangeChest).getIdentity());

						Inventario.slot[Warehouse.numExchangeInventory] = Warehouse.temporaryChest;
						((Chest) atual).setSlot(Warehouse.temporaryInventory, Warehouse.numExchangeChest);

						// System.out.println("NOVO:");
						// System.out.println("Inventario:" +
						// Inventario.slot[Warehouse.numExchangeInventory].getIdentity());
						// System.out.println("Bau:" + ((Chest)
						// atual).getSlot(Warehouse.numExchangeChest).getIdentity());
						Warehouse.resetWareHouse();
					}
				}
			} else if (atual instanceof Anotacao) {
				if (Entity.isColidding(this, atual)) {

					return;
				}
			} else if (typeIsNpc(atual)) {
				collisionNpc(atual);
			} else if (atual instanceof Placa) {
				collisionPlaca(atual);
			} else if (atual instanceof Vaso) {
				collisionVaso(atual);
			} else if (atual instanceof Livro) {
				// System.out.println("i: " + i);
				collisionLivro(atual);
			} else if (atual instanceof eventBlock) {
				collisionEventBlock(atual);
			} else if (atual instanceof Poco) {
				collisionPoco(atual);
			} else if (atual instanceof Alavanca) {
				collisionAlavanca(atual);
			}
		}
	}

	public static boolean typeIsNpc(Entity atual) {
		if (atual instanceof Princesa || atual instanceof Esqueleto)
			return true;
		else
			return false;
	}

	public void collisionAlavanca(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima			
			nextisAlavancaUp = true;
			return;
		}
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			nextisAlavancaDown = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) { // Direita
			if (dir == RIGHT_DIR) {				
				((Alavanca) atual).setTryAnimation(true);
				tryActiveEvent = true;
			}
			nextisAlavancaRight = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) { // Esquerda
			nextisAlavancaLeft = true;
			return;
		}
	}

	public void collisionPoco(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				((Poco) atual).setTryEventActivePoco(true);
				((Poco) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisPoco = true;
			return;
		}
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			if (dir == DOWN_DIR) {
				((Poco) atual).setTryEventActivePoco(true);
				((Poco) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisPoco = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) { // Direita
			if (dir == RIGHT_DIR) {
				((Poco) atual).setTryEventActivePoco(true);
				((Poco) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisPoco = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) { // Esquerda
			if (dir == LEFT_DIR) {
				((Poco) atual).setTryEventActivePoco(true);
				((Poco) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisPoco = true;
			return;
		}
	}

	public void collisionEventBlock(Entity atual) {
		if (Entity.isColidding(this, atual)) {
			if(!((eventBlock) atual).isUsed()) {
				((eventBlock) atual).setTryActive(true);
				((eventBlock) atual).setChoose(true);
				return;
			}		
		}
	}

	public void collisionNpc(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (atual instanceof Princesa) {
				if (((Princesa) atual).isExistEventPrincesa()) {
					if (UP_DIR == dir) {
						((Princesa) atual).setTryEventActivePrincesa(true);
						tryActiveEvent = true;
						Game.uiNpc = true;
					}
				}
				((Princesa) atual).setChoose(true);
			}
			if (atual instanceof Esqueleto) {
				if (((Esqueleto) atual).isExistEventEsqueleto()) {
					if (UP_DIR == dir) {
						((Esqueleto) atual).setTryEventActiveEsqueleto(true);
						tryActiveEvent = true;
						Game.uiNpc = true;
					}
				}
				((Esqueleto) atual).setChoose(true);
			}
			nextisNpcUp = true;
		}
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) // Baixo
			nextisNpcDown = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) // Direita
			nextisNpcRight = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) // Esquerda
			nextisNpcLeft = true;
	}

	public void collisionDoor(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				((Door) atual).setTryAnimation(true);
				tryActiveEvent = true;
			}
			if (!((Door) atual).getAnimation() && ((Door) atual).getTryAnimation())
				Game.uiDoor = true;
			if (!((Door) atual).getOpenDoor()) {
				nextisDoorUp = true;

			} else {
				nextisDoorUp = false;
				// ((Door) atual).setChoose(false);
			}
			((Door) atual).setChoose(true);
			return;
		} else
			((Door) atual).setTryAnimation(false);
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			if (dir == DOWN_DIR) {
				((Door) atual).setTryAnimation(true);
				tryActiveEvent = true;
			}
			if (!((Door) atual).getAnimation() && ((Door) atual).getTryAnimation())
				Game.uiDoor = true;
			if (!((Door) atual).getOpenDoor()) {
				nextisDoorDown = true;
			} else {
				nextisDoorDown = false;
				// ((Door) atual).setChoose(false);
			}
			((Door) atual).setChoose(true);
			return;
		} else
			((Door) atual).setTryAnimation(false);
	}

	public void collisionChest(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				((Chest) atual).setTryAnimation(true);
				tryActiveEvent = true;
			}
			if (!((Chest) atual).isAnimation() && ((Chest) atual).isTryAnimation())
				Game.uiChest = true;
			if (!((Chest) atual).isOpenChest())
				Inventario.visible = true;
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

	public void collisionPlaca(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				((Placa) atual).setTryEventActivePlaca(true);
				Game.uiPlaca = true;
				tryActiveEvent = true;
			}
			((Placa) atual).setChoose(true);
			nextisPlacaUp = true;
			return;
		}
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) // Baixo
			nextisPlacaDown = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) // Direita
			nextisPlacaRight = true;
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) // Esquerda
			nextisPlacaLeft = true;
	}

	public void collisionVaso(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				((Vaso) atual).setTryEventActiveVaso(true);
				((Vaso) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisVaso = true;
			return;
		}
		if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			if (dir == DOWN_DIR) {
				((Vaso) atual).setTryEventActiveVaso(true);
				((Vaso) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisVaso = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) { // Direita
			if (dir == RIGHT_DIR) {
				((Vaso) atual).setTryEventActiveVaso(true);
				((Vaso) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisVaso = true;
			return;
		}
		if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) { // Esquerda
			if (dir == LEFT_DIR) {
				((Vaso) atual).setTryEventActiveVaso(true);
				((Vaso) atual).setChoose(true);
				tryActiveEvent = true;
			}
			nextisVaso = true;
			return;
		}
	}

	public void collisionLivro(Entity atual) {
		if (atual.getY() - this.getY() == -Tile.SIZE && atual.getX() - this.getX() == 0) { // Cima
			if (dir == UP_DIR) {
				if (((Livro) atual).isExistEventLivro()) {
					// System.out.println("aaa");
					((Livro) atual).setTryEventActiveLivro(true);
					((Livro) atual).setChoose(true);
					tryActiveEvent = true;
				}
			}
			nextisLivro = true;
			return;
		} else if (atual.getY() - this.getY() == Tile.SIZE && atual.getX() - this.getX() == 0) { // Baixo
			if (dir == DOWN_DIR) {
				if (((Livro) atual).isExistEventLivro()) {
					((Livro) atual).setTryEventActiveLivro(true);
					((Livro) atual).setChoose(true);
					tryActiveEvent = true;
				}
			}
			nextisLivro = true;
			return;
		} else if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == Tile.SIZE) { // Direita
			if (dir == RIGHT_DIR) {
				if (((Livro) atual).isExistEventLivro()) {
					((Livro) atual).setTryEventActiveLivro(true);
					((Livro) atual).setChoose(true);
					tryActiveEvent = true;
				}
			}
			nextisLivro = true;
			return;
		} else if (atual.getY() - this.getY() == 0 && atual.getX() - this.getX() == -Tile.SIZE) { // Esquerda
			if (dir == LEFT_DIR) {
				if (((Livro) atual).isExistEventLivro()) {
					((Livro) atual).setTryEventActiveLivro(true);
					((Livro) atual).setChoose(true);
					tryActiveEvent = true;
				}
			}
			nextisLivro = true;
			return;
		}
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

	public void correctCollisionGeneral() {
		correctCollisionWithOthers();
		correctCollisionDoor();
		correctCollisionNpc();
		correctCollisionChest();
		correctCollisionPlaca();
		correctCollisionVaso();
		correctCollisionLivro();
	}

	public boolean existTrueNext() {
		if (nextisDoorDown || nextisDoorUp || nextisNpcUp || nextisNpcDown || nextisNpcLeft || nextisNpcRight
				|| nextisPlacaRight || nextisPlacaLeft || nextisPlacaUp || nextisPlacaDown || nextisVaso || nextisLivro
				|| tryActiveEvent || nextisPoco || nextisAlavancaDown || nextisAlavancaUp || nextisAlavancaLeft
				|| nextisAlavancaRight)
			return true;
		else
			return false;
	}

	public void correctCollisionWithOthers() {
		if (existTrueNext()) {
			tryActiveEvent = false;
			for (Entity atual : Game.entities) {
				if (atual instanceof Door) {
					((Door) atual).setChoose(false);
				}
				if (atual instanceof Princesa) {
					((Princesa) atual).setTryEventActivePrincesa(false);
					((Princesa) atual).setChoose(false);
				}
				if (atual instanceof Esqueleto) {
					((Esqueleto) atual).setTryEventActiveEsqueleto(false);
					((Esqueleto) atual).setChoose(false);
				}
				if (atual instanceof Placa) {
					((Placa) atual).setTryEventActivePlaca(false);
					((Placa) atual).setChoose(false);
				}
				if (atual instanceof Vaso) {
					((Vaso) atual).setTryEventActiveVaso(false);
				}
				if (atual instanceof Livro) {
					((Livro) atual).setTryEventActiveLivro(false);
					((Livro) atual).setChoose(false);
					((Livro) atual).getAnotacaoDialogue().setExit(false);
				}
				if (atual instanceof eventBlock) {
					((eventBlock) atual).setActive(false);
					((eventBlock) atual).setTryActive(false);
					((eventBlock) atual).setChoose(false);
				}
				if (atual instanceof Poco) {
					((Poco) atual).setChoose(false);
					((Poco) atual).setEventActivePoco(false);
					((Poco) atual).setTryEventActivePoco(false);
				}
				if(atual instanceof Alavanca) {
					((Alavanca) atual).setTryAnimation(false);
					((Alavanca) atual).setAnimation(false);
				}
			}
		}
	}

	public void correctCollisionPlaca() {
		if (nextisPlacaUp)
			nextisPlacaUp = false;
		if (nextisPlacaDown)
			nextisPlacaDown = false;
		if (nextisPlacaLeft)
			nextisPlacaLeft = false;
		if (nextisPlacaRight)
			nextisPlacaRight = false;
		Game.uiPlaca = false;
	}

	public void correctCollisionDoor() {
		if (nextisDoorDown)
			nextisDoorDown = false;
		if (nextisDoorUp)
			nextisDoorUp = false;
		Game.uiDoor = false;
	}
	
	public void correctCollisionAlavanca() {
		if (nextisAlavancaDown)
			nextisAlavancaDown = false;
		if (nextisAlavancaUp)
			nextisAlavancaUp = false;
		if(nextisAlavancaLeft)
			nextisAlavancaLeft = false;
		if(nextisAlavancaRight)
			nextisAlavancaRight = false;
		Game.uiAlavanca = false;
	}

	public void correctCollisionVaso() {
		if (nextisVaso)
			nextisVaso = false;
	}

	public void correctCollisionPoco() {
		if (nextisPoco)
			nextisPoco = false;
	}

	public void correctCollisionLivro() {
		if (nextisLivro)
			nextisLivro = false;
		Game.uiLivro = false;
	}

	public void correctCollisionNpc() {
		if (nextisNpcUp)
			nextisNpcUp = false;
		if (nextisNpcDown)
			nextisNpcDown = false;
		if (nextisNpcLeft)
			nextisNpcLeft = false;
		if (nextisNpcRight)
			nextisNpcRight = false;
		Game.uiNpc = false;
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

	public boolean isnextisNpcUp() {
		return nextisNpcUp;
	}

	public void setNextisNpctUp(boolean nextisNpctUp) {
		this.nextisNpcUp = nextisNpctUp;
	}

	public boolean isNextisNpcDown() {
		return nextisNpcDown;
	}

	public void setNextisNpcDown(boolean nextisNpcDown) {
		this.nextisNpcDown = nextisNpcDown;
	}

	public boolean isNextisNpcLeft() {
		return nextisNpcLeft;
	}

	public void setNextisNpcLeft(boolean nextisNpcLeft) {
		this.nextisNpcLeft = nextisNpcLeft;
	}

	public boolean isNextisNpcRight() {
		return nextisNpcRight;
	}

	public void setNextisNpcRight(boolean nextisNpcRight) {
		this.nextisNpcRight = nextisNpcRight;
	}

	public boolean isNextisPlacaUp() {
		return nextisPlacaUp;
	}

	public void setNextisPlacaUp(boolean nextisPlacaUp) {
		this.nextisPlacaUp = nextisPlacaUp;
	}

	public boolean isNextisPlacaDown() {
		return nextisPlacaDown;
	}

	public void setNextisPlacaDown(boolean nextisPlacaDown) {
		this.nextisPlacaDown = nextisPlacaDown;
	}

	public boolean isNextisPlacaLeft() {
		return nextisPlacaLeft;
	}

	public void setNextisPlacaLeft(boolean nextisPlacaLeft) {
		this.nextisPlacaLeft = nextisPlacaLeft;
	}

	public boolean isNextisPlacaRight() {
		return nextisPlacaRight;
	}

	public void setNextisPlacaRight(boolean nextisPlacaRight) {
		this.nextisPlacaRight = nextisPlacaRight;
	}

	public boolean isNextisVaso() {
		return nextisVaso;
	}

	public void setNextisVaso(boolean nextisVaso) {
		this.nextisVaso = nextisVaso;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public boolean isNextisLivro() {
		return nextisLivro;
	}

	public void setNextisLivro(boolean nextisLivro) {
		this.nextisLivro = nextisLivro;
	}

	public boolean isNextisAlavancaUp() {
		return nextisAlavancaUp;
	}

	public void setNextisAlavancaUp(boolean nextisAlavancaUp) {
		this.nextisAlavancaUp = nextisAlavancaUp;
	}

	public boolean isNextisAlavancaLeft() {
		return nextisAlavancaLeft;
	}

	public void setNextisAlavancaLeft(boolean nextisAlavancaLeft) {
		this.nextisAlavancaLeft = nextisAlavancaLeft;
	}

	public boolean isNextisAlavancaDown() {
		return nextisAlavancaDown;
	}

	public void setNextisAlavancaDown(boolean nextisAlavancaDown) {
		this.nextisAlavancaDown = nextisAlavancaDown;
	}

	public boolean isNextisAlavancaRight() {
		return nextisAlavancaRight;
	}

	public void setNextisAlavancaRight(boolean nextisAlavancaRight) {
		this.nextisAlavancaRight = nextisAlavancaRight;
	}

}
