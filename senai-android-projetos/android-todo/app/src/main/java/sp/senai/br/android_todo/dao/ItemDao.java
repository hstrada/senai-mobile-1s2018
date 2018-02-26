package sp.senai.br.android_todo.dao;

import java.util.ArrayList;
import java.util.List;

import sp.senai.br.android_todo.model.Item;
import sp.senai.br.android_todo.model.Situacao;

public class ItemDao {

    public static ItemDao itemDao = new ItemDao();

    private List<Item> itens;

    private long id = 1;

    private ItemDao() {
        itens = new ArrayList<>();
        itens.add(new Item(id++, "Descricao 1", Situacao.ANDAMENTO));
        itens.add(new Item(id++, "Descricao 2", Situacao.ANDAMENTO));
        itens.add(new Item(id++, "Descricao 3", Situacao.FINALIZADA));
    }

    public List<Item> getItens() {
        return itens;
    }

    public void salvar(Item item) {
        if (item.getId() == null) {
            item.setId(id++);
            itens.add(item);
        }
    }
}
