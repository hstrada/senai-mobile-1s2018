package br.senai.sp.android_livros_busca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.android_livros_busca.view.EditActivity;
import br.senai.sp.android_livros_busca.view.adapter.LivroAdapter;
import br.senai.sp.android_livros_busca.dao.LivroDao;
import br.senai.sp.android_livros_busca.model.Livro;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lvLivros;
    private SearchView svLivros;
    private LivroAdapter livroAdapter;

    private RecyclerView rvLivros;

    private LivroDao dao = LivroDao.manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, EditActivity.class);
               startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvLivros = findViewById(R.id.rvLivros);
        svLivros = findViewById(R.id.svLivros);

        carregarInformacoes();
        buscarSearchView();
    }

    private void buscarSearchView() {
        svLivros.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // recebo a String que quero buscar
                String livroBuscado = s;
                // coloco um filtro no próprio adapter
                livroAdapter.filtrarPorNome(livroBuscado);
                return false;
            }
        });
    }

    private void carregarInformacoes() {

        List<Livro> livros = dao.getLista();

        livroAdapter = new LivroAdapter(livros, this);
        rvLivros.setAdapter(livroAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvLivros.setLayoutManager(layoutManager);

        rvLivros.setNestedScrollingEnabled(false);

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
//        rvLivros.setLayoutManager(layoutManager);

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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_gallery:
                break;
            case R.id.nav_manage:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                carregarInformacoes();
                Toast.makeText(getApplicationContext(), "Livro inserido com sucesso.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
