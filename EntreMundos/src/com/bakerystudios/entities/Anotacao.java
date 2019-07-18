package com.bakerystudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.bakerystudios.engine.Renderable;
import com.bakerystudios.engine.Updateble;
import com.bakerystudios.engine.camera.Camera;
import com.bakerystudios.game.Game;
import com.bakerystudios.game.screen.Screen;
import com.bakerystudios.gui.TextBox;

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
	
	private boolean statusEventoAnotacao = false;
	private boolean nextPaginaSelected = false;
	private boolean exitSelected = false;

	private List<String>[] linha;
	
	private int currentPagina = 1;
	private int paginas = 0;

	private boolean eventIsOver = false;

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
				setStatusEventoAnotacao(true);
			}
		}
	}

	public void render(Graphics g) {
		if (visible) {
			g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if (isStatusEventoAnotacao())
				eventoAnotacao(g);
		}
	}
	
	public void eventoAnotacao(Graphics g) {
		if(isStatusEventoAnotacao()) {

			TextBox.show(g, null, null, null);
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			int linhas = linha[currentPagina - 1].size();
 
			for(int j = 0; j < linhas; j++) {
				int fontHeight = g.getFontMetrics().getHeight();
				drawCentralizedString(g, linha[currentPagina - 1].get(j), (int) y + j * fontHeight);
			}	
			// EXISTE PRÓXIMA PAGINA, EXIBE BOTÃO DE PRÓXIMA PAGINA - CARLOS
			if(!eventIsOver && nextPaginaSelected) {
				currentPagina++;
				nextPaginaSelected = false;
			}
			
			if(currentPagina == this.paginas) {
				setEventIsOver(true);
			}
			
			if(exitSelected) { // CLICOU ESC PARA SAIR DO EVENTO
				setStatusEventoAnotacao(false);
				exitSelected = false;
				currentPagina = 1;
				eventIsOver = false;
				return;
			}			
		}
	}
	
	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}

	public boolean isNextPagina() {
		return nextPagina;
	}

	public void setNextPagina(boolean nextPagina) {
		this.nextPagina = nextPagina;
	}
	
	public boolean isNextPaginaSelected() {
		return nextPagina;
	}

	public void setNextPaginaSelected(boolean nextPaginaSelected) {
		this.nextPaginaSelected = nextPaginaSelected;
	}
	
	public boolean isExitSelected() {
		return exitSelected;
	}

	public void setExitSelected(boolean exitSelected) {
		this.exitSelected = exitSelected;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getCurrentPagina() {
		return currentPagina;
	}

	public void setCurrentPagina(int currentPagina) {
		this.currentPagina = currentPagina;
	}
	
	public boolean isStatusEventoAnotacao() {
		return statusEventoAnotacao;
	}

	public void setStatusEventoAnotacao(boolean statusEventoAnotacao) {
		this.statusEventoAnotacao = statusEventoAnotacao;
	}

	public boolean isEventIsOver() {
		return eventIsOver;
	}

	public void setEventIsOver(boolean eventIsOver) {
		this.eventIsOver = eventIsOver;
	}

}
