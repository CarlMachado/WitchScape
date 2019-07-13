package com.bakerystudios.engine.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.engine.graphics.Tile;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World(String path) {
		// MAPA POR TILEMAP

		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					if (pixelAtual == 0xFF000000) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					} else if (pixelAtual == 0xFFFFFFFF) {
						// Parede
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					} else if (pixelAtual == 0xFF0026FF) {
						// Player
						Game.getPlayer().setX(xx * 16);
						Game.getPlayer().setY(yy * 16);
					} 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// MAPA RANDOM
		/*
		 * Game.player.setX(0); Game.player.setY(0); WIDTH = 100; HEIGHT = 100; tiles =
		 * new Tile[WIDTH * HEIGHT];
		 * 
		 * for(int xx = 0; xx < WIDTH; xx++) { for(int yy = 0; yy < HEIGHT;yy++) {
		 * tiles[xx+yy*WIDTH] = new WallTile(xx*16,yy*16,Tile.TILE_WALL); } }
		 * 
		 * int dir = 0; int xx = 0; int yy = 0; for(int i = 0; i < 200; i++) {
		 * tiles[xx+yy*WIDTH] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR); if(dir == 0)
		 * { // DIREITA if(xx < WIDTH) { xx++; } }else if(dir == 1) { // ESQUERDA if(xx
		 * > 0) { xx--; } }else if(dir == 2) { // BAIXO if(yy < HEIGHT) { yy++; } }else
		 * if(dir == 3) { // CIMA if(yy > 0) { yy--; } }
		 * 
		 * if(Game.rand.nextInt(100) < 30) { dir = Game.rand.nextInt(4); } }
		 */
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

	public static void restartGame(String level) {
		return;
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
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}

}