package co.infinum.mvpexample.data.interactors.pokedex;

import java.io.IOException;

import javax.inject.Inject;

import co.infinum.mvpexample.R;
import co.infinum.mvpexample.data.managers.StringManager;
import co.infinum.mvpexample.data.models.responses.PokemonListResponse;
import co.infinum.mvpexample.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PokedexInteractorImpl implements PokedexInteractor {

    private final ApiService apiService;

    private final StringManager stringManager;

    protected boolean isCancelled = false;

    private Call<PokemonListResponse> call;

    @Inject
    public PokedexInteractorImpl(ApiService apiService, StringManager stringManager) {
        this.apiService = apiService;
        this.stringManager = stringManager;
    }

    @Override
    public void getPokemons(final PokedexListener listener) {
        reset();
        call = apiService.getPokedex();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful()) {
                    listener.onPokemonsObtained(response.body());
                } else {
                    try {
                        listener.onError(response.errorBody().string());
                    } catch (IOException e) {
                        Timber.e(e);
                        listener.onError(stringManager.getString(R.string.error_generic));
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                if (!isCancelled) {
                    listener.onError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void cancel() {
        isCancelled = true;
        call.cancel();
    }

    @Override
    public void reset() {
        isCancelled = false;
    }
}
