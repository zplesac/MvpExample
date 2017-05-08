package co.infinum.mvpexample.di.modules;

import android.content.Context;

import co.infinum.mvpexample.MvpExampleApp;
import co.infinum.mvpexample.di.qualifiers.AppContext;
import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    @Provides
    @AppContext
    public Context provideAppContext() {
        return MvpExampleApp.getInstance();
    }
}
