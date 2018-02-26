package sp.senai.br.android_todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import sp.senai.br.android_todo.activity.ItemActivity;
import sp.senai.br.android_todo.dao.ItemDao;
import sp.senai.br.android_todo.model.Item;

public class MainActivity extends AppCompatActivity {

    private ItemDao dao = ItemDao.itemDao;
    private ListView lvItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvItens = findViewById(R.id.lvItens);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this
                , android.R.layout.simple_list_item_1
                , dao.getItens());

        lvItens.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
