package com.example.eduardojulio.evinotestapp.view.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eduardojulio.evinotestapp.R;
import com.example.eduardojulio.evinotestapp.bean.GameInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Classe responsável por adaptar os itens de uma lista ao layout implementado.
 *
 * Created by Eduardo Julio on 3/21/2018.
 */

public class TopGameListAdapter extends RecyclerView.Adapter<TopGameListAdapter.TopGameListViewHolder>{
    private List<GameInfo> games;
    private List<GameInfo> filteredGames;
    private Context ctx;
    private ItemFilter mFilter = new ItemFilter();

    public TopGameListAdapter(List<GameInfo> games, Context ctx) {
        this.games = games;
        this.filteredGames = games;
        this.ctx = ctx;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, GameInfo obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @Override
    public TopGameListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game, parent ,false);
        return new TopGameListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopGameListViewHolder holder, int position) {
        GameInfo game = filteredGames.get(position);
        holder.bindObject(game, position);
    }

    public Filter getFilter(){
        return mFilter;
    }

    @Override
    public int getItemCount() {
        return filteredGames.size();
    }

    /**
     * Classe responsavel por realizar o filtro no lista de top games.
     */
     class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<GameInfo> list = games;
            final List<GameInfo> resultGames = new ArrayList<>(list.size());

            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getGame().getName();
                if (str_title.toLowerCase().contains(query)) {
                    resultGames.add(list.get(i));
                }
            }

            results.values = resultGames;
            results.count = resultGames.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredGames = (List<GameInfo>) results.values;
            notifyDataSetChanged();
        }
    }


    /**
     * Classe resposável por atribuir um valor ou comportamento aos componentes visuais.
     */
    class TopGameListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cvRoot)
        CardView cvRoot;
        @BindView(R.id.mplRoot)
        View mplRoot;
        @BindView(R.id.ivGameImage)
        ImageView ivGameImage;
        @BindView(R.id.tvGameName)
        TextView tvGameName;
        @BindView(R.id.tvViewers)
        TextView tvViewers;
        @BindView(R.id.tvChannels)
        TextView tvChannels;

         TopGameListViewHolder(View view) {
             super(view);
             ButterKnife.bind(this, view);
        }

        /**
         * Metodo responsável por inicializar os componentes da view com o valor do objeto recebido.
         * @param game GameInfo game, Objeto com os dados do jogo.
         */
        void bindObject(final GameInfo game, final int position){
            tvGameName.setText(game.getGame().getName());
            tvViewers.setText(String.format("%s %s", ctx.getResources().getString(R.string.viewers), String.valueOf(game.getViewers())));
            tvChannels.setText(String.format("%s %s", ctx.getResources().getString(R.string.channels), String.valueOf(game.getChannels())));
            Glide.with(ctx).load(game.getGame().getBoxURL()).placeholder(R.drawable.ph_image_view).crossFade().into(ivGameImage);

            mplRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(cvRoot, game, position);
                    }
                }
            });
        }
    }
}
