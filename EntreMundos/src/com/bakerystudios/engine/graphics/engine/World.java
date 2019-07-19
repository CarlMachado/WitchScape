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
import com.bakerystudios.entities.Chest;
import com.bakerystudios.entities.Door;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Skeleton;
import com.bakerystudios.entities.Board;
import com.bakerystudios.entities.Princess;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.teleports.Teleport;

public class World {
	
	private final int FLOOR = 0xFF000000;
	private final int WALL = 0xFFFFFFFF;
	private final int PLAYER = 0xFF5BABE1;
	private final int DOOR_FIRST = 0xFF7F3300;
	private final int DOOR_SECOND = 0xFF682900;
	private final int DOOR_THIRD = 0xFFD85300;
	private final int CHEST = 0xFF0026FF;
	private final int PRINCESS = 0xFFFF7F7F;
	private final int SKELETON = 0xFF808080;
	private final int BOARD = 0xFFFF6A00;
	private final int TELEPORT = 0xFF666FFF;
	
	private BufferedImage[] princessSprites;
	private BufferedImage[] SkeletonSprites;
	private List<String>[] boardDialogue_FIRST;

	public static BufferedImage map;
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String map_path, String colmap_path) {
		try {
			loadInfo();
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
					if(pixelAtual == PLAYER) {
						Game.player.setX(xx * Tile.SIZE);
						Game.player.setY(yy * Tile.SIZE);
						//System.out.println("x: " + xx * Tile.SIZE);
						//System.out.println("y: " + yy * Tile.SIZE);
					}
					if(pixelAtual == DOOR_FIRST) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "chave1", true));
					}
					if(pixelAtual == DOOR_SECOND) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, "chave2", true));
					}
					if(pixelAtual == DOOR_THIRD) {
						Game.entities.add(new Door(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, null, false));
					}
					if(pixelAtual == CHEST) {
						Game.entities.add(new Chest(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null));
					}
					if(pixelAtual == PRINCESS) {
						Game.entities.add(new Princess(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, princessSprites, true));
					}
					if(pixelAtual == SKELETON) {
						Game.entities.add(new Skeleton(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null, SkeletonSprites, true));
					}
					if(pixelAtual == BOARD) {
						Game.entities.add(new Board(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, Game.wall.getSprite(48, 128, Tile.SIZE, Tile.SIZE), boardDialogue_FIRST));
					}
					if(pixelAtual == TELEPORT) {
						Game.entities.add(new Teleport(xx * Tile.SIZE, yy * Tile.SIZE, Tile.SIZE, Tile.SIZE, null));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0;
		for(Entity e : Game.entities) {
			if(e instanceof Teleport) {
				if(i == 0) {
					((Teleport) e).setDestiny(tiles[1579]);
					i++;
				} else {
					((Teleport) e).setDestiny(tiles[987]);
				}
			}
		}
	}

	public static boolean isFree(int xnext, int ynext) {

		int x1 = xnext / Tile.SIZE;
		int y1 = ynext / Tile.SIZE;

		int x2 = (xnext + Tile.SIZE - 1) / Tile.SIZE;
		int y2 = ynext / Tile.SIZE;

		int x3 = xnext / Tile.SIZE;
		int y3 = (ynext + Tile.SIZE - 1) / Tile.SIZE;

		int x4 = (xnext + Tile.SIZE - 1) / Tile.SIZE;
		int y4 = (ynext + Tile.SIZE - 1) / Tile.SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}

	public void loadInfo() {
		princessSprites = new BufferedImage[1];
		for(int i = 0; i < princessSprites.length; i++)
			princessSprites[i] = Game.characters.getSprite(96, 0, Tile.SIZE, Tile.SIZE);
		
		SkeletonSprites = new BufferedImage[1];
		for(int i = 0; i < SkeletonSprites.length; i++)
			SkeletonSprites[i] = Game.characters.getSprite(144, 0, Tile.SIZE, Tile.SIZE);
		
		
		boardDialogue_FIRST = new ArrayList[2];
		for(int i = 0; i < boardDialogue_FIRST.length; i++)
			boardDialogue_FIRST[i] = new ArrayList<String>();
		boardDialogue_FIRST[0].add("Plaquinha bonita, eu te amo por funcionar");
		boardDialogue_FIRST[0].add("Testizin beautifil pa ver que funciona");		
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

}