package com.example.eduardojulio.evinotestapp.bean;

import java.io.Serializable;

/**
 * Classe responsavel pelos atributos informativos de um jogo no servidor remoto.
 *
 * Created by Eduardo Julio on 3/21/2018.
 */

public class GameInfo implements Serializable{
    private Game game;
    private int channels;
    private int viewers;

    public Game getGame() {
        return game;
    }

    public int getChannels() {
        return channels;
    }

    public int getViewers() {
        return viewers;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "game=" + game +
                ", channels=" + channels +
                ", viewers=" + viewers +
                '}';
    }
}
