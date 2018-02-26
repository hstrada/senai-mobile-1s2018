package sp.senai.br.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lvContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContatos = findViewById(R.id.lvContatos);

        String[] contatos = new String[] {"Helena", "Mateus", "Gustavo", "Wilson", "João", "Maria"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);

        lvContatos.setAdapter(adapter);

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valor = (String) parent.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), valor, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
