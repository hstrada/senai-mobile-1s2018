package br.senai.sp.android_fic_escolas.dao;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sp.android_fic_escolas.model.Aluno;
import br.senai.sp.android_fic_escolas.model.Usuario;

/**
 * Created by Helena Strada on 19/03/2018.
 */

public class UsuarioDao {

    public static UsuarioDao manager = new UsuarioDao();

    // Lista aonde serão armazenados os alunos
    private List<Usuario> lista;

    private long id = 1;

    private UsuarioDao() {
        lista = new ArrayList<>();
        lista.add(new Usuario("a", "a"));
    }

    public Usuario localizar(Usuario usuarioRecebido) {

        Usuario procurarUsuario = null;

        for (Usuario u: lista) {
            if ((u.getUsuario().equals(usuarioRecebido.getUsuario())) && (u.getSenha().equals(usuarioRecebido.getSenha()))) {
                procurarUsuario = u;
                return procurarUsuario;
            }
        }
        return procurarUsuario;
    }


}
