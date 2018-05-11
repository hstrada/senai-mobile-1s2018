package br.senai.sp.android_fic_escolas_dev.rv.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.text.DateFormat;

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.bd.AlunoDaoDB;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;
import br.senai.sp.android_fic_escolas_dev.rv.adapter.AlunoAdapterRV;
import br.senai.sp.android_fic_escolas_dev.views.FormActivity;
import br.senai.sp.android_fic_escolas_dev.views.MainActivity;
import br.senai.sp.android_fic_escolas_dev.views.MapsActivity;

/**
 * Created by Helena Strada on 10/04/2018.
 */

public class AlunoViewHolderRV extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public final TextView nomeAlunoRv;
    public final TextView dataNascimentoAlunoRv;
    private Long alunoId;
    public final AlunoAdapterRV adapter;


    public AlunoViewHolderRV(View itemView, AlunoAdapterRV adapter) {
        super(itemView);
        this.adapter = adapter;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        nomeAlunoRv = itemView.findViewById(R.id.tvNomeAluno);
        dataNascimentoAlunoRv = itemView.findViewById(R.id.tvDataNascimentoAluno);
    }

    public void preencher(Aluno aluno) {
        alunoId = aluno.getId();
        nomeAlunoRv.setText(aluno.getNome());
        dataNascimentoAlunoRv.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(aluno.getDataNascimento()));
    }

    @Override
    public void onClick(View view) {
        final Activity context = (Activity) view.getContext();
        final Intent intent = new Intent(context, FormActivity.class);
        intent.putExtra("idAluno", alunoId);
        context.startActivityForResult(intent, 1);
    }

    @Override
    public boolean onLongClick(View view) {
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.getMenuInflater().inflate(R.menu.menu_aluno_options, popup.getMenu());

        final Activity context = (Activity) view.getContext();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuAlunoEditar:
                        final Intent intent = new Intent(context, FormActivity.class);
                        intent.putExtra("idAluno", alunoId);
                        context.startActivityForResult(intent, 1);
                        break;

                    case R.id.menuAlunoDeletar:
                        AlunoDaoDB dao = new AlunoDaoDB(context);
                        dao.remover(dao.localizar(alunoId));
                        deletarAluno();
                        break;

                    case R.id.menuAlunoMostrarMapa:
                        Intent intentMapa = new Intent(context, MapsActivity.class);
                        intentMapa.putExtra("idAluno", alunoId);
                        context.startActivity(intentMapa);

                }

                return true;
            }
        });

        popup.show();
        return false;
    }

    public void deletarAluno() {
        adapter.remove(getAdapterPosition());
    }
}
