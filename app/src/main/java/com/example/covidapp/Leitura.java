package com.example.covidapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import com.example.covidapp.Constants.Type;
import android.widget.TextView;
import android.widget.Toast;

public class Leitura extends AppCompatActivity {

    private TextView txtTexto;
    private Type type;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_leitura);

        txtTexto = (TextView) findViewById(R.id.txt_text);
        type = (Type) getIntent().getSerializableExtra(Constants.STORAGE_TYPE);

        try {

            if (type == Type.INTERNAL){
                LerInterno();
            }else{
                LerExterno();
            }

        } catch (IOException e){
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void LerInterno() throws IOException {
        FileInputStream infl = openFileInput(Constants.FILE_NAME);

        Scanner scanner = new Scanner(infl);
        try {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                sb.append(line).append(System.lineSeparator());
            }

            txtTexto.setText(sb.toString());
        } finally {
            scanner.close();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void LerExterno() throws IOException{
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)){
            throw new IOException("O SD Card não montado ou encontrado");
        }

        File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        Scanner scanner = new Scanner( new File(dir, Constants.FILE_NAME));

        try {
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                sb.append(line).append(System.lineSeparator());
            }
            txtTexto.setText(sb.toString());
        }finally {
            scanner.close();
        }


    }
}