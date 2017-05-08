package co.infinum.mvpexample.data.interactors.pokedex;

import co.infinum.mvpexample.ui.shared.BaseMvp;

public interface PokedexInteractor extends BaseMvp.Interactor {

    void getPokemons(PokedexListener listener);
}
