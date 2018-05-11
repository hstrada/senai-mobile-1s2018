package br.senai.sp.android_fic_escolas_dev.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.List;

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.adapter.AlunoAdapter;
import br.senai.sp.android_fic_escolas_dev.bd.AlunoDaoDB;
import br.senai.sp.android_fic_escolas_dev.dao.AlunoDao;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;
import br.senai.sp.android_fic_escolas_dev.rv.adapter.AlunoAdapterRV;

public class MainActivity extends AppCompatActivity {

    private ListView lvListaAlunos;
    private RecyclerView rvListaAlunos;
    // private AlunoDao dao = AlunoDao.manager;
    private List<Aluno> listagemDeAlunos;
    private AlunoAdapter alunoAdapter;
    private AlunoDaoDB dao = new AlunoDaoDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setTitle("Lista de Alunos");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDeFormulario = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intentDeFormulario, 1);
            }
        });

        // fazendo referência das views
        // lvListaAlunos = findViewById(R.id.lvListaAlunos);
        rvListaAlunos = findViewById(R.id.rvListaAlunos);

//        String[] listagemDeAlunos = new String[]{"Helena", "Thales", "Felipe"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listagemDeAlunos);
//        lvListaAlunos.setAdapter(adapter);

        carregarListaDeAlunos();

    }

    private void carregarListaDeAlunos() {
        listagemDeAlunos = dao.getLista();
        rvListaAlunos.setAdapter(new AlunoAdapterRV(listagemDeAlunos, this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListaAlunos.setLayoutManager(layoutManager);
        // alunoAdapter = new AlunoAdapter(listagemDeAlunos, this);
        // lvListaAlunos.setAdapter(alunoAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Selecionando um aluno da lista
//        lvListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Aluno alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
//                // Toast.makeText(getApplicationContext(), alunoSelecionado.toString(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, FormActivity.class);
//                intent.putExtra("idAluno", alunoSelecionado.getId());
//                startActivityForResult(intent, 1);
//            }
//        });

        // Selecionando um aluno da lista com longclick
//        lvListaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
//                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_aluno_options, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.menuAlunoEditar:
//                                Aluno alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
//                                Intent intent = new Intent(MainActivity.this, FormActivity.class);
//                                intent.putExtra("idAluno", alunoSelecionado.getId());
//                                startActivityForResult(intent, 1);
//                                break;
//                            case R.id.menuAlunoDeletar:
//                                alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
//                                criarDialogoComAlunoSelecionado(getApplicationContext(), alunoSelecionado);
//                                break;
//                            case R.id.menuAlunoMostrarMapa:
//                                alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
//                                intent = new Intent(MainActivity.this, MapsActivity.class);
//                                intent.putExtra("idAluno", alunoSelecionado.getId());
//                                startActivity(intent);
//                        }
//                        return true;
//                    }
//                });
//
//                popupMenu.show();
//                return false;
//            }
//        });
    }

    private void criarDialogoComAlunoSelecionado(Context context, final Aluno alunoSelecionado) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dao.remover(alunoSelecionado);
                        carregarListaDeAlunos();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.dialogoDecisao));
        builder.setMessage("Tem certeza que deseja excluir?").setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                carregarListaDeAlunos();
                Toast.makeText(getApplicationContext(), "Aluno inserido com sucesso.", Toast.LENGTH_LONG).show();
            }
        }
    }

}
