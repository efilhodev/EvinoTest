package com.example.eduardojulio.evinotestapp.interfaces;

import android.content.Context;

/**
 * Interface com os metodos necessarios para exibir o detalhe do game.
 *
 * Created by Eduardo Julio on 25/03/2018.
 */

public interface GameDetailsMVP {
    interface Model{
        /**
         * Metodo responsavel por retornar a descricao do jogo.
         * OBS:EM UMA SITUACAO REAL, A BUSCA TERIA DE TER UM ID E ELA SERIA FEITA NO BANCO DE DADOS
         * OU NO SERVIDOR REMOTO.
         * @return String , texto com a descricao do jogo.
         */
        String getGameDescription(Context context);
    }
    interface Presenter{
        String getGameDescription();
        void   getGameDescriptionFailures(String errorMessage);
    }
    interface View{
        //Qualquer metodo de visualizacao que seja necessario para o detalhes de jogos.
        void getGameDescriptionFailures(String errorMessage);

        /**
         * Método responsável por exibir/esconder o componente progress bar em caso loading
         * @param show Boolean show, flag para saber se exibe ou não o componente.
         */
        void showProgressBar(boolean show);
    }
}
