package co.infinum.mvpexample.data.network;

import co.infinum.mvpexample.data.models.responses.PokemonListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/v1/pokedex/1")
    Call<PokemonListResponse> getPokedex();
}
