package com.example.eduardojulio.evinotestapp.data;


import com.example.eduardojulio.evinotestapp.data.response.TopGamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Interface para os métodos REST de comunição com o servidor da aplicação.
 *
 * Created by Eduardo Julio on 3/21/2018.
 */

public interface AppRestEndpoint {

    /**
     * Interface de um metodo para chamar a lista de ranking de jogos do servidor remoto.
     * @return List<GameInfo> , Lista de objetos que possuem as informações do jogo.
     */
    @Headers({"Accept: application/vnd.twitchtv.v5+json",
              "Client-ID: 3a6s4i1e7b2yciviwit8ms4r5lowhe" })
    @GET("games/top?limit=25")
    Call<TopGamesResponse> getTopsGames();
}
