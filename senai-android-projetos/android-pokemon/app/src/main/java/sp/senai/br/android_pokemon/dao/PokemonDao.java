package sp.senai.br.android_pokemon.dao;

import java.util.ArrayList;
import java.util.List;

import sp.senai.br.android_pokemon.model.Pokemon;

/**
 * Created by helena.strada on 22/01/18.
 */

public class PokemonDao {

    public static PokemonDao instance = new PokemonDao();
    private List<Pokemon> pks;

    private long id = 1;

    private PokemonDao() {
        pks = new ArrayList<>();
        pks.add(
                new Pokemon(id++, "Bulbasaur", (long) 1, "Grass",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png"));
        pks.add(
                new Pokemon(id++, "Charmander", (long) 4, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png"));
        pks.add(
                new Pokemon(id++, "Charizard", (long) 6, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png"));
        pks.add(
                new Pokemon(id++, "Blastoise", (long) 9, "Water",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/009.png"));
        pks.add(
                new Pokemon(id++, "Pikachu", (long) 25, "Eletric",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/025.png"));
    }

    public Pokemon buscarPorId(Long id) {
        try {
            return pks.get(pks.indexOf(new Pokemon(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public Pokemon buscarPorNome(String nome) {
        Pokemon pkm = new Pokemon();

        for (Pokemon p : pks) {
            if (p.getNome().equals(nome)) {
                pkm = p;
                return pkm;
            } else {
                pkm = null;
            }
        }

        return pkm;
    }

}
