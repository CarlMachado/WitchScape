package com.bakerystudios.game.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import com.bakerystudios.engine.OptionManager;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.game.input.Input;

public class Screen extends Canvas implements Updateble {

	// UNICA CLASSE QUE NÃO UTILIZA DE TILE.SIZE POR MOTIVOS DE INSTANCIAÇÃO
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = -8027725672170867390L;
	
	public static int SCALE = 5;
	public static int WIDTH = 1280;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE_WIDTH = WIDTH * SCALE;
	public static int SCALE_HEIGHT = HEIGHT * SCALE;

	public static JFrame frame;
	
	private OptionManager resolutions;
	
	public Screen(List<Input> inputs) {
		for(Input input : inputs) {
			addKeyListener(input);
			addMouseListener(input);
		}
		initResolutions();
		initScreenSize();
		setPreferredSize(new Dimension(SCALE_WIDTH, SCALE_HEIGHT));
		frame = new JFrame("BKS Game Engine");
		frame.add(this);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
	}
	
	private void initResolutions() {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
		WIDTH = d.width / SCALE;
		HEIGHT = d.height / SCALE;
		resolutions = new OptionManager(3);
		resolutions.addOption(new ResolutionOption("800x600", 0, 800, 600));
		resolutions.addOption(new ResolutionOption("1280x720", 0, 1280, 720));
		resolutions.addOption(new ResolutionOption("MAXIMIZADO", 0, WIDTH, HEIGHT));
	}

	// pega a resolução do monitor para inicializar
	// a resolução do jogo
	private void initScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
		WIDTH = d.width / SCALE;
		HEIGHT = d.height / SCALE;
		SCALE_WIDTH = WIDTH * SCALE;
		SCALE_HEIGHT = HEIGHT * SCALE;
	}

	// atualiza a resolução da tela
	public void updateScreenSize(Dimension d) {
		WIDTH = d.width / SCALE;
		HEIGHT = d.height / SCALE;
		SCALE_WIDTH = WIDTH * SCALE;
		SCALE_HEIGHT = HEIGHT * SCALE;
	}

	// atualiza o tamanho do render do jogo de acordo com
	// o tamanho atual da tela
	@Override
	public void update() {
		WIDTH = (frame.getWidth() - 16) / SCALE;
		HEIGHT = (frame.getHeight() - 16) / SCALE;
		SCALE_WIDTH = WIDTH * SCALE;
		SCALE_HEIGHT = HEIGHT * SCALE;
	}
	
}
