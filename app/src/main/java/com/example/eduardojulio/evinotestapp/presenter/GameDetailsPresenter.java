package com.example.eduardojulio.evinotestapp.presenter;

import android.content.Context;

import com.example.eduardojulio.evinotestapp.interfaces.GameDetailsMVP;
import com.example.eduardojulio.evinotestapp.model.GameDetailsModel;

/**
 *
 * Classe responsável por fazer a ligação do Model com a View
 * Created by Eduardo Julio on 25/03/2018.
 */

public class GameDetailsPresenter implements GameDetailsMVP.Presenter {
    private GameDetailsMVP.Model model;
    private GameDetailsMVP.View  view;

    public GameDetailsPresenter(GameDetailsMVP.View view) {
        this.view = view;
        this.model = new GameDetailsModel(this);
    }

    @Override
    public String getGameDescription() {
        return model.getGameDescription((Context) view);
    }

    @Override
    public void getGameDescriptionFailures(String errorMessage) {
        view.getGameDescriptionFailures(errorMessage);
    }
}
