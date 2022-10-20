package com.example.covidapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class Page_sintomas extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_previnir);

        Toolbar toolbarReg = (Toolbar) findViewById(R.id.toolbarReg);

        toolbarReg.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbarReg.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page_sintomas.this, ListaFragment.class);
                startActivity(intent);
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.assistir_demon_slayer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.crunchyroll.com/pt-br/demon-slayer-kimetsu-no-yaiba");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                finish();
            }
        });
    }
}