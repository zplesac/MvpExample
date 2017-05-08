package co.infinum.mvpexample;

import android.app.Application;

import co.infinum.mvpexample.di.components.AppComponent;
import co.infinum.mvpexample.di.components.DaggerAppComponent;
import timber.log.Timber;

public class MvpExampleApp extends Application {

    private static MvpExampleApp instance;

    private static void setInstance(MvpExampleApp instance) {
        MvpExampleApp.instance = instance;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);

        Timber.plant(new Timber.DebugTree());
    }

    public static MvpExampleApp getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
