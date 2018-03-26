package sp.senai.br.android_pokemon.model;

/**
 * Created by helena.strada on 22/01/18.
 */

public class Pokemon {

    private Long id;
    private String nome;
    private Long numero;
    private String tipo;
    private String urlImagem;

    public Pokemon(Long id, String nome, Long numero, String tipo, String urlImagem) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.tipo = tipo;
        this.urlImagem = urlImagem;
    }

    public Pokemon() {

    }

    public Pokemon(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        return id != null ? id.equals(pokemon.id) : pokemon.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (urlImagem != null ? urlImagem.hashCode() : 0);
        return result;
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
