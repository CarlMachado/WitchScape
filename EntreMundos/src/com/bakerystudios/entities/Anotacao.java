package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.screen.Screen;

import java.awt.Font;

public class Anotacao extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean animation = false;

	private int currentAnimacao = 0;
	private int maxAnimacao = sprites.length; // sprites.length
	private int currentFrame = 0;
	private int maxFrame = 30;

	private boolean statusEventoAnotacao = false;
	private boolean visible = false;
	private boolean nextPagina = false;
	private boolean nextPaginaSelected = false;
	private boolean exitSelected = false;

	private List<String>[] linha;

	public Anotacao(int x, int y, int width, int height, BufferedImage sprite, boolean visible, List<String>[] linha) {
		super(x, y, width, height, sprite);
		this.visible = visible;
		this.linha = linha;
	}

	public void update() {
		if (animation) {
			currentFrame++;
			if (currentFrame >= maxFrame) {
				currentAnimacao++;
			}
			if (currentAnimacao >= maxAnimacao) {
				currentAnimacao = 0;
				animation = false;
				statusEventoAnotacao = true;
			}
		}
	}

	public void render(Graphics g) {
		if (visible) {
			g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if (statusEventoAnotacao)
				eventoAnotacao(g);
		}
	}

	public void eventoAnotacao(Graphics g) {
		boolean exibiu = false;
		int paginas = linha.length;
		// FALTA CRIAR JANELA POR TRÁS DO TEXTO
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.025)));

		for (int i = 0; i < paginas; i++) {
			int linhas = linha[i].size();
			while(!nextPaginaSelected) {
				if(!exibiu) {
					for(int j = 0; j < linhas; j++) {
						int fontHeight = g.getFontMetrics().getHeight();
						g.drawString(linha[i].get(i), (int) x, (int) y + i * fontHeight); // COORDENADAS A DEFINIR (x e y)
					}	
					exibiu = true;
				}
				if(linha[i + 1] != null) { 
					// EXISTE PRÓXIMA PAGINA, EXIBE BOTÃO DE PRÓXIMA PAGINA
				}
				if(exitSelected) // CLICOU ESC PARA SAIR DO EVENTO
					return;
			}
			if(exitSelected)
				return;
		}
		statusEventoAnotacao = false;
	}

	public boolean isNextPagina() {
		return nextPagina;
	}

	public void setNextPagina(boolean nextPagina) {
		this.nextPagina = nextPagina;
	}

}
