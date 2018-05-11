package br.senai.sp.android_fic_escolas_dev.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Helena Strada on 28/03/2018.
 */

public class AlunoDbHelper extends SQLiteOpenHelper {

    // propriedades para o nosso banco de dados
    private static final String NOME_BANCO = "alunos.db";
    public static final String TABELA = "alunos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String FOTO = "foto";
    public static final String DATANASCIMENTO = "dt_nascimento";
    private static final int VERSAO = 1;

    // definimos o nome do nosso banco e qual script queremos executar no início da nossa aplicação
    public AlunoDbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // script para a criação da nossa tabela
        String criarBD = "CREATE TABLE " + TABELA + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOME + " TEXT,"
                + ENDERECO + " TEXT,"
                + DATANASCIMENTO + " TEXT,"
                + FOTO + " TEXT)";
        sqLiteDatabase.execSQL(criarBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // script para atualização
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);

    }
}
