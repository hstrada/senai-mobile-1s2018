package sp.senai.br.android_todo.model;

import java.util.Objects;

public class Item {

    private Long id;
    private String descricao;
    private Situacao situacao;

    public Item() {

    }

    public Item(Long id, String descricao, Situacao situacao) {
        this.id = id;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", situacao=" + situacao +
                '}';
    }
}
