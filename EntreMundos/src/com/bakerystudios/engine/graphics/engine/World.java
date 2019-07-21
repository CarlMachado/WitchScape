package com.bakerystudios.engine.graphics.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.tiles.FloorTile;
import com.bakerystudios.engine.graphics.tiles.WallTile;
import com.bakerystudios.entities.Alavanca;
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Esqueleto;
import com.bakerystudios.entities.Livro;
import com.bakerystudios.entities.Placa;
import com.bakerystudios.entities.Poco;
import com.bakerystudios.entities.Princesa;
import com.bakerystudios.entities.Vaso;
import com.bakerystudios.events.eventBlock;
import com.bakerystudios.game.Game;
import com.bakerystudios.inventario.Slot;

public class World {

	private final int FLOOR = 0xFF000000;
	private final int WALL = 0xFFFFFFFF;
	private final int PLAYER = 0xFF5BABE1;
	private final int DOOR_FIRST = 0xFF7F3300;
	private final int DOOR_SECOND = 0xFF682900;
	private final int DOOR_THIRD = 0xFFD85300;
	private final int DOOR_FORTY = 0xFFF95F00;
	private final int CHEST = 0xFF0026FF;
	private final int CHEST_SECOND = 0xFF001172;
	private final int CHEST_THIRD = 0xFF000628;
	private final int PRINCESA = 0xFFFF7F7F;
	private final int ESQUELETO = 0xFF808080;
	private final int PLACA = 0XFFFF6A00;
	private final int VASO = 0XFF441B00;
	private final int TILE_EVENT_FIRST = 0XFFFF006E;

	private BufferedImage[] spritesPrincesa;
	private BufferedImage[] spritesEsqueleto;
	private BufferedImage[] spritesPorta;
	private BufferedImage[] spritesGrade;
	private BufferedImage[] spritesLivro;
	private BufferedImage[] spritesAlavanca;
	private List<String>[] placaDialogue_FIRST;
	private List<String>[] placaDialogue_SECOND;
	private Slot[] slot_FIRST;
	private Slot[] slot_SECOND;
	private Slot[] slot_THIRD;
	private Slot[] slot_FORTY;

	public BufferedImage map;
	public Tile[] tiles;
	public int WIDTH, HEIGHT;

