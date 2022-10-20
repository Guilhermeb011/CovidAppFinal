package com.example.covidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContaFragment extends Fragment {
    public static boolean login_validate = false;
    private static final String FILE_EMAIL = "email.txt";
    private static final String FILE_SENHA = "senha.txt";
    EditText emailCampo;
    EditText senhaCampo;
    private TextView registrar;

    // VALIDAÇÃO COMENTADA - EXPLICAÇÃO NA LINHA 80
    /*private String userEmail = "admin@gmail.com";
    private String userPass = "admin123";
    boolean isValid = false;*/

    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* definição das variaveis */
        emailCampo = (EditText) getView().findViewById(R.id.email);
        senhaCampo = (EditText) getView().findViewById(R.id.senha);
        registrar = (TextView) getView().findViewById(R.id.registre);

        Button botao_entrar = getView().findViewById(R.id.botao_enviar);
        Button botao_lembrar = getView().findViewById(R.id.botao_lembrar);

        botao_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editEmail = emailCampo.getText().toString();
                String editSenha = senhaCampo.getText().toString();
                if(editEmail.isEmpty() || editSenha.isEmpty()){
                    Snackbar.make(v, "Insira o Email e Senha para Login", Snackbar.LENGTH_SHORT).show();
                } else if(editEmail.isEmpty()){
                    Snackbar.make(v, "Insira o Email para Login", Snackbar.LENGTH_SHORT).show();
                } else if(editSenha.isEmpty()){
                    Snackbar.make(v, "Insira a Senha para Login", Snackbar.LENGTH_SHORT).show();
                } else{
                    /*
                    VALIDAÇÃO COMENTADA - EXPLICAÇÃO NA LINHA 170

                    isValid = validate(editEmail, editSenha);

                    if(!isValid){
                        Snackbar.make(v, "Email ou senha inválidos, tente novamente", Snackbar.LENGTH_SHORT).show();
                    } else{
                        Snackbar.make(v, "Voce entrou mas a página está em construção", Snackbar.LENGTH_SHORT).show();
                    }*/

                    String email = emailCampo.getText().toString();
                    String senha = senhaCampo.getText().toString();
                    FileOutputStream fosEmail = null;
                    FileOutputStream fosSenha = null;

                    try {
                        fosEmail = getActivity().openFileOutput(FILE_EMAIL, Context.MODE_PRIVATE);
                        fosSenha = getActivity().openFileOutput(FILE_SENHA, Context.MODE_PRIVATE);
                        fosEmail.write(email.getBytes());
                        fosSenha.write(senha.getBytes());

                        emailCampo.getText().clear();
                        senhaCampo.getText().clear();
                        Toast.makeText(context,"Salvo em" + context.getFilesDir() + "/" + FILE_EMAIL + " e " + FILE_SENHA, Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(fosEmail != null && fosSenha != null){
                            try {
                                fosEmail.close();
                                fosSenha.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistroActivity.class);
                startActivity(intent);
            }
        });

        botao_lembrar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                FileInputStream fisEmail = null;
                FileInputStream fisSenha = null;
                try {
                    fisEmail = context.openFileInput(FILE_EMAIL);
                    fisSenha = context.openFileInput(FILE_SENHA);
                    InputStreamReader isrEmail = new InputStreamReader(fisEmail);
                    InputStreamReader isrSenha = new InputStreamReader(fisSenha);
                    BufferedReader brEmail = new BufferedReader(isrEmail);
                    BufferedReader brSenha = new BufferedReader(isrSenha);
                    StringBuilder sbEmail = new StringBuilder();
                    StringBuilder sbSenha = new StringBuilder();
                    String email;
                    String senha;
                    while((email = brEmail.readLine()) != null && (senha = brSenha.readLine()) != null){
                        sbEmail.append(email).append("\n");
                        sbSenha.append(senha).append("\n");
                    }

                    emailCampo.setText(sbEmail.toString());
                    senhaCampo.setText(sbSenha.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fisEmail != null && fisSenha != null){
                        try {
                            fisEmail.close();
                            fisSenha.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    // TODA A VALIDAÇÃO DE SENHA E EMAIL CADASTRADO FOI COMENTADA E SERÁ IMPLEMENTADA JUNTO COM O BD
    /*private boolean validate(String email, String senha){
        if(email.equals(userEmail) && senha.equals(userPass)){
            return true;
        }
        return false;
    }*/
}
