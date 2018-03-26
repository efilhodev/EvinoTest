package com.example.eduardojulio.evinotestapp.view.activities;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Classe responsavel por adicionar a chamada do butter knife no metodo setContentView para
 * que nao precise adicionar em todas as activities
 *
 * Created by Eduardo Julio on 22/03/2018.
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

}

