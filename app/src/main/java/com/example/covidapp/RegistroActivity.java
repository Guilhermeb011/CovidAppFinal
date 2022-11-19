package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covidapp.Usuario.Usuario;
import com.example.covidapp.Usuario.UsuarioDAO;

public class RegistroActivity extends AppCompatActivity {

    Button btnConcluir;
    TextView txtTitulo;
    EditText txtNome, txtLogin, txtSenha;
    String UsuarioNome, UsuarioLogin, UsuarioSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registro);

        btnConcluir = findViewById(R.id.botaoRegistro);
        txtNome = findViewById(R.id.editTextTextPersonName);
        txtLogin = findViewById(R.id.editTextTextEmailAddress);
        txtSenha = findViewById(R.id.editTextTextPassword);


        Intent intent = getIntent();


        // CADASTRAR

            btnConcluir.setOnClickListener(v -> {
                UsuarioNome = txtNome.getText().toString();
                UsuarioLogin = txtLogin.getText().toString();
                UsuarioSenha = txtSenha.getText().toString();

                Usuario usuario = new Usuario(UsuarioLogin, UsuarioSenha, UsuarioNome);

                UsuarioDAO usuarioDAO = new UsuarioDAO(RegistroActivity.this);

                try {
                    usuarioDAO.cadastrarUsuario(usuario);
                    Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

    }
}