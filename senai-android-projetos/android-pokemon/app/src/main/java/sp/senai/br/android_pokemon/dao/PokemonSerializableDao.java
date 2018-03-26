package sp.senai.br.android_pokemon.dao;

import java.util.ArrayList;
import java.util.List;

import sp.senai.br.android_pokemon.model.PokemonSerializable;

/**
 * Created by helena.strada on 22/01/18.
 */

public class PokemonSerializableDao {

    public static PokemonSerializableDao instance = new PokemonSerializableDao();
    private List<PokemonSerializable> pks;

    private long id = 1;

    private PokemonSerializableDao () {
        pks = new ArrayList<>();
        pks.add(
                new PokemonSerializable(id++, "Bulbasaur", (long) 1, "Grass",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png"));
        pks.add(
                new PokemonSerializable(id++, "Charmander", (long) 4, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png"));
        pks.add(
                new PokemonSerializable(id++, "Charizard", (long) 6, "Fire",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png"));
        pks.add(
                new PokemonSerializable(id++, "Blastoise", (long) 9, "Water",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/009.png"));
        pks.add(
                new PokemonSerializable(id++, "Pikachu", (long) 25, "Eletric",
                        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/025.png"));
    }

    public PokemonSerializable buscarPorIdSerializable(Long id) {
        try {
            return pks.get(pks.indexOf(new PokemonSerializable(id)));
        } catch (Exception e) {
            return null;
        }
    }

    public PokemonSerializable buscarPorNomeSerializable(String nome) {
        PokemonSerializable pkm = new PokemonSerializable();

        for (PokemonSerializable p : pks) {
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
