package br.senai.sp.android_livros_busca.dao;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.senai.sp.android_livros_busca.model.Livro;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class LivroDao {

    public static LivroDao manager = new LivroDao();

    private List<Livro> lista;

    private long id = 1;

    private LivroDao() {

        Date date1 = null, date2 = null, date3 = null, date4 = null;
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            date1 = dateformat.parse("17/07/1989");
            date2 = dateformat.parse("18/03/2018");
            date3 = dateformat.parse("01/11/2015");
            date4 = dateformat.parse("01/09/2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lista = new ArrayList<>();
        lista.add(new Livro(id++, "Livro A", "Autor A", date1));
        lista.add(new Livro(id++, "Livro B", "Autor B", date2));
        lista.add(new Livro(id++, "Book C", "Author C", date3));
        lista.add(new Livro(id++, "Book D", "Author D", date4));
        lista.add(new Livro(id++, "Book C", "Author C", date3));
        lista.add(new Livro(id++, "Book D", "Author D", date4));
        lista.add(new Livro(id++, "Book C", "Author C", date3));
        lista.add(new Livro(id++, "Book D", "Author D", date4));
        lista.add(new Livro(id++, "Book C", "Author C", date3));
    }

    public List<Livro> getLista() {
        return lista;
    }

    public void remover(Livro livro) {
        lista.remove(livro);
    }

    public void salvar(Livro obj) {
        if(obj.getId() == null) {
            obj.setId(id++);
            lista.add(obj);
        } else {
            lista.set(lista.indexOf(obj), obj);
        }
        Log.d("Salvar: ", lista.toString());
    }

    public Livro localizar(Long id) {
        return lista.get(lista.indexOf(new Livro(id)));
    }

}
