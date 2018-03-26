package com.example.eduardojulio.evinotestapp.presenter;

import com.example.eduardojulio.evinotestapp.bean.GameInfo;
import com.example.eduardojulio.evinotestapp.interfaces.TopGamesListMVP;
import com.example.eduardojulio.evinotestapp.model.TopGamesListModel;

import java.util.List;

/**
 * Classe responsável por fazer a ligação do Model com a View
 *
 * Created by Eduardo Julio on 22/03/2018.
 */

public class TopGamesListPresenter implements TopGamesListMVP.Presenter {
    private TopGamesListMVP.Model model;
    private TopGamesListMVP.View  view;

    public TopGamesListPresenter(TopGamesListMVP.View view) {
        this.view = view;
        model = new TopGamesListModel(this);
    }

    @Override
    public void doRequestTopGamesList() {
        view.showProgressBar(true);
        model.doRequestTopGamesList();
    }

    @Override
    public void requestTopGameListSuccessfully(List<GameInfo> games) {
        view.showProgressBar(false);
        view.requestTopGameListSuccessfully(games);
    }

    @Override
    public void requestTopGameListFailure(String errorMessage) {
        view.showProgressBar(false);
        view.requestTopGameListFailure(errorMessage);
    }
}
