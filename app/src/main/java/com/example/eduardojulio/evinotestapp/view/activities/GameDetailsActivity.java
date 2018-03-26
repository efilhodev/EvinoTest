package com.example.eduardojulio.evinotestapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eduardojulio.evinotestapp.R;
import com.example.eduardojulio.evinotestapp.bean.GameInfo;
import com.example.eduardojulio.evinotestapp.interfaces.GameDetailsMVP;
import com.example.eduardojulio.evinotestapp.presenter.GameDetailsPresenter;

import butterknife.BindView;

/**
 *
 *
 * Created by Eduardo Julio on 22/03/2018.
 */

public class GameDetailsActivity extends BaseActivity implements GameDetailsMVP.View{
    @BindView(R.id.root)
    CoordinatorLayout clRoot;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivGameImage)
    ImageView ivGameImage;
    @BindView(R.id.tvViewers)
    TextView tvViewers;
    @BindView(R.id.tvChannels)
    TextView tvChannels;
    @BindView(R.id.tvPopularity)
    TextView tvPopularity;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    private GameDetailsMVP.Presenter presenter;

    @SuppressWarnings("unchecked")
    public static void navigate(AppCompatActivity activity, View view, GameInfo gameInfo){

        Intent intent = new Intent(activity, GameDetailsActivity.class);
        intent.putExtra(GameInfo.class.getSimpleName(), gameInfo);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "Screen");

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        init();
    }

    private void init(){
        if(null == presenter){
            presenter = new GameDetailsPresenter(this);
        }

        GameInfo game = (GameInfo) getIntent().getSerializableExtra(GameInfo.class.getSimpleName());

        Glide.with(this).load(game.getGame().getBoxURL()).placeholder(R.drawable.ph_image_view).crossFade().into(ivGameImage);
        toolbar.setTitle(game.getGame().getName());
        tvViewers.setText(String.format("%s %s", getResources().getString(R.string.viewers), String.valueOf(game.getViewers())));
        tvChannels.setText(String.format("%s %s", getResources().getString(R.string.channels), String.valueOf(game.getChannels())));
        tvPopularity.setText(String.format("%s %s", getResources().getString(R.string.popularity), String.valueOf(game.getGame().getPopularity())));

        loadDescription();
    }

    private void loadDescription(){
        showProgressBar(true);
        Handler handler= new Handler();
        Runnable r=new Runnable() {
            public void run() {
                Animation slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                showProgressBar(false);
                tvDescription.setText(presenter.getGameDescription());
                tvDescription.setVisibility(View.VISIBLE);
                tvDescription.startAnimation(slideUpAnimation);
            }
        };
        handler.postDelayed(r, 1000);
    }


    @Override
    public void getGameDescriptionFailures(String errorMessage) {
        Snackbar snack = Snackbar.make(clRoot, errorMessage, Snackbar.LENGTH_LONG);
        snack.show();
    }

    @Override
    public void showProgressBar(boolean show) {
        pbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
