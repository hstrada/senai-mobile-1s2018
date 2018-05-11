package br.senai.sp.android_fic_escolas.model;

/**
 * Created by Helena Strada on 15/03/2018.
 */

public class Usuario {

    private Long id;
    private String usuario;
    private String senha;

    public Usuario(Long id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario() {

    }

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario1 = (Usuario) o;

        if (id != null ? !id.equals(usuario1.id) : usuario1.id != null) return false;
        if (usuario != null ? !usuario.equals(usuario1.usuario) : usuario1.usuario != null)
            return false;
        return senha != null ? senha.equals(usuario1.senha) : usuario1.senha == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
