package sp.senai.br.android_pokemon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by helena.strada on 22/01/18.
 */

public class PokemonParcelable implements Parcelable {

    private Long id;
    private String nome;
    private Long numero;
    private String tipo;
    private String urlImagem;

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

    public PokemonParcelable() {

    }

    public PokemonParcelable(Long id) {
        this.id = id;
    }

    public PokemonParcelable(Long id, String nome, Long numero, String tipo, String urlImagem) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.tipo = tipo;
        this.urlImagem = urlImagem;
    }

    public PokemonParcelable(Parcel source) {
        id = source.readLong();
        nome = source.readString();
        numero = source.readLong();
        tipo = source.readString();
        urlImagem = source.readString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokemonParcelable pokemon = (PokemonParcelable) o;

        if (!id.equals(pokemon.id)) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nome);
        dest.writeLong(numero);
        dest.writeString(tipo);
        dest.writeString(urlImagem);
    }

    public static final Creator<PokemonParcelable> CREATOR =
            new Creator<PokemonParcelable>() {
        @Override
        public PokemonParcelable[] newArray(int size) {
            return new PokemonParcelable[size];
        }

        @Override
        public PokemonParcelable createFromParcel(Parcel source) {
            return new PokemonParcelable(source);
        }
    };

}
