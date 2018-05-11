package br.senai.sp.android_fic_escolas.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sp.android_fic_escolas.model.Aluno;
import br.senai.sp.android_fic_escolas.model.Escola;

/**
 * Created by Helena Strada on 08/03/2018.
 */

public class EscolaDao {

    public static EscolaDao manager = new EscolaDao();

    // Lista aonde serão armazenados os jogos
    private List<Escola> lista;

    private long id = 0;

    private EscolaDao() {
        lista = new ArrayList<>();
        lista.add(new Escola(id++, "Senai Informática", "00.111.000/0001-00", "Avenida Barão de Limeira, 539"));
    }

    public Escola localizar(Long id) {
        return lista.get(lista.indexOf(new Escola(id)));
    }

}
