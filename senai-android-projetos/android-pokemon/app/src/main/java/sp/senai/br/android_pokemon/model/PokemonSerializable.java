package sp.senai.br.android_pokemon.model;

import java.io.Serializable;

/**
 * Created by helena.strada on 22/01/18.
 */

public class PokemonSerializable implements Serializable {

    private Long id;
    private String nome;
    private Long numero;
    private String tipo;
    private String urlImagem;

    public static final long serialVersionUID = 100L;

    public PokemonSerializable(Long id) {
        this.id = id;
    }

    public PokemonSerializable(Long id, String nome, Long numero, String tipo, String urlImagem) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.tipo = tipo;
        this.urlImagem = urlImagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokemonSerializable that = (PokemonSerializable) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public PokemonSerializable() {

    }

    @Override
    public String toString() {
        return "PokemonSerializable{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