	public World(String map_path, String colmap_path) {
		try {
			loadInfo();
			loadEntities();
			BufferedImage col_map = ImageIO.read(getClass().getResource(colmap_path));
			map = ImageIO.read(getClass().getResource(map_path));
			int[] pixels = new int[col_map.getWidth() * col_map.getHeight()];
			WIDTH = col_map.getWidth();
			HEIGHT = col_map.getHeight();
			tiles = new Tile[col_map.getWidth() * col_map.getHeight()];
			col_map.getRGB(0, 0, col_map.getWidth(), col_map.getHeight(), pixels, 0, col_map.getWidth());
			for (int xx = 0; xx < col_map.getWidth(); xx++) {
				for (int yy = 0; yy < col_map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * col_map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * Tile.SIZE, yy * Tile.SIZE, Tile.TILE_FLOOR);

					if (pixelAtual == FLOOR) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * Tile.SIZE, yy * Tile.SIZE, Tile.TILE_FLOOR);
					}
					if (pixelAtual == WALL) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * Tile.SIZE, yy * Tile.SIZE, Tile.TILE_WALL);
					}
					if (pixelAtual == PLAYER) {
						Game.player.setX(xx * Tile.SIZE);
						Game.player.setY(yy * Tile.SIZE);
					}
					if (pixelAtual == DOOR_FIRST) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "chave1",
								true, false, "", spritesPorta, spritesPorta.length));
					}
					if (pixelAtual == DOOR_SECOND) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "", true,
								false, "", spritesPorta, spritesPorta.length));
					}
					if (pixelAtual == DOOR_THIRD) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "",
								false, false, "", spritesPorta, spritesPorta.length));
					}
					if (pixelAtual == DOOR_FORTY) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "chave1",
								true, true, "Luva de Couro", spritesGrade, spritesGrade.length));
					}
					if (pixelAtual == CHEST) {
						Game.entities
								.add(new Chest(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, slot_FIRST));
					}
					if (pixelAtual == CHEST_SECOND) {
						Game.entities.add(
								new Chest(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, slot_SECOND));
					}
					if (pixelAtual == CHEST_THIRD) {
						Game.entities
								.add(new Chest(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, slot_THIRD));
					}
					if (pixelAtual == PRINCESA) {
						Game.entities.add(new Princesa(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null,
								spritesPrincesa, true));
					}
					if (pixelAtual == ESQUELETO) {
						Game.entities.add(new Esqueleto(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null,
								spritesEsqueleto, true));
					}
					if (pixelAtual == PLACA) {
						Game.entities.add(new Placa(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE,
								Game.wall.getSprite(48, 128, Tile.SIZE, Tile.SIZE), placaDialogue_FIRST));
					}
					if (pixelAtual == TILE_EVENT_FIRST) {
						Game.entities.add(new eventBlock(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null,
								true, "chave2", "Chave Porão", Game.doors.getSprite(16, 160, Tile.SIZE, Tile.SIZE)));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isFree(int xnext, int ynext) {

		int x1 = xnext / Tile.SIZE;
		int y1 = ynext / Tile.SIZE;

		int x2 = (xnext + Tile.SIZE - 1) / Tile.SIZE;
		int y2 = ynext / Tile.SIZE;

		int x3 = xnext / Tile.SIZE;
		int y3 = (ynext + Tile.SIZE - 1) / Tile.SIZE;

		int x4 = (xnext + Tile.SIZE - 1) / Tile.SIZE;
		int y4 = (ynext + Tile.SIZE - 1) / Tile.SIZE;

		return !((tiles[x1 + (y1 * WIDTH)] instanceof WallTile) || (tiles[x2 + (y2 * WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * WIDTH)] instanceof WallTile) || (tiles[x4 + (y4 * WIDTH)] instanceof WallTile));
	}

	@SuppressWarnings("unchecked")
	public void loadEntities() {
		placaDialogue_SECOND = new ArrayList[2];
		for (int i = 0; i < placaDialogue_SECOND.length; i++)
			placaDialogue_SECOND[i] = new ArrayList<String>();
		placaDialogue_SECOND[0].add("Livro que eu amo");
		placaDialogue_SECOND[0].add("funcione plz");
		placaDialogue_SECOND[1].add("piroca vai");
		placaDialogue_SECOND[1].add("teste testante testador testmaster");

		// Outros
		Game.entities.add(new Livro(368, 432, Tile.SIZE, Tile.SIZE, null, spritesLivro, true, placaDialogue_SECOND));
		Game.entities.add(new Vaso(368, 592, Tile.SIZE, Tile.SIZE, null, "chave1", "Chave da Porta",
				Game.doors.getSprite(0, 160, Tile.SIZE, Tile.SIZE)));
		Game.entities.add(new Poco(400, 368, Tile.SIZE, Tile.SIZE, null, "chave5", "Chave da Alavanca",
				Game.doors.getSprite(48, 160, Tile.SIZE, Tile.SIZE)));
		Game.entities.add(new Alavanca(480, 192, Tile.SIZE, Tile.SIZE, null, true, "chave5", spritesAlavanca));
		// PORTAS
		Game.entities.add(new Door(352, 576, Tile.SIZE, Tile.SIZE, null, "chave1", true, false, "", spritesPorta,
				spritesPorta.length));		
		Game.entities.add(new Door(368, 464, Tile.SIZE, Tile.SIZE, null, "chave2", true, false, "", spritesPorta,
				spritesPorta.length));
		Game.entities.add(new Door(288, 464, Tile.SIZE, Tile.SIZE, null, "chave3", true, false, "", spritesPorta,
				spritesPorta.length));
		Game.entities.add(new Door(1216, 256, Tile.SIZE, Tile.SIZE, null, "chave4", true, true, "Luva de Couro",
				spritesGrade, spritesGrade.length));
		Game.entities.add(
				new Door(528, 176, Tile.SIZE, Tile.SIZE, null, "", true, true, "switch", spritesPorta, spritesPorta.length));
		// BAU
		Game.entities.add(new Chest(1168, 272, Tile.SIZE, Tile.SIZE, null, slot_SECOND));
		Game.entities.add(new Chest(1232, 208, Tile.SIZE, Tile.SIZE, null, slot_THIRD));
		Game.entities.add(new Chest(528, 96, Tile.SIZE, Tile.SIZE, null, slot_FORTY));
		// TILE EVENT
		Game.entities.add(new eventBlock(1360, 624, Tile.SIZE, Tile.SIZE, null, true, "chave2", "Chave da Porta",
				Game.doors.getSprite(64, 160, Tile.SIZE, Tile.SIZE)));
		Game.entities.add(new eventBlock(272, 480, Tile.SIZE, Tile.SIZE, null, true, "chave4", "Chave do Porão",
				Game.doors.getSprite(16, 160, Tile.SIZE, Tile.SIZE)));
	}

	public void loadInfo() {

		slot_FIRST = new Slot[9];
		for (int i = 0; i < slot_FIRST.length; i++)
			slot_FIRST[i] = new Slot();

		slot_SECOND = new Slot[9];
		for (int i = 0; i < slot_SECOND.length; i++)
			slot_SECOND[i] = new Slot();

		slot_SECOND[3].setAmount(1);
		slot_SECOND[3].setIdentity("Luva de Couro");
		slot_SECOND[3].setShortName("Luva de Couro");
		slot_SECOND[3].setImageSlot(Game.doors.getSprite(0, 144, Tile.SIZE, Tile.SIZE));

		slot_THIRD = new Slot[9];
		for (int i = 0; i < slot_THIRD.length; i++)
			slot_THIRD[i] = new Slot();

		slot_THIRD[5].setAmount(1);
		slot_THIRD[5].setIdentity("Diario");
		slot_THIRD[5].setShortName("Diario");
		slot_THIRD[5].setImageSlot(Game.doors.getSprite(32, 128, Tile.SIZE, Tile.SIZE));
		
		slot_THIRD[1].setAmount(1);
		slot_THIRD[1].setIdentity("chave3");
		slot_THIRD[1].setShortName("Chave da Porta");
		slot_THIRD[1].setImageSlot(Game.doors.getSprite(32, 160, Tile.SIZE, Tile.SIZE));

		slot_FORTY = new Slot[9];
		for (int i = 0; i < slot_FORTY.length; i++)
			slot_FORTY[i] = new Slot();
		
		slot_FORTY[6].setAmount(1);
		slot_FORTY[6].setIdentity("Bola");
		slot_FORTY[6].setShortName("Bola");
		slot_FORTY[6].setImageSlot(Game.doors.getSprite(32, 128, Tile.SIZE, Tile.SIZE)); // COORDENADAS DA BOLA
		
		spritesLivro = new BufferedImage[3];
		for (int i = 0; i < spritesLivro.length; i++)
			spritesLivro[i] = Game.doors.getSprite(16 * i, 128, Tile.SIZE, Tile.SIZE);

		spritesPrincesa = new BufferedImage[1];
		for (int i = 0; i < spritesPrincesa.length; i++)
			spritesPrincesa[i] = Game.characters.getSprite(96, 0, Tile.SIZE, Tile.SIZE);

		spritesEsqueleto = new BufferedImage[1];
		for (int i = 0; i < spritesEsqueleto.length; i++)
			spritesEsqueleto[i] = Game.characters.getSprite(144, 0, Tile.SIZE, Tile.SIZE);

		spritesPorta = new BufferedImage[4];
		for (int i = 0; i < spritesPorta.length; i++)
			spritesPorta[i] = Game.doors.getSprite(0, 16 * i, Tile.SIZE, Tile.SIZE);

		spritesGrade = new BufferedImage[4];
		for (int i = 0; i < spritesGrade.length; i++)
			spritesGrade[i] = Game.doors.getSprite(64, 16 * i, Tile.SIZE, Tile.SIZE);
		
		spritesAlavanca= new BufferedImage[2];
		for (int i = 0; i < spritesAlavanca.length; i++)
			spritesAlavanca[i] = Game.doors.getSprite(48 + i * 16, 64, Tile.SIZE, Tile.SIZE);

		placaDialogue_FIRST = new ArrayList[1];
		for (int i = 0; i < placaDialogue_FIRST.length; i++)
			placaDialogue_FIRST[i] = new ArrayList<String>();
		placaDialogue_FIRST[0].add("Plaquinha bonita, eu te amo por funcionar");
		placaDialogue_FIRST[0].add("Testizin beautifil pa ver que funciona");

	}

	public void render(Graphics g) {
//		int xstart = Camera.x >> 4;
//		int ystart = Camera.y >> 4;
//
//		int xfinal = xstart + (Screen.WIDTH >> 4);
//		int yfinal = ystart + (Screen.HEIGHT >> 4);
//		
//		for (int xx = xstart; xx <= xfinal; xx++) {
//			for (int yy = ystart; yy <= yfinal; yy++) {
//				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
//					continue;
//				if(xx + (yy * WIDTH) < tiles.length) {
//					Tile tile = tiles[xx + (yy * WIDTH)];
//					tile.render(g);
//				}
//			}
//		}
		Tile tile = tiles[0];
		g.drawImage(map, tile.getX() - Camera.x, tile.getY() - Camera.y, null);
	}

	public int getVASO() {
		return VASO;
	}

	public BufferedImage[] getSpritesPorta() {
		return spritesPorta;
	}

	public void setSpritesPorta(BufferedImage[] spritesPorta) {
		this.spritesPorta = spritesPorta;
	}

	public BufferedImage[] getSpritesGrade() {
		return spritesGrade;
	}

	public void setSpritesGrade(BufferedImage[] spritesGrade) {
		this.spritesGrade = spritesGrade;
	}

	public int getCHEST_SECOND() {
		return CHEST_SECOND;
	}

	public Slot[] getSlot_FIRST() {
		return slot_FIRST;
	}

	public void setSlot_FIRST(Slot[] slot_FIRST) {
		this.slot_FIRST = slot_FIRST;
	}

	public Slot[] getSlot_SECOND() {
		return slot_SECOND;
	}

	public void setSlot_SECOND(Slot[] slot_SECOND) {
		this.slot_SECOND = slot_SECOND;
	}

	public int getCHEST_THIRD() {
		return CHEST_THIRD;
	}

	public Slot[] getSlot_THIRD() {
		return slot_THIRD;
	}

	public void setSlot_THIRD(Slot[] slot_THIRD) {
		this.slot_THIRD = slot_THIRD;
	}

	public int getTILE_EVENT() {
		return TILE_EVENT_FIRST;
	}

	public BufferedImage[] getSpritesAlavanca() {
		return spritesAlavanca;
	}

	public void setSpritesAlavanca(BufferedImage[] spritesAlavanca) {
		this.spritesAlavanca = spritesAlavanca;
	}

}