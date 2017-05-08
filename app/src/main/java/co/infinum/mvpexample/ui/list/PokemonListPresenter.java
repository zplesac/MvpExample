package co.infinum.mvpexample.ui.list;

import java.util.List;

import javax.inject.Inject;

import co.infinum.mvpexample.data.interactors.pokedex.PokedexInteractor;
import co.infinum.mvpexample.data.interactors.pokedex.PokedexListener;
import co.infinum.mvpexample.data.models.Pokemon;
import co.infinum.mvpexample.data.models.responses.PokemonListResponse;

public class PokemonListPresenter implements PokemonListMvp.Presenter {

    private final PokemonListMvp.View view;

    private final PokedexInteractor interactor;

    private List<Pokemon> pokemons;

    @Inject
    public PokemonListPresenter(PokemonListMvp.View view, PokedexInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void init() {
        view.initUi();

        view.showProgress();
        interactor.getPokemons(new PokedexListener() {
            @Override
            public void onPokemonsObtained(PokemonListResponse response) {
                if (response != null && response.getPokemons() != null && !response.getPokemons().isEmpty()) {
                    pokemons = response.getPokemons();
                    view.setPokemonList(pokemons);
                    view.setEmptyViewVisibility(false);
                } else {
                    view.setEmptyViewVisibility(true);
                }

                view.hideProgress();
            }

            @Override
            public void onError(String errorMessage) {
                view.hideProgress();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void onItemClicked(int position, Pokemon item) {
        view.navigateToDetails(pokemons.get(position));
    }

    @Override
    public void cancel() {
        interactor.cancel();
    }
}
