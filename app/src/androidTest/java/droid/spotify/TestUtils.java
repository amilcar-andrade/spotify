package droid.spotify;

import java.util.ArrayList;
import java.util.List;

import droid.spotify.data.model.Album;
import droid.spotify.data.model.AlbumItem;
import droid.spotify.data.model.Artist;
import droid.spotify.data.model.ArtistItem;
import droid.spotify.data.model.Envelop;
import droid.spotify.data.model.Image;
import droid.spotify.data.model.SearchCategory;

class TestUtils {

    // SearchCategory
    private static final String SEARCH_CATEGORY_ARTIST_TITLE = "Artists";
    private static final String SEARCH_CATEGORY_ALBUM_TITLE = "Albums";

    // SearchResult fields
    private static final int SEARCH_RESULT_LIMIT = 0;
    private static final int SEARCH_RESULT_OFFSET = 0;
    private static final int SEARCH_RESULT_TOTAL = 0;
    private static final String SEARCH_RESULT_NEXT = "SEARCH_RESULT_NEXT";
    private static final String SEARCH_RESULT_PREV = "SEARCH_RESULT_PREV";

    // SearchItem fields
    private static final String SEARCH_ITEM_ID = "SEARCH_ITEM_ID";
    private static final String SEARCH_ITEM_NAME = "NAME";
    private static final String SEARCH_ITEM_URI = "SEARCH_ITEM_URI";

    // AlbumItem fields
    private static final String ALBUM_ITEM_TYPE = "ALBUM_ITEM_TYPE";
    private static final String ALBUM_TYPE = "ALBUM_TYPE";

    // Artist fields
    private static final String ARTIST_ITEM_HREF = "ARTIST_ITEM_HREF";
    private static final int ARTIST_ITEM_PRIORITY = 0;

    // Image fields
    private static final int IMAGE_W = 100;
    private static final int IMAGE_H = 100;
    private static final String IMAGE_URL = "IMAGE_URL";

    private TestUtils() {
        // utility class
    }

    static SearchCategory<AlbumItem> createAlbumItemSearchCategory() {
        return new SearchCategory<>(SEARCH_CATEGORY_ALBUM_TITLE,
                createAlbumItems(), AlbumItem.class);
    }

    static SearchCategory<ArtistItem> createArtistItemSearchCategory() {
        return new SearchCategory<>(SEARCH_CATEGORY_ARTIST_TITLE,
                createArtistItems(), ArtistItem.class);
    }

    static Envelop createEnvelop() {
        return new Envelop(createAlbum(), createArtist());
    }

    static Album createAlbum() {
        return new Album(SEARCH_RESULT_LIMIT, SEARCH_RESULT_NEXT, SEARCH_RESULT_OFFSET,
                SEARCH_RESULT_PREV, SEARCH_RESULT_TOTAL, createAlbumItems());
    }

    static Artist createArtist() {
        return new Artist(SEARCH_RESULT_LIMIT, SEARCH_RESULT_NEXT, SEARCH_RESULT_OFFSET,
                SEARCH_RESULT_PREV, SEARCH_RESULT_TOTAL, createArtistItems());
    }

    private static List<AlbumItem> createAlbumItems() {
        List<AlbumItem> albumItems = new ArrayList<>(1);
        albumItems.add(createAlbumItem());
        return albumItems;
    }

    static AlbumItem createAlbumItem() {
        return new AlbumItem(SEARCH_ITEM_ID, SEARCH_ITEM_NAME, SEARCH_ITEM_URI, createImages(),
                createArtistItems(), ALBUM_ITEM_TYPE, ALBUM_TYPE);
    }

    private static List<ArtistItem> createArtistItems() {
        List<ArtistItem> artistItems = new ArrayList<>(1);
        artistItems.add(createArtistItem());
        return artistItems;
    }

    static ArtistItem createArtistItem() {
        return createArtistItem(createImages());
    }

    static ArtistItem createArtistItem(List<Image> images) {
        return new ArtistItem(SEARCH_ITEM_ID, SEARCH_ITEM_NAME, SEARCH_ITEM_URI,
                ARTIST_ITEM_HREF, images, ARTIST_ITEM_PRIORITY);
    }

    private static List<Image> createImages() {
        List<Image> images = new ArrayList<>(1);
        images.add(createImage());
        return images;
    }

    static Image createImage() {
        return createImage(IMAGE_H, IMAGE_W, IMAGE_URL);
    }

    static Image createImage(int h, int w, String url) {
        return new Image(h, w, url);
    }
}
