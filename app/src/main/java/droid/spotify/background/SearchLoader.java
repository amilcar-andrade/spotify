package droid.spotify.background;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.spotify.R;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import droid.spotify.SpotifyApplication;
import droid.spotify.data.api.SpotifyService;
import droid.spotify.data.model.Album;
import droid.spotify.data.model.AlbumItem;
import droid.spotify.data.model.Artist;
import droid.spotify.data.model.ArtistItem;
import droid.spotify.data.model.Envelop;
import droid.spotify.data.model.SearchCategory;
import retrofit2.Call;

public class SearchLoader extends AbstractAsyncTaskLoader<List<SearchCategory>> {
    private static final String BUNDLE_KEY_SEARCH = "BUNDLE_KEY_SEARCH";
    private static final String TAG = SearchLoader.class.getSimpleName();
    public static final int LOADER_ID = 1251;

    @Inject
    SpotifyService service;

    private final String albumsTitle;
    private final String artistsTitle;
    private final Call<Envelop> call;

    public SearchLoader(Context context, Bundle args) {
        super(context);
        final Resources resources = context.getResources();
        SpotifyApplication.get(context).getComponent().inject(this);
        albumsTitle = resources.getString(R.string.albums);
        artistsTitle = resources.getString(R.string.artists);
        call = service.search(args.getString(BUNDLE_KEY_SEARCH));
    }

    @VisibleForTesting
    public SearchLoader(Context context) {
        this(context, new Bundle());
    }

    @Override
    protected List<SearchCategory> loadInBackground0() {
        try {
            final Envelop envelop = call.execute().body();
            return createCategories(envelop);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return new ArrayList<>();
        }
    }

    private @NonNull
    List<SearchCategory> createCategories(Envelop envelop) {
        List<SearchCategory> sc = new ArrayList<>();
        if (envelop == null) return sc;

        final Artist artists = envelop.artists;
        final Album albums = envelop.albums;
        if (albums == null && artists == null) return sc;

        if (artists != null && albums != null &&
                artists.items != null && albums.items != null) {
            sc.add(new SearchCategory<>(albumsTitle, albums.items, AlbumItem.class));
            sc.add(new SearchCategory<>(artistsTitle, artists.items, ArtistItem.class));
            return sc;
        }
        return sc;
    }

    @VisibleForTesting
    public List<SearchCategory> createCategoriesForTest(Envelop envelop) {
        return createCategories(envelop);
    }

    @Override
    protected void onReset() {
        super.onReset();
        call.cancel();
    }

    public static Bundle createBundle(String search) {
        final Bundle b = new Bundle(1);
        b.putString(BUNDLE_KEY_SEARCH, search);
        return b;
    }
}
