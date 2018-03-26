package sp.senai.br.android_pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.regex.Pattern;

import sp.senai.br.android_pokemon.dao.PokemonDao;
import sp.senai.br.android_pokemon.dao.PokemonParcelableDao;
import sp.senai.br.android_pokemon.dao.PokemonSerializableDao;
import sp.senai.br.android_pokemon.model.Pokemon;
import sp.senai.br.android_pokemon.model.PokemonParcelable;
import sp.senai.br.android_pokemon.view.ShowActivity;

public class MainActivity extends AppCompatActivity {

    // Buscando as referências do texto e do botão
    private EditText etPokemon;
    private Button btnBuscar;

    /* UM DAO PARA CADA TIPO DE BUSCA */
    private PokemonDao dao = PokemonDao.instance;
    private PokemonSerializableDao daos = PokemonSerializableDao.instance;
    private PokemonParcelableDao daop = PokemonParcelableDao.instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPokemon = findViewById(R.id.etPokemon);
        btnBuscar = findViewById(R.id.btnBuscar);

        /* AS 3 BUSCAS DE POKEMON */

        // btnBuscar.setOnClickListener(btnMostrarClickListener);
        // btnBuscar.setOnClickListener(btnSerializableClickListener);
        btnBuscar.setOnClickListener(btnParcelableClickListener);

    }

    /*
     *
     * Pega o valor que o usuário digitou
     * Faz uma busca no dao por nome ou id
     *
     */
    // BUSCA UM POKEMON NUMA LISTA SIMPLES
    private View.OnClickListener btnMostrarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String retorno = etPokemon.getText().toString();

            if (retorno.matches("^-?\\d{1,19}$")) {
                long id = Long.parseLong(retorno);
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemon", id);
                startActivity(intent);
            } else if (retorno.matches("[a-zA-Z0-9 *]+$") || retorno.matches("[a-zA-Z*]+$")) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemon", retorno);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "O texto digitado não confere.", Toast.LENGTH_LONG).show();
            }
        }
    };

    // BUSCA UM POKEMON SERIALIZABLE
    private View.OnClickListener btnSerializableClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String retorno = etPokemon.getText().toString();

            if (retorno.matches("^-?\\d{1,19}$")) {
                long id = Long.parseLong(retorno);
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemonAchado", daos.buscarPorIdSerializable(id));
                startActivity(intent);
            } else if (retorno.matches("[a-zA-Z0-9 *]+$") || retorno.matches("[a-zA-Z*]+$")) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemonAchado", daos.buscarPorNomeSerializable(retorno));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "O texto digitado não confere.", Toast.LENGTH_LONG).show();
            }
        }
    };

    // BUSCA UM POKEMON PARCELABLE
    private View.OnClickListener btnParcelableClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String retorno = etPokemon.getText().toString();

            if (retorno.matches("^-?\\d{1,19}$")) {
                long id = Long.parseLong(retorno);
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemonAchadoParcelable", daop.buscarPorIdParcelable(id));
                startActivity(intent);
            } else if (retorno.matches("[a-zA-Z0-9 *]+$") || retorno.matches("[a-zA-Z*]+$")) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("pokemonAchadoParcelable", daop.buscarPorNomeParcelable(retorno));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "O texto digitado não confere.", Toast.LENGTH_LONG).show();
            }
        }
    };

}
