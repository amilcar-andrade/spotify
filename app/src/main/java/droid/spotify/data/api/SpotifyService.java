package droid.spotify.data.api;

import android.support.annotation.VisibleForTesting;

import droid.spotify.data.model.Envelop;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyService {
    private static final int SEARCH_LIMIT = 10;
    private static final SpotifyService INSTANCE = new SpotifyService();
    private final SpotifyApi api;

    private SpotifyService() {
        api = new Retrofit.Builder()
                .baseUrl(SpotifyApi.ENDPOINT)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SpotifyApi.class);
    }

    public static SpotifyService getInstance() {
        return INSTANCE;
    }

    public Call<Envelop> search(String q) {
        return api.search(q, SEARCH_LIMIT);
    }

    @VisibleForTesting
    public int getSearchLimit() {
        return SEARCH_LIMIT;
    }
}
