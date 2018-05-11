package br.senai.sp.android_fic_escolas_dev.dao;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.android_fic_escolas_dev.model.Usuario;

/**
 * Created by Helena Strada on 23/03/2018.
 */

public class UsuarioDao {

    public static UsuarioDao manager = new UsuarioDao();

    // Lista aonde serão armazenados os alunos
    private List<Usuario> lista;

    private long id = 1;

    private UsuarioDao() {
        lista = new ArrayList<>();
        lista.add(new Usuario("administrador", "administrador"));
    }

    public Usuario localizar(Usuario usuarioRecebido) {

        Usuario procurarUsuario = null;

        for (Usuario u : lista) {
            if ((u.getEmail().equals(usuarioRecebido.getEmail())) && (u.getSenha().equals(usuarioRecebido.getSenha()))) {
                procurarUsuario = u;
                return procurarUsuario;
            }
        }
        return procurarUsuario;
    }

}
