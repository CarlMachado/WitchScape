package com.hatchetstudios.gui;

import com.hatchetstudios.engine.Renderable;
import com.hatchetstudios.engine.Updateble;
import com.hatchetstudios.game.Game;
import com.hatchetstudios.game.GameState;
import com.hatchetstudios.game.screen.Screen;

import java.awt.*;

public class GameIntro implements Updateble, Renderable {

    public int stateIntro = 0;

    public GameIntro() {
    }

    @Override
    public void update() {
        if (Game.enter) {
            stateIntro++;
            Game.enter = false;
        }
    }

    @Override
    public void render(Graphics g) {
        var str1 = "Em um certo dia eu estava explorando a floresta negra,";
        var str2 = "Até que eu me deparei um um garoto.";
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        g.setColor(Color.WHITE);
        g.setFont(Game.boxFont);
        if (stateIntro == 0) {
            str1 = "Em um certo dia eu estava explorando a floresta negra,";
            str2 = "Até que eu me deparei um um garoto.";
        } else if (stateIntro == 1) {
            str1 = "Ele, que parecia estar muito triste, me pediu um favor.";
            str2 = "Para que eu pegasse sua bola dentro da casa da bruxa má.";
        } else if (stateIntro == 2) {
            str1 = "Bruxa má? Eu me perguntei. Há essas crianças.";
            str2 = "Eu nem imaginava o que estava por vir...";
        } else if (stateIntro > 2){
            GameState.state = GameState.PLAYING;
        }
        g.drawString(str1, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str1) / 2, 300);
        g.drawString(str2, Screen.SCALE_WIDTH / 2 - g.getFontMetrics().stringWidth(str2) / 2, 350);
    }
}
