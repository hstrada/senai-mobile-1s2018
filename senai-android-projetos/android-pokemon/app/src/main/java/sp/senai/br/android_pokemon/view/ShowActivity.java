package sp.senai.br.android_pokemon.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sp.senai.br.android_pokemon.R;
import sp.senai.br.android_pokemon.dao.PokemonDao;
import sp.senai.br.android_pokemon.model.Pokemon;
import sp.senai.br.android_pokemon.model.PokemonParcelable;
import sp.senai.br.android_pokemon.model.PokemonSerializable;

public class ShowActivity extends AppCompatActivity {

    // modelos dos pokemons
    private PokemonSerializable pokemonSerializable;
    private PokemonParcelable pokemonParcelable;
    private Pokemon pokemon;
    private Long idAchado;

    // dao para instanciar
    private PokemonDao dao = PokemonDao.instance;

    // campos para editar
    private TextView tvResultadoNome;
    private TextView tvResultadoNumero;
    private ImageView ivPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // declarando o que precisamos para carregar na tela
        ivPokemon = findViewById(R.id.ivPokemon);
        tvResultadoNome = findViewById(R.id.tvResultadoNome);
        tvResultadoNumero = findViewById(R.id.tvResultadoNumero);


        // =======================================================================================//

        // 1 -

        /* PROCURAR UM POKEMON POR ID */

        /*
        if (getIntent().getLongExtra("pokemon", 0) != 0) {
            idAchado = Long.valueOf(getIntent().getLongExtra("pokemon", 0));
            pokemon = dao.buscarPorId(idAchado);
        } else {
            pokemon = dao.buscarPorNome(getIntent().getStringExtra("pokemon"));
        }

        if (pokemon.getId() != null) {
            Glide.with(getApplicationContext()).load(pokemon.getUrlImagem()).into(ivPokemon);
            tvResultadoNome.setText(pokemon.getNome().toString());
            tvResultadoNumero.setText(pokemon.getNumero().toString());
        } else {
            Toast.makeText(getApplicationContext(), "Não foi possível encontrar o pokemon."
                    , Toast.LENGTH_LONG).show();
        }
        */


        // =======================================================================================//

        // 2 -

        /* SERIALIZABLE */


        if (getIntent().getSerializableExtra("pokemonAchado") != null) {
            pokemonSerializable = (PokemonSerializable) getIntent().getSerializableExtra("pokemonAchado");
        }
        if (pokemonSerializable != null) {
            Glide.with(getApplicationContext()).load(pokemonSerializable.getUrlImagem()).into(ivPokemon);
            tvResultadoNumero.setText(pokemonSerializable.getNumero().toString());
            tvResultadoNome.setText(pokemonSerializable.getNome());
        } else {
            Toast.makeText(getApplicationContext(), "Pokemon não encontrado.", Toast.LENGTH_LONG).show();
        }



        // =======================================================================================//

        // 3 -

        /* PARCELABLE */

        /*
        if (getIntent().getExtras().getParcelable("pokemonAchadoParcelable") != null) {
            pokemonParcelable = getIntent().getExtras().getParcelable("pokemonAchadoParcelable");
        }
        if (pokemonParcelable != null) {
            Glide.with(getApplicationContext()).load(pokemonParcelable.getUrlImagem()).into(ivPokemon);
            tvResultadoNumero.setText(pokemonParcelable.getNumero().toString());
            tvResultadoNome.setText(pokemonParcelable.getNome());
        } else {
            Toast.makeText(getApplicationContext(), "Pokemon não encontrado.", Toast.LENGTH_LONG).show();
        }
        */

    }


}
