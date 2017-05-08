package co.infinum.mvpexample.ui.list;

import java.util.List;

import co.infinum.mvpexample.data.models.Pokemon;
import co.infinum.mvpexample.ui.shared.BaseMvp;

public interface PokemonListMvp {

    interface View extends BaseMvp.View {

        void initUi();

        void setPokemonList(List<Pokemon> pokemons);

        void setEmptyViewVisibility(boolean isVisible);

        void navigateToDetails(Pokemon pokemon);
    }

    interface Presenter extends BaseMvp.Presenter {

        void init();

        void onItemClicked(int position, Pokemon item);
    }
}
