package com.bakerystudios.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.World;
import com.bakerystudios.engine.graphics.Spritesheet;
import com.bakerystudios.entities.Entity;
import com.bakerystudios.entities.Player;
import com.bakerystudios.game.input.Input;
import com.bakerystudios.game.input.MenuInput;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.GraphicUserInterface;
import com.bakerystudios.sound.AudioManager;
import com.bakerystudios.engine.Renderable;

public class Game implements Runnable, Renderable, Updateble {

	private boolean isRunning;

	private Thread thread;
	private Screen screen;
	private List<Input> inputs = new ArrayList<>();

	public static Random rand;

	private BufferedImage frame;
	private GraphicUserInterface gui;
	private Spritesheet spritesheet;
	private AudioManager audio;

	public static World world;
	public static List<Entity> entities;

	public Game() {
		// Object instantiation
		inputs = new ArrayList<>();
		inputs.add(new MenuInput());
		screen = new Screen(inputs);
		rand = new Random();
		gui = new GraphicUserInterface();
		frame = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		spritesheet = new Spritesheet("/spritesheet.png");
		audio = new AudioManager();

		entities = new ArrayList<Entity>();
		// exemplo de adição de entidade
		world = new World("/level1.png");

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
		screen.update();
		audio.update();

		if (GameState.state == GameState.PLAYING) {
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.update();
			}
		} else if (GameState.state == GameState.OVER) {

		}
	}

	private void nonPixelatedRender(Graphics g) {
		gui.render(g);

		if (GameState.state == GameState.PLAYING) {
			g.setColor(Color.GREEN);
			g.fillOval(60, 60, 50, 50);

		} else if (GameState.state == GameState.OVER) {

		}
	}

	private void pixelatedRender(Graphics g) {

		if (GameState.state == GameState.PLAYING) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, 50, 50);

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
		this.spritesheet = spritesheet;
	}
}
