package droid.spotify;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import droid.spotify.data.model.Album;
import droid.spotify.data.model.AlbumItem;
import droid.spotify.data.model.Artist;
import droid.spotify.data.model.ArtistItem;
import droid.spotify.data.model.Envelop;
import droid.spotify.data.model.Image;
import droid.spotify.data.model.SearchCategory;

@RunWith(AndroidJUnit4.class)
public class ParcelableTest {

    @Test
    public void envelop() {
        Envelop e = TestUtils.createEnvelop();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Envelop eFromParcel = Envelop.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void searchCategoryAlbumItems() {
        SearchCategory<AlbumItem> sc = TestUtils.createAlbumItemSearchCategory();
        Parcel parcel = Parcel.obtain();
        sc.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        SearchCategory scFromParcel = SearchCategory.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(sc, scFromParcel);
    }

    @Test
    public void searchCategoryArtistItems() {
        SearchCategory<ArtistItem> sc = TestUtils.createArtistItemSearchCategory();
        Parcel parcel = Parcel.obtain();
        sc.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        SearchCategory scFromParcel = SearchCategory.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(sc, scFromParcel);
    }

    @Test
    public void album() {
        Album album = TestUtils.createAlbum();
        Parcel parcel = Parcel.obtain();
        album.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Album albumFromParcel = Album.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(album, albumFromParcel);
    }

    @Test
    public void artist() {
        Artist artist = TestUtils.createArtist();
        Parcel parcel = Parcel.obtain();
        artist.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Artist artistFromParcel = Artist.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(artist, artistFromParcel);
    }

    @Test
    public void albumItem() {
        AlbumItem albumItem = TestUtils.createAlbumItem();
        Parcel parcel = Parcel.obtain();
        albumItem.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        AlbumItem albumItemFromParcel = AlbumItem.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(albumItem, albumItemFromParcel);
    }

    @Test
    public void artistItem() {
        ArtistItem artistItem = TestUtils.createArtistItem();
        Parcel parcel = Parcel.obtain();
        artistItem.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        ArtistItem artistItemFromParcel = ArtistItem.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(artistItem, artistItemFromParcel);
    }

    @Test
    public void image() {
        Image image = TestUtils.createImage();
        Parcel parcel = Parcel.obtain();
        image.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Image imageFromParcel = Image.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(image, imageFromParcel);
    }
}
