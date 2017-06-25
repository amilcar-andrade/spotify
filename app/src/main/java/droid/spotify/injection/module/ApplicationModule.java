package droid.spotify.injection.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import droid.spotify.data.api.SpotifyApi;
import droid.spotify.data.api.SpotifyService;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    SpotifyApi providesSpotifyApi(GsonConverterFactory factory) {
        return SpotifyApi.Factory.create(factory);
    }

    @Provides
    @Singleton
    SpotifyService providesSpotifyService(SpotifyApi api) {
        return new SpotifyService(api);
    }
}