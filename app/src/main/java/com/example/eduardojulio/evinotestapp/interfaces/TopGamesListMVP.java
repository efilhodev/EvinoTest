package com.example.eduardojulio.evinotestapp.interfaces;

import com.example.eduardojulio.evinotestapp.bean.GameInfo;

import java.util.List;

/**
 * Interface com os metodos necessarios para exibir a lista de top games
 *
 * Created by Eduardo Julio on 22/03/2018.
 */

public interface TopGamesListMVP {
    interface Model{
        /**
         * Metodo responsável por requisitar a lista de top games para o serviço remoto.
         */
        void doRequestTopGamesList();
    }
    interface Presenter{
        /**
         * Metodo responsável por definir comportamento para view e executar metodo de negocio
         * do Model para lista de top games.
         */
        void doRequestTopGamesList();

        /**
         * Método resposável por retornar a lista remota com sucesso para View, depois
         * de ser chamada no Model no caso de sucesso.
         * @param games List<GameInfo> games, lista com objetos contendo informações sobre os jogos.
         */
        void requestTopGameListSuccessfully(List<GameInfo> games);

        /**
         * Método responsável por retornar uma mensagem de erro para View, depois de ser chamada
         * no Model no caso de falha.
         * @param errorMessage String errorMessage, String contendo o erro responsável pela falha.
         */
        void requestTopGameListFailure(String errorMessage);
    }
    interface View{
        /**
         * Método responsável por criar a lista de jogos na View em caso de sucesso na chamada
         * remota.
         * @param games List<GameInfo> games, lista com objetos contendo informações sobre os jogos.
         */
        void requestTopGameListSuccessfully(List<GameInfo> games);

        /**
         *  Método responsável por exibir a mensagem de erro ao usuário por meio de um snack bar.
         * @param errorMessage String errorMessage, String contendo o erro responsável pela falha.
         */
        void requestTopGameListFailure(String errorMessage);

        /**
         * Método responsável por exibir/esconder o componente progress bar em caso loading
         * @param show Boolean show, flag para saber se exibe ou não o componente.
         */
        void showProgressBar(boolean show);
    }
}
