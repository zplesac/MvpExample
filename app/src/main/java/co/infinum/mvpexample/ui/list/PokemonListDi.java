package co.infinum.mvpexample.ui.list;

import co.infinum.mvpexample.data.interactors.pokedex.PokedexInteractorModule;
import dagger.Provides;

public interface PokemonListDi {

    @dagger.Subcomponent(modules = Module.class)
    interface Component {

        void inject(PokemonListActivity activity);
    }

    @dagger.Module(includes = {
            PokedexInteractorModule.class
    })
    class Module {

        private PokemonListMvp.View view;

        public Module(PokemonListMvp.View view) {
            this.view = view;
        }

        @Provides
        public PokemonListMvp.View provideView() {
            return view;
        }

        @Provides
        public PokemonListMvp.Presenter providePresenter(PokemonListPresenter presenter) {
            return presenter;
        }
    }
}
