package com.example.eduardojulio.evinotestapp.model;

import android.support.annotation.NonNull;

import com.example.eduardojulio.evinotestapp.data.AppRestManager;
import com.example.eduardojulio.evinotestapp.data.response.TopGamesResponse;
import com.example.eduardojulio.evinotestapp.interfaces.TopGamesListMVP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe responsavel pelas regras do modelo de negocio e busca de dados para a lista de Top games.
 *
 * Created by Eduardo Julio on 22/03/2018.
 */

public class TopGamesListModel implements TopGamesListMVP.Model {
    private TopGamesListMVP.Presenter presenter;

    public TopGamesListModel(TopGamesListMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Metodo responsavel por realizar a chamada da lista de top games remotamente.
     */
    @Override
    public void doRequestTopGamesList() {
        Call<TopGamesResponse> call = AppRestManager.getInstance().getAppRestEndPoint().getTopsGames();
        call.enqueue(new Callback<TopGamesResponse>() {
            @Override
            public void onResponse(@NonNull Call<TopGamesResponse> call, @NonNull Response<TopGamesResponse> response) {
                switch (response.code()){
                    case 200:
                        //noinspection ConstantConditions
                        presenter.requestTopGameListSuccessfully(response.body().getGames());
                        break;
                    default:
                        presenter.requestTopGameListFailure("Erro " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopGamesResponse> call, @NonNull Throwable t) {
                presenter.requestTopGameListFailure(t.getMessage());
            }
        });
    }
}
