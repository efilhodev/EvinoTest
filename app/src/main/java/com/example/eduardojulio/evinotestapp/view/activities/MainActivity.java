package com.example.eduardojulio.evinotestapp.view.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.eduardojulio.evinotestapp.R;
import com.example.eduardojulio.evinotestapp.bean.GameInfo;
import com.example.eduardojulio.evinotestapp.interfaces.TopGamesListMVP;
import com.example.eduardojulio.evinotestapp.presenter.TopGamesListPresenter;
import com.example.eduardojulio.evinotestapp.view.adapter.TopGameListAdapter;
import com.example.eduardojulio.evinotestapp.view.widget.SpaceItemDecoration;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 *
 * Created by Eduardo Julio on 21/03/2018.
 */


public class MainActivity extends BaseActivity implements TopGamesListMVP.View{
    @BindView(R.id.root)
    CoordinatorLayout clRoot;
    @BindView(R.id.errorRoot)
    RelativeLayout rlErrorRoot;
    @BindView(R.id.rvTopGames)
    RecyclerView rvTopGames;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.tbDefault)
    Toolbar toolbar;
    @BindView(R.id.tbSearch)
    Toolbar searchToolbar;

    private boolean isSearch;
    private TopGamesListMVP.Presenter presenter;
    private TopGameListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Metodo responsavel por inicializar componentes basicos da view.
     */
    private void init(){
        setSupportActionBar(toolbar);
        if(null == presenter){
            presenter = new TopGamesListPresenter(this);
        }
        presenter.doRequestTopGamesList();

        StaggeredGridLayoutManager mLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvTopGames.setLayoutManager(mLayoutManager);
        rvTopGames.setHasFixedSize(true);
        rvTopGames.addItemDecoration(new SpaceItemDecoration(15));
    }

    @Override
    public void requestTopGameListSuccessfully(List<GameInfo> games) {
        adapter = new TopGameListAdapter(games, this);
        rvTopGames.setAdapter(adapter);

        adapter.setOnItemClickListener(new TopGameListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, GameInfo obj, int position) {
                GameDetailsActivity.navigate(MainActivity.this, view, obj);
            }
        });
    }

    @Override
    public void requestTopGameListFailure(String errorMessage) {
        rlErrorRoot.setVisibility(View.VISIBLE);
        Snackbar snack = Snackbar.make(clRoot, errorMessage, Snackbar.LENGTH_LONG);
        snack.show();
    }

    @Override
    public void showProgressBar(boolean show) {
        pbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.errorRoot)
    public void onRefreshListClick(){
        rlErrorRoot.setVisibility(View.GONE);
        presenter.doRequestTopGamesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(isSearch ? R.menu.menu_search_toolbar : R.menu.menu_main_toolbar, menu);

       if(isSearch){
            final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
            search.setIconified(false);
            search.setQueryHint("Procurar ...");
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    adapter.getFilter().filter(s);
                    return true;
                }
            });
            search.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    closeSearch();
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                isSearch = true;
                searchToolbar.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.GONE);
                setSupportActionBar(searchToolbar);
                supportInvalidateOptionsMenu();
                return true;

            case android.R.id.home:
                closeSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método responsável por fechar o campo de busca no toolbar.
     */
    private void closeSearch() {
        if (isSearch) {
            isSearch = false;
            adapter.getFilter().filter("");
            setSupportActionBar(toolbar);
            searchToolbar.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
            supportInvalidateOptionsMenu();
        }
    }

}
