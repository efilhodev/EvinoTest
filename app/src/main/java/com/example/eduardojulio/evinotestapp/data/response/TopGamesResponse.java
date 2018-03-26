package com.example.eduardojulio.evinotestapp.data.response;

import com.example.eduardojulio.evinotestapp.bean.GameInfo;

import java.util.List;

/**
 * Classe responsavel por receber a resposta do servidor referente ao servico /games/top
 * Created by Eduardo Julio on 22/03/2018.
 */

public class TopGamesResponse {
    private List<GameInfo> games;

    public TopGamesResponse(List<GameInfo> games) {
        this.games = games;
    }

    public List<GameInfo> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return "TopGamesResponse{" +
                "games=" + games +
                '}';
    }
}
