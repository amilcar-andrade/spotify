package droid.spotify.injection.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import droid.spotify.background.SearchLoader;
import droid.spotify.data.api.SpotifyService;
import droid.spotify.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Application app);
    void inject(SearchLoader loader);

    SpotifyService spotifyService();
}