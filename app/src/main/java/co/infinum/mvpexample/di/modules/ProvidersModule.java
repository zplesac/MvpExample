package co.infinum.mvpexample.di.modules;

import android.content.Context;

import co.infinum.mvpexample.data.managers.AndroidStringManager;
import co.infinum.mvpexample.data.managers.StringManager;
import co.infinum.mvpexample.di.qualifiers.AppContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ProvidersModule {

    @Provides
    public StringManager provideStringManager(@AppContext Context context){
        return new AndroidStringManager(context);
    }
}
