package droid.spotify.data.model;

import android.os.Parcelable;
import android.util.Range;

import java.util.List;

/**
 * Base model of a search/spotify item
 */
public abstract class SearchItem implements Parcelable, HasDescription, HasImages {

    public final String id;
    public final String name;
    public final String uri;
    public final List<Image> images;

    public SearchItem(String id, String name, String uri, List<Image> images) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.images = images;
    }

    static Creator<? extends  SearchItem> newCreator(String type) {
        if (ArtistItem.class.getCanonicalName().equals(type)) {
            return ArtistItem.CREATOR;
        } else {
            return AlbumItem.CREATOR;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchItem)) return false;

        SearchItem that = (SearchItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
        return images != null ? images.equals(that.images) : that.images == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

    @Override
    public Image getImage(Range<Integer> range) {
        if (range == null || images == null || images.isEmpty()) {
            return null;
        }

        for (Image i : images) {
            if (range.contains((i.width + i.height) / 2)) {
                return i;
            }
        }
        // Just return the first image
        return images.get(0);
    }
}
