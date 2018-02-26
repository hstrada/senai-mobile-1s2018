package sp.senai.br.android_todo.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import sp.senai.br.android_todo.R;
import sp.senai.br.android_todo.dao.ItemDao;
import sp.senai.br.android_todo.model.Item;
import sp.senai.br.android_todo.model.Situacao;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tilDescricao;
    private Spinner spSituacao;
    private Button btnSalvar;
    private ItemDao dao = ItemDao.itemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        tilDescricao = findViewById(R.id.tilDescricao);
        spSituacao = findViewById(R.id.spSituacao);
        btnSalvar = findViewById(R.id.btSalvar);

        spSituacao.setAdapter(new ArrayAdapter<Situacao>(this, android.R.layout.simple_list_item_1, Situacao.values()));

        btnSalvar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btSalvar:

                Item item = new Item();
                item.setDescricao(tilDescricao.getEditText().getText().toString());
                Situacao s = (Situacao) spSituacao.getSelectedItem();
                item.setSituacao(s);

                dao.salvar(item);

                // Log.d("btn",tilDescricao.getEditText().getText().toString() + spSituacao.getSelectedItem().toString());

                Log.d("lista: ", dao.getItens().toString());

                break;
        }

    }
}
