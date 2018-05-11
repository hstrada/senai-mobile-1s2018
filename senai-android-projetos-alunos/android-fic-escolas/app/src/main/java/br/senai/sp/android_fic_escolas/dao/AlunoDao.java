package br.senai.sp.android_fic_escolas.dao;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.senai.sp.android_fic_escolas.model.Aluno;

/**
 * Created by Helena Strada on 08/03/2018.
 */

public class AlunoDao {

    public static AlunoDao manager = new AlunoDao();

    // Lista aonde serão armazenados os alunos
    private List<Aluno> lista;

    private long id = 1;

    private AlunoDao() {
        Date date1 = null, date2 = null, date3 = null;
        try {
            SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
            date1 = dateformat3.parse("17/07/1989");
            date2 = dateformat3.parse("18/03/1993");
            date3 = dateformat3.parse("01/11/1997");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lista = new ArrayList<>();
        lista.add(new Aluno(id++, "Helena Strada", date1, "Rua Cinco Pontas, 8, São Paulo - SP"));
        lista.add(new Aluno(id++, "Maria", date2, "Rua Cinco Pontas, 8"));
        lista.add(new Aluno(id++, "Neuza", date3, "Rua Cinco Pontas, 8"));
    }

    public List<Aluno> getLista() {
        return lista;
    }

    public void remover(Aluno Aluno) {
        lista.remove(Aluno);
        Log.d("Deletar: ", lista.toString());
    }

    public void salvar(Aluno obj) {
        if(obj.getId() == null) {
            obj.setId(id++);
            lista.add(obj);
        } else {
            lista.set(lista.indexOf(obj), obj);
        }
        Log.d("Salvar: ", lista.toString());
    }

    public Aluno localizar(Long id) {
        return lista.get(lista.indexOf(new Aluno(id)));
    }


}
