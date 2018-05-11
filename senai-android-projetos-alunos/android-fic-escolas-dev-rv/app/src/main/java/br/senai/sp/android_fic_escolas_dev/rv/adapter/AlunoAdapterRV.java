package br.senai.sp.android_fic_escolas_dev.rv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;
import br.senai.sp.android_fic_escolas_dev.rv.holder.AlunoViewHolderRV;

/**
 * Created by Helena Strada on 10/04/2018.
 */

public class AlunoAdapterRV extends RecyclerView.Adapter {

    private List<Aluno> alunos;
    private Context context;

    public AlunoAdapterRV(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_simple_aluno_lista, parent, false);
        AlunoViewHolderRV holder = new AlunoViewHolderRV(view, this);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlunoViewHolderRV viewHolder = (AlunoViewHolderRV) holder;

        Aluno aluno = alunos.get(position);

        ((AlunoViewHolderRV) holder).preencher(aluno);

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public void remove(int position) {
        alunos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
