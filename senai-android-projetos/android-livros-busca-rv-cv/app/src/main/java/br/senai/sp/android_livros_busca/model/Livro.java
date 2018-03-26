package br.senai.sp.android_livros_busca.model;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class Livro {

    private Long id;
    private String nome;
    private byte[] capa;
    private String autor;
    private Date dataLancamento;

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public Livro(Long id, String nome, String autor, Date dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
    }

    public Livro(Long id, String nome, byte[] capa, String autor, Date dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.capa = capa;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
    }

    public Livro(String nome, byte[] capa, String autor, Date dataLancamento) {
        this.nome = nome;
        this.capa = capa;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        return id != null ? id.equals(livro.id) : livro.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(capa);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (dataLancamento != null ? dataLancamento.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capa=" + Arrays.toString(capa) +
                ", autor='" + autor + '\'' +
                ", dataLancamento=" + dataLancamento +
                '}';
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Livro(Long id) {
        this.id = id;
    }

    public Livro(Long id, String nome, String autor) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
    }

    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public Livro(Long id, String nome, byte[] capa, String autor) {
        this.id = id;
        this.nome = nome;
        this.capa = capa;
        this.autor = autor;
    }

    public Livro() {

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

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
