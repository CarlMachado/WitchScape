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
	private int maxAnimacao = 0; // sprites.length
	private int currentFrame = 0;
	private int maxFrame = 30;	
	
	private boolean visible = false;
	private boolean nextPagina = false;
	
	public static boolean statusEventoAnotacao = false;
	public static boolean nextPaginaSelected = false;
	public static boolean exitSelected = false;

	private List<String>[] linha;
	
	private int currentPagina = 0;
	private int paginas = 0;

	public Anotacao(int x, int y, int width, int height, BufferedImage sprite, boolean visible, List<String>[] linha) {
		super(x, y, width, height, sprite);
		this.visible = visible;
		this.linha = linha;
		this.setPaginas(linha.length);
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
		if(statusEventoAnotacao) {
			int paginas = linha.length;
			// FALTA CRIAR JANELA POR TRÁS DO TEXTO - CARLOS
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, (int) (Screen.SCALE_WIDTH * 0.025)));
			
			int linhas = linha[currentPagina].size();
			for(int j = 0; j < linhas; j++) {
				int fontHeight = g.getFontMetrics().getHeight();
				g.drawString(linha[currentPagina].get(j), (int) x, (int) y + j * fontHeight); // COORDENADAS A DEFINIR (x e y)
			}	
			if(currentPagina + 1 != paginas) { 
				// EXISTE PRÓXIMA PAGINA, EXIBE BOTÃO DE PRÓXIMA PAGINA - CARLOS
				if(nextPaginaSelected) {
					currentPagina++;
					nextPaginaSelected = false;
				}
			}
			
			if(exitSelected) { // CLICOU ESC PARA SAIR DO EVENTO
				statusEventoAnotacao = false;
				return;
			}			
		}
	}

	public boolean isNextPagina() {
		return nextPagina;
	}

	public void setNextPagina(boolean nextPagina) {
		this.nextPagina = nextPagina;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

}
