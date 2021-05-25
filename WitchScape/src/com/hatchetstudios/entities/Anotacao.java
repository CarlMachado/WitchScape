package com.hatchetstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.hatchetstudios.engine.Renderable;
import com.hatchetstudios.engine.Updateble;
import com.hatchetstudios.engine.camera.Camera;
import com.hatchetstudios.game.Game;
import com.hatchetstudios.game.screen.Screen;
import com.hatchetstudios.gui.TextBox;

public class Anotacao extends Entity implements Renderable, Updateble {

	private BufferedImage[] sprites;

	private boolean animation = false;

	private int currentAnimacao = 0;
	private int maxAnimacao = 0; // sprites.length
	private int currentFrame = 0;
	private int maxFrame = 30;

	private boolean visible = false;

	private boolean nextPaginaSelected = false;
	
	private boolean status = false;
	private boolean exit = false;
	private boolean sinalizeExit = false;
	private boolean lestPage = false;

	private List<String>[] linha;

	private int currentPagina = 1;
	private int paginas = 0;

	public Anotacao(int x, int y, int width, int height, BufferedImage sprite, boolean visible, List<String>[] linha) {
		super(x, y, width, height, sprite);
		this.visible = visible;
		this.linha = linha;
		this.setPaginas(linha.length);
		
		this.animation = false;
		this.nextPaginaSelected = false;
		this.status = false;
		this.exit = false;
		this.sinalizeExit = false;
		this.lestPage = false;
		this.currentPagina = 1;
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
				status = true;
			}
		}
	}

	public void render(Graphics g) {
		if (visible) {
			g.drawImage(sprites[currentAnimacao], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if (status)
				eventoAnotacao(g);
		}
	}

	public void eventoAnotacao(Graphics g) {
		if(status) {
			TextBox.showDialog(g, Game.boxFont, null, null, null, true, true);
			g.setFont(Game.boxFont);
			g.setColor(Color.BLACK);
			int linhas = linha[currentPagina - 1].size();
 
			for(int j = 0; j < linhas; j++) {
				int fontHeight = g.getFontMetrics().getHeight();
				drawCentralizedString(g, linha[currentPagina - 1].get(j), (int) y - 30 + j * fontHeight);
			}	
			
			if(nextPaginaSelected) {
				if(currentPagina != this.paginas)
						currentPagina++;
				nextPaginaSelected = false;
			}

			if(currentPagina == this.paginas) {
				lestPage = true;
			}	

			if (exit && !sinalizeExit){			
				animation = false;			
				nextPaginaSelected = false;
				sinalizeExit = true;
				exit = false;
				lestPage = false;
				currentPagina = 1;
				status = false;
				return;
			}	
		}			
	}

	protected void drawCentralizedString(Graphics g, String str, int y) {
		g.drawString(str, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str) / 2, y);
	}

	public boolean isNextPaginaSelected() {
		return nextPaginaSelected;
	}

	public void setNextPaginaSelected(boolean nextPaginaSelected) {
		this.nextPaginaSelected = nextPaginaSelected;
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

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isSinalizeExit() {
		return sinalizeExit;
	}

	public void setSinalizeExit(boolean sinalizeExit) {
		this.sinalizeExit = sinalizeExit;
	}

	public boolean isLestPage() {
		return lestPage;
	}

	public void setLestPage(boolean lestPage) {
		this.lestPage = lestPage;
	}

}
