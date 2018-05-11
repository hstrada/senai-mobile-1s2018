package br.senai.sp.android_fic_escolas_dev.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.senai.sp.android_fic_escolas_dev.model.Aluno;

/**
 * Created by Helena Strada on 28/03/2018.
 */

public class AlunoDaoDB {

    private SQLiteDatabase db;
    private AlunoDbHelper dbo;

    public AlunoDaoDB (Context context) {
        dbo = new AlunoDbHelper(context);
    }

    public void salvar(Aluno aluno) {

        SQLiteDatabase db = dbo.getWritableDatabase();

        /* long resultado;
        ContentValues values = new ContentValues();
        values.put(JogoDbHelper.NOME, jogo.getNome());
        values.put(JogoDbHelper.FABRICANTE, jogo.getFabricante());
        resultado = db.insert(JogoDbHelper.TABELA,
                null,
                values);
        if (resultado == -1) {
            Log.d("Erro ao inserir", "Erro");
            return "Erro ao inserir registro";
        } else {
            Log.d("Sucesso ao inserir", "Sucesso");
            return "Registro inserido com sucesso";
        } */

        if (aluno.getId() == null) {
            String inserir = "insert into "
                    + AlunoDbHelper.TABELA
                    + " (nome, endereco, foto, dt_nascimento) values (?, ?, ?, ?)";
            db.execSQL(inserir, new Object[]{aluno.getNome(), aluno.getEndereco(), aluno.getFotoAluno(), aluno.getDataNascimento()});
//            try {
//                db.execSQL(inserir, new Object[]{aluno.getNome(), aluno.getEndereco(), aluno.getFotoAluno().getBytes("UTF-8"), aluno.getDataNascimento()});
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
        } else {
            String update = "update " + AlunoDbHelper.TABELA + " set nome = ?, endereco = ?, foto = ?, dt_nascimento = ? where _id = ?";
            db.execSQL(update, new Object[]{aluno.getNome(), aluno.getEndereco(), aluno.getFotoAluno(), aluno.getDataNascimento(), aluno.getId()});
//            try {
//                db.execSQL(update, new Object[]{aluno.getNome(), aluno.getEndereco(), aluno.getFotoAluno().getBytes("UTF-8"), aluno.getDataNascimento(), aluno.getId()});
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
        }

        db.close();

    }

    public List<Aluno> getLista() {
        List<Aluno> alunos = new LinkedList<>();
        String rawQuery = "SELECT _id, nome, endereco, foto, dt_nascimento FROM " + AlunoDbHelper.TABELA;
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Aluno aluno = null;
        if (cursor.moveToFirst()) {
            do {
                aluno = new Aluno();
                aluno.setId(cursor.getLong(0));
                aluno.setNome(cursor.getString(1));
                aluno.setEndereco(cursor.getString(2));
                aluno.setFotoAluno(cursor.getString(3));
                aluno.setDataNascimento(new Date(cursor.getString(4)));
                alunos.add(aluno);
            } while (cursor.moveToNext());
        }
        return alunos;
    }

    public Aluno localizar(Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "SELECT _id, nome, endereco, foto, dt_nascimento FROM " + AlunoDbHelper.TABELA + " WHERE _id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Aluno alunoA = new Aluno();
        alunoA.setId(cursor.getLong(0));
        alunoA.setNome(cursor.getString(1));
        alunoA.setEndereco(cursor.getString(2));
        alunoA.setFotoAluno(cursor.getString(3));
        alunoA.setDataNascimento(new Date(cursor.getString(4)));
        db.close();
        return alunoA;
    }

    public void remover(Aluno aluno) {
        SQLiteDatabase db = dbo.getWritableDatabase();

        /* String where = JogoDbHelper.ID + "=" + jogo.getId();
        db.delete(JogoDbHelper.TABELA, where, null); */
        String deletar = "delete from " + AlunoDbHelper.TABELA + " where _id = ?";
        db.execSQL(deletar, new Object[]{aluno.getId()});
        db.close();

    }

}
