package com.example.covidapp.Usuario;



import java.io.Serializable;

public class Usuario implements Serializable {
    private String Nome, Login, Senha;
    private int Id;

    public Usuario(){}

    public Usuario(int id, String nome, String login, String senha){
        Id = id;
        Nome = nome;
        Login = login;
        Senha = senha;
    }

    public Usuario(String login, String senha, String nome){
        Login = login;
        Senha = senha;
        Nome = nome;
    }

    public Usuario(int id){

    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}