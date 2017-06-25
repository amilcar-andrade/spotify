package droid.spotify.data.api;

import android.support.annotation.VisibleForTesting;

import javax.inject.Singleton;

import droid.spotify.data.model.Envelop;
import retrofit2.Call;

@Singleton
public class SpotifyService {
    private static final int SEARCH_LIMIT = 10;
    private final SpotifyApi api;

    public SpotifyService(SpotifyApi api) {
        this.api = api;
    }

    public Call<Envelop> search(String q) {
        return api.search(q, SEARCH_LIMIT);
    }

    @VisibleForTesting
    public int getSearchLimit() {
        return SEARCH_LIMIT;
    }
}
