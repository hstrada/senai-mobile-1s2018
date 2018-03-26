package br.senai.sp.android_livros_busca.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.android_livros_busca.MainActivity;
import br.senai.sp.android_livros_busca.R;
import br.senai.sp.android_livros_busca.dao.LivroDao;
import br.senai.sp.android_livros_busca.model.Livro;
import br.senai.sp.android_livros_busca.view.EditActivity;
import br.senai.sp.android_livros_busca.view.adapter.LivroAdapter;

/**
 * Created by Helena Strada on 16/03/2018.
 */

public class LivroViewHolder extends RecyclerView.ViewHolder  {

    public final TextView nome;
    public final TextView autor;
    public final ImageView capa;
    private Long livroId;
    public final LivroAdapter adapter;
    private LivroDao dao = LivroDao.manager;

    public LivroViewHolder(final View view, final LivroAdapter adapter) {
        super(view);
        this.adapter = adapter;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity)view.getContext();
                final Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("livroId", livroId);
                context.startActivityForResult(intent, 1);
                // context.startActivity(intent);
            }
        });

        nome = view.findViewById(R.id.tvNomeLivro);
        autor = view.findViewById(R.id.tvAutorLivro);
        capa = view.findViewById(R.id.ivCapaLivro);

    }

    public void preencher(Livro livro) {
        livroId = livro.getId();
        nome.setText(livro.getNome());
        autor.setText(livro.getAutor());

        if (livro.getCapa() != null) {
            byte[] encodeByte = livro.getCapa();
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            capa.setImageBitmap(bitmap);
        }
    }

}
