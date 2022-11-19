package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Sugestoes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sugestoes);

    }

    public void fGravarInterna(View view){
        Intent it = new Intent(this, Gravacao.class);
        it.putExtra(Constants.STORAGE_TYPE, Constants.Type.INTERNAL);
        startActivity(it);
    }

    public void fLerInterna(View view){
        Intent it = new Intent(this, Leitura.class);
        it.putExtra(Constants.STORAGE_TYPE, Constants.Type.INTERNAL);
        startActivity(it);
    }

    public void fGravarExterna(View view){
        Intent it = new Intent(this, Gravacao.class);
        it.putExtra(Constants.STORAGE_TYPE, Constants.Type.EXTERNAL);
        startActivity(it);
    }

    public void fLerExterna(View view){
        Intent it = new Intent(this, Leitura.class);
        it.putExtra(Constants.STORAGE_TYPE, Constants.Type.EXTERNAL);
        startActivity(it);
    }


}