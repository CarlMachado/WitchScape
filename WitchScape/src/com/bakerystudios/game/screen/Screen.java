package com.bakerystudios.game.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import com.bakerystudios.game.input.Input;

public class Screen extends Canvas {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = -8027725672170867390L;
	
	public static int SCALE = 5;
	public static int WIDTH = 1280 / SCALE;
	public static int HEIGHT = (WIDTH / 16) * 9;
	public static int SCALE_WIDTH = WIDTH * SCALE;
	public static int SCALE_HEIGHT = HEIGHT * SCALE;

	public static JFrame frame;
	
	public Screen(List<Input> inputs) {
		for(Input input : inputs) {
			addKeyListener(input);
			addMouseListener(input);
		}
		//initScreenSize();
		setPreferredSize(new Dimension(SCALE_WIDTH, SCALE_HEIGHT));
		frame = new JFrame("Witch Scape");
		frame.add(this);
		frame.setResizable(false);
		//frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		//frame.setResizable(false);
	}

	// pega a resolução do monitor para inicializar
	// a resolução do jogo
	@SuppressWarnings("unused")
	private void initScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
		WIDTH = d.width / SCALE;
		HEIGHT = d.height / SCALE;
		SCALE_WIDTH = WIDTH * SCALE;
		SCALE_HEIGHT = HEIGHT * SCALE;
	}
	
}
