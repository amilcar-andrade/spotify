package droid.spotify;

import android.app.Application;
import android.content.Context;

import droid.spotify.injection.component.ApplicationComponent;
import droid.spotify.injection.component.DaggerApplicationComponent;
import droid.spotify.injection.module.ApplicationModule;

public class SpotifyApplication extends Application {
    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }

    public static SpotifyApplication get(Context context) {
        return (SpotifyApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
