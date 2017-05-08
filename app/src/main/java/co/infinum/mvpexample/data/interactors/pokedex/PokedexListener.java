package co.infinum.mvpexample.data.interactors.pokedex;

import co.infinum.mvpexample.data.models.responses.PokemonListResponse;
import co.infinum.mvpexample.ui.shared.BaseMvp;

public interface PokedexListener extends BaseMvp.ErrorListener {

    void onPokemonsObtained(PokemonListResponse pokemons);
}
