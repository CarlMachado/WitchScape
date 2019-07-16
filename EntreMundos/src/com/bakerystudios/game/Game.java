package com.bakerystudios.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.graphics.engine.Spritesheet;
import com.bakerystudios.engine.graphics.engine.Tile;
import com.bakerystudios.engine.graphics.engine.World;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Player;
import com.bakerystudios.game.input.Input;
import com.bakerystudios.game.input.MenuInput;
import com.bakerystudios.game.input.PlayerInput;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.GraphicUserInterface;
import com.bakerystudios.inventario.Inventario;
import com.bakerystudios.sound.AudioManager;

public class Game implements Runnable, Renderable, Updateble {

	private boolean isRunning;

	private Thread thread;
	private Screen screen;
	private List<Input> inputs = new ArrayList<>();

	public static Random rand;

	private BufferedImage frame;
	private GraphicUserInterface gui;

	private AudioManager audio;

	private static Player player;

	public static Spritesheet spritesheet;
	public static Spritesheet characters;
	public static Spritesheet doors;
	public static World world;
	public static List<Entity> entities;
	public static Inventario inventario;

	public static boolean uiDoor = false;
	public static boolean uiChest = false;

	public Game() {
		// Object instantiation
		inputs = new ArrayList<>();
		inputs.add(new MenuInput());
		inputs.add(new PlayerInput());
		screen = new Screen(inputs);
		rand = new Random();
		gui = new GraphicUserInterface();
		frame = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		spritesheet = new Spritesheet("/sprites/spritesheet.png");
		characters = new Spritesheet("/sprites/characters.png");
		doors = new Spritesheet("/sprites/doors.png");
		audio = new AudioManager();
		player = new Player(16, 192, Tile.SIZE, Tile.SIZE, null);
		inventario = new Inventario();

		entities = new ArrayList<Entity>();
		entities.add(player);
		world = new World("/levels/map1.png", "/levels/map1_collisionmap.png");
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		gui.update();
		audio.update();

		if (GameState.state == GameState.PLAYING) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.update();
			}
			inventario.update();
		} else if (GameState.state == GameState.OVER) {

		}
	}

	private void nonPixelatedRender(Graphics g) {
		gui.render(g);
		Game.inventario.render(g);

		if (GameState.state == GameState.PLAYING) {

		} else if (GameState.state == GameState.OVER) {

		}
	}

	private void pixelatedRender(Graphics g) {
		if (GameState.state == GameState.PLAYING) {

			world.render(g);
			for (Entity e : entities)
				e.render(g);
		} else if (GameState.state == GameState.OVER) {

		}
	}

	@Override
	public void render(Graphics g) {
		BufferStrategy bs = screen.getBufferStrategy();
		if (bs == null) {
			screen.createBufferStrategy(3);
			return;
		}

		g = frame.getGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);

		pixelatedRender(g);

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(frame, 0, 0, Screen.SCALE_WIDTH, Screen.SCALE_HEIGHT, null);

		nonPixelatedRender(g);

		bs.show();
	}

	@Override
	public void run() {
		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0;
		long lastTime = System.nanoTime();

		screen.requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				render(null);
				delta--;
			}
		}

		stop();
	}

	public Spritesheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(Spritesheet spritesheet) {
		Game.spritesheet = spritesheet;
	}

	public static Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		Game.player = player;
	}

}
