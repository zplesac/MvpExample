package co.infinum.mvpexample.data.interactors.pokedex;

import dagger.Module;
import dagger.Provides;

@Module
public class PokedexInteractorModule {

    @Provides
    public PokedexInteractor provideInteractor(PokedexInteractorImpl interactor) {
        return interactor;
    }
}
