package com.example.eduardojulio.evinotestapp.model;

import android.content.Context;

import com.example.eduardojulio.evinotestapp.R;
import com.example.eduardojulio.evinotestapp.interfaces.GameDetailsMVP;

/**
 * Classe responsavel pelas regras do modelo de negocio e busca de dados para o detalhes do jogo.
 * Created by Eduardo Julio on 25/03/2018.
 */

public class GameDetailsModel implements GameDetailsMVP.Model {
    private GameDetailsMVP.Presenter presenter;

    public GameDetailsModel(GameDetailsMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Metodo responsavel por retornar a descricao do jogo.
     * OBS:EM UMA SITUACAO REAL, A BUSCA TERIA DE TER UM ID E ELA SERIA FEITA NO BANCO DE DADOS
     * OU NO SERVIDOR REMOTO. NESTE CASO NUNCA DARA NULL, MAS EM UM CASO REAL SERIA POSSIVEL.
     * @param context Context context, objeto que contem informacoes sobre o estado aplicacao.
     * @return String , texto com a descricao do jogo.
     */
    @Override
    public String getGameDescription(Context context){
        String description = null;
        try {
            description =  context.getResources().getString(R.string.mock_description_text);
            if(null == description) throw new NullPointerException();
        }catch (Exception e){
            presenter.getGameDescriptionFailures(e.getMessage());
        }
        return description;
    }
}
