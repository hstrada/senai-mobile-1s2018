package sp.senai.br.android_pokemon.dao;

import java.util.ArrayList;
import java.util.List;

import sp.senai.br.android_pokemon.model.PokemonParcelable;

/**
 * Created by helena.strada on 22/01/18.
 */

public class PokemonParcelableDao {

    public static PokemonParcelableDao instance = new PokemonParcelableDao();
    private List<PokemonParcelable> pks;

    private long id = 1;

    private PokemonParcelableDao () {
        pks = new ArrayList<>();
        pks.add(
                new PokemonParcelable(id++, "Bulbasaur", (long) 1, "Grass",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png"));
        pks.add(
                new PokemonParcelable(id++, "Charmander", (long) 4, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png"));
        pks.add(
                new PokemonParcelable(id++, "Charizard", (long) 6, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png"));
        pks.add(
                new PokemonParcelable(id++, "Blastoise", (long) 9, "Water",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/009.png"));
        pks.add(
                new PokemonParcelable(id++, "Pikachu", (long) 25, "Eletric",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/025.png"));
    }

    public PokemonParcelable buscarPorIdParcelable(Long id) {
        try {
            return pks.get(pks.indexOf(new PokemonParcelable(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public PokemonParcelable buscarPorNomeParcelable(String nome) {
        PokemonParcelable pkm = new PokemonParcelable();

        for (PokemonParcelable p : pks) {
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
