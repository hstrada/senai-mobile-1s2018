package br.senai.sp.android_livros_busca.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.senai.sp.android_livros_busca.R;
import br.senai.sp.android_livros_busca.model.Livro;
import br.senai.sp.android_livros_busca.view.holder.LivroViewHolder;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class LivroAdapter extends RecyclerView.Adapter {

    private final List<Livro> livrosAdapter;
    private final Activity activity;
    private ArrayList<Livro> livrosLista;

    public LivroAdapter(List<Livro> livrosAdapter, Activity activity) {
        this.livrosAdapter = livrosAdapter;
        this.activity = activity;
        this.livrosLista = new ArrayList<>();
        this.livrosLista.addAll(livrosAdapter);
    }

    // filtrando por nome
    public void filtrarPorNome(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        livrosAdapter.clear();
        if (charText.length() == 0) {
            livrosAdapter.addAll(livrosLista);
        } else {
            for (Livro l : livrosLista) {
                if (l.getNome().toLowerCase(Locale.getDefault()).contains(charText)) {
                    livrosAdapter.add(l);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.livro_item_adapter, parent, false);
        LivroViewHolder holder = new LivroViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LivroViewHolder viewHolder = (LivroViewHolder) holder;
        Livro livros = livrosAdapter.get(position);
        viewHolder.preencher(livros);
    }

    @Override
    public int getItemCount() {
        return livrosAdapter.size();
    }
}
