package com.bakerystudios.engine.graphics.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.tiles.FloorTile;
import com.bakerystudios.engine.graphics.tiles.WallTile;
import com.bakerystudios.entities.Door;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public class World {
	
	private final int FLOOR = 0xFF000000;
	private final int WALL = 0xFFFFFFFF;
	private final int DOOR = 0xFF7F3300;
	private final int PLAYER = 0xFF5BABE1;

	public static BufferedImage map;
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World(String map_path, String colmap_path) {
		try {
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
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
					
					if (pixelAtual == FLOOR) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_FLOOR);
					}
					if (pixelAtual == WALL) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL);
					}
					if(pixelAtual == DOOR) {
						Game.entities.add(new Door(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE, null));
					}
					if(pixelAtual == PLAYER) {
						System.out.println("player");
						Game.player.setX(xx * TILE_SIZE);
						Game.player.setY(yy * TILE_SIZE);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFree(int xnext, int ynext) {

		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;

		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;

		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Screen.WIDTH >> 4);
		int yfinal = ystart + (Screen.HEIGHT >> 4);
		
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				if(xx + (yy * WIDTH) < tiles.length) {
					Tile tile = tiles[xx + (yy * WIDTH)];
					tile.render(g);
				}
			}
		}
		Tile tile = tiles[0];
		g.drawImage(map, tile.getX() - Camera.x, tile.getY() - Camera.y, null);
	}

}