package br.senai.sp.android_fic_escolas_dev.model;

import java.util.Date;

/**
 * Created by Helena Strada on 08/03/2018.
 */

public class Aluno {

    private Long id;
    private String nome;
    private Date dataNascimento;
    private String endereco;
    private String fotoAluno;

    public Aluno(Long id, String nome, Date dataNascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Aluno(Long id) {
        this.id = id;
    }

    public Aluno() {

    }

    public Aluno(String nome, Date dataNascimento, String endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public String getFotoAluno() {
        return fotoAluno;
    }

    public void setFotoAluno(String fotoAluno) {
        this.fotoAluno = fotoAluno;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        return id != null ? id.equals(aluno.id) : aluno.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco='" + endereco + '\'' +
                ", fotoAluno=" + fotoAluno +
                '}';
    }
}
