package co.infinum.mvpexample.di.modules;

import javax.inject.Singleton;

import co.infinum.mvpexample.BuildConfig;
import co.infinum.mvpexample.data.managers.MoshiProvider;
import co.infinum.mvpexample.data.network.ApiService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("Retrofit").d(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(loggingInterceptor);
        return okHttpBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create()) // if we don't add this, strings get serialized with extra quotes!
                .addConverterFactory(MoshiConverterFactory.create(MoshiProvider.getInstance().getMoshi()))
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient);
        return retrofitBuilder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
