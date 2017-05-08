package co.infinum.mvpexample.di.components;

import javax.inject.Singleton;

import co.infinum.mvpexample.MvpExampleApp;
import co.infinum.mvpexample.di.modules.ApiModule;
import co.infinum.mvpexample.di.modules.AppContextModule;
import co.infinum.mvpexample.di.modules.ProvidersModule;
import co.infinum.mvpexample.ui.list.PokemonListDi;
import co.infinum.mvpexample.ui.list.PokemonListMvp;
import dagger.Component;

@Component(modules = {
        AppContextModule.class,
        ApiModule.class,
        ProvidersModule.class
})
@Singleton
public interface AppComponent {

    void inject(MvpExampleApp app);

    PokemonListDi.Component plus(PokemonListDi.Module module);
}
