package br.senai.sp.android_fic_escolas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.android_fic_escolas.adapter.AlunoAdapter;
import br.senai.sp.android_fic_escolas.dao.AlunoDao;
import br.senai.sp.android_fic_escolas.model.Aluno;
import br.senai.sp.android_fic_escolas.view.EditActivity;
import br.senai.sp.android_fic_escolas.view.MapsActivity;
import br.senai.sp.android_fic_escolas.view.SobreActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lvAlunos;
    private AlunoAdapter alunoAdapter;
    private AlunoDao dao = AlunoDao.manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // carrega a lista de alunos
        carregarListaAlunos();
    }

    private void carregarListaAlunos() {
        List<Aluno> listarAlunos = dao.getLista();
        lvAlunos = findViewById(R.id.lvAlunos);

        // ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(getApplicationContext(), android.R.layout.simple_list_item_1, listarAlunos);
        // lvAlunos.setAdapter(adapter);
        alunoAdapter = new AlunoAdapter(listarAlunos, this);
        lvAlunos.setAdapter(alunoAdapter);

        // Selecionando um item da lista
//        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Aluno alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
//                // Toast.makeText(getApplicationContext(), alunoSelecionado.toString(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.putExtra("idAluno", alunoSelecionado.getId());
//                startActivityForResult(intent, 1);
//            }
//        });

        // Abrindo um menu de opções para o usuário
        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_aluno_options, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.menuAlunoEditar:
                                Aluno alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
                                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                                intent.putExtra("idAluno", alunoSelecionado.getId());
                                startActivityForResult(intent, 1);
                                break;

                            case R.id.menuAlunoMostrarMapa:
                                alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
                                intent = new Intent(MainActivity.this, MapsActivity.class);
                                intent.putExtra("idAluno", alunoSelecionado.getId());
                                startActivityForResult(intent, 1);
                                break;

                            case R.id.menuAlunoDeletar:
                                alunoSelecionado = (Aluno) adapterView.getAdapter().getItem(i);
                                dao.remover(alunoSelecionado);
                                carregarListaAlunos();
                                break;
                        }
                        return true;
                    }
                });

                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.nav_listagem_alunos:
                closeContextMenu();
                break;
            case R.id.nav_adicionar_aluno:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.nav_configuracoes:
                Intent sobreIntent = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(sobreIntent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                carregarListaAlunos();
                Toast.makeText(getApplicationContext(), "Aluno inserido com sucesso.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
