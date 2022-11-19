package com.example.covidapp.Usuario;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.covidapp.Conexao;

public class UsuarioDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public UsuarioDAO(Context applicationContext) {
        conexao = new Conexao(applicationContext);
        banco = conexao.getWritableDatabase();
    }

    public long cadastrarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("nome", usuario.getNome());

        return banco.insert("tbUsuario", null, values);
    }

    public Usuario buscarUsuario(String login){
        Usuario usuario = new Usuario();
        Cursor cursor = banco.query("tbUsuario",
                new String[]{
                        "idUsuario," +
                                "login," +
                                "senha," +
                                "nome"
                },
                "login = ?",
                new String[]{login},
                null,
                null,
                null,
                String.valueOf(1));

        while(cursor.moveToNext()){
            usuario.setId(cursor.getInt(0));
            usuario.setLogin(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setNome(cursor.getString(3));
        }

        return usuario;
    }

    public long updateUsuario(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put("idUsuario", usuario.getId());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("nome", usuario.getNome());

        return banco.update("tbUsuario", values, "idUsuario = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public Boolean verificarLogin(String login, String senha){
        Cursor cursor = banco.rawQuery("SELECT * FROM tbUsuario WHERE login = ? AND senha = ?", new String[] {login, senha});

        return cursor.getCount() > 0;
    }
}
