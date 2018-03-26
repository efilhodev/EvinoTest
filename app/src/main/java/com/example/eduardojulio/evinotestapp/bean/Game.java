package com.example.eduardojulio.evinotestapp.bean;

import java.io.Serializable;

/**
 * Classe responsável pelos atributos de um jogo.
 * Created by Eduardo Julio on 3/21/2018.
 */

public class Game implements Serializable{
    private String name;
    private String boxURL;
    private String logoURL;

    private int popularity;

    public String getName() {
        return name;
    }

    public String getBoxURL() {
        return boxURL;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoxURL(String boxURL) {
        this.boxURL = boxURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", boxURL='" + boxURL + '\'' +
                ", logoURL='" + logoURL + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
