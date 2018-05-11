package br.senai.sp.android_fic_escolas_dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;

/**
 * Created by Helena Strada on 08/03/2018.
 */

public class AlunoAdapter extends BaseAdapter {

    private final List<Aluno> alunosAdapter;
    private final Activity activity;

    public AlunoAdapter(List<Aluno> alunosAdapter, Activity activity) {
        this.alunosAdapter = alunosAdapter;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunosAdapter.size();
    }

    @Override
    public Object getItem(int i) {
        return alunosAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LinearLayout layout;

        if(view == null) {
            Context ctx = viewGroup.getContext();
            LayoutInflater svc = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new LinearLayout(ctx);
            svc.inflate(R.layout.item_simple_aluno_lista, layout);
        } else {
            layout = (LinearLayout)view;
        }

        Aluno aluno = alunosAdapter.get(i);

        // buscando as referências das views
        TextView nomeAluno = layout.findViewById(R.id.tvNomeAluno);
        TextView dataNascimentoAluno = layout.findViewById(R.id.tvDataNascimentoAluno);

        // "populando" as views
        nomeAluno.setText(aluno.getNome());
        // formatando as datas
        dataNascimentoAluno.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(aluno.getDataNascimento()));

        return layout;
    }
}