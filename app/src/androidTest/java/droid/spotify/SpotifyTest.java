package droid.spotify;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import droid.spotify.background.SearchLoader;
import droid.spotify.data.api.SpotifyService;
import droid.spotify.data.model.AlbumItem;
import droid.spotify.data.model.ArtistItem;
import droid.spotify.data.model.Envelop;
import droid.spotify.data.model.HasImages;
import droid.spotify.data.model.Image;
import droid.spotify.data.model.SearchCategory;

import static org.junit.Assert.*;

/**
 * TODO: Split tests into different classes
 */
@RunWith(AndroidJUnit4.class)
public class SpotifyTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("android.spotify", appContext.getPackageName());
    }

    /*
     * Asserts properly init of a singleton
     */
    @Test
    public void serviceSingleton() throws Exception {
        final SpotifyService expected = SpotifyService.getInstance();
        assertTrue(expected == SpotifyService.getInstance());
    }

    /*
     * Asserts search limit of a service
    */
    @Test
    public void serviceSearchLimit() throws Exception {
        final SpotifyService s = SpotifyService.getInstance();
        assertEquals(10, s.getSearchLimit());
    }

    /**
     * Asserts the implementation of HasDescription of the AlbumItem class
     */
    @Test
    public void albumItemHasDescription() {
        AlbumItem item = TestUtils.createAlbumItem();
        Assert.assertEquals("NAME", item.getDescription());
    }

    /**
     * Asserts the implementation of HasDescription of the ArtistItem class
     */
    @Test
    public void artistItemHasDescription() {
        ArtistItem item = TestUtils.createArtistItem();
        Assert.assertEquals("Popularity of 0%", item.getDescription());
    }

    /**
     * Asserts that we return null if the 'images' field is null
     */
    @Test
    public void imagesNull() {
        ArtistItem item = TestUtils.createArtistItem(null);
        Assert.assertNull(item.getImage(HasImages.IMAGE_MEDIUM));
        Assert.assertNull(item.getImage(HasImages.IMAGE_SMALL));
        Assert.assertNull(item.getImage(HasImages.IMAGE_LARGE));
    }

    /**
     * Asserts that we return null if the 'images' field is an empty list
     */
    @Test
    public void imagesEmpty() {
        ArtistItem item = TestUtils.createArtistItem(new ArrayList<Image>());
        Assert.assertNull(item.getImage(HasImages.IMAGE_MEDIUM));
        Assert.assertNull(item.getImage(HasImages.IMAGE_SMALL));
        Assert.assertNull(item.getImage(HasImages.IMAGE_LARGE));
    }

    /**
     * Asserts that we return the large image
     */
    @Test
    public void imageRanges() {
        Image large = TestUtils.createImage(650, 650, "large");
        Image medium = TestUtils.createImage(300, 300, "medium");
        Image small = TestUtils.createImage(100, 100, "small");
        ArtistItem item = TestUtils.createArtistItem(Arrays.asList(large, medium, small));
        Image actualLargeImage = item.getImage(HasImages.IMAGE_LARGE);
        Image actualMediumImage = item.getImage(HasImages.IMAGE_MEDIUM);
        Image actualSmallImage = item.getImage(HasImages.IMAGE_SMALL);
        Assert.assertEquals(large, actualLargeImage);
        Assert.assertEquals(medium, actualMediumImage);
        Assert.assertEquals(small, actualSmallImage);

        // Null range
        Image nullImage = item.getImage(null);
        Assert.assertNull(nullImage);
    }

    @Test
    public void searchLoader() {
        Context context = InstrumentationRegistry.getTargetContext();
        SearchLoader loader = new SearchLoader(context);
        Envelop envelop = TestUtils.createEnvelop();
        List<SearchCategory> categories = loader.createCategoriesForTest(envelop);
        Assert.assertTrue(categories.size() == 2);

        SearchCategory albums = categories.get(0);
        SearchCategory artists = categories.get(1);
        Assert.assertEquals(TestUtils.createAlbumItemSearchCategory(), albums);
        Assert.assertEquals(TestUtils.createArtistItemSearchCategory(), artists);

        // Null envelop returns an empty list
        categories = loader.createCategoriesForTest(null);
        Assert.assertEquals(categories, new ArrayList<SearchCategory>());
    }
}
