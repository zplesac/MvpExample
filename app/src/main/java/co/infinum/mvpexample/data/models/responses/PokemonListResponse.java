package co.infinum.mvpexample.data.models.responses;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

import co.infinum.mvpexample.data.models.Pokemon;

public class PokemonListResponse implements Serializable {

    @Json(name = "pokemon")
    private List<Pokemon> pokemons;

    public PokemonListResponse() {
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PokemonListResponse that = (PokemonListResponse) o;

        return pokemons != null ? pokemons.equals(that.pokemons) : that.pokemons == null;
    }

    @Override
    public int hashCode() {
        return pokemons != null ? pokemons.hashCode() : 0;
    }
}
