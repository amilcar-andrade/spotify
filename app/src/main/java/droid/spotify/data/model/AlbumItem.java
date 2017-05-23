package droid.spotify.data.model;
import android.os.Parcel;

import java.util.List;

public class AlbumItem extends SearchItem {
    public final String album_type;
    public final String type;
    public final List<ArtistItem> artists;

    public AlbumItem(String id, String name, String uri, List<Image> images,
                     List<ArtistItem> artists, String album_type, String type) {
        super(id, name, uri, images);
        this.album_type = album_type;
        this.type = type;
        this.artists = artists;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.uri);
        dest.writeTypedList(this.images);
        dest.writeTypedList(this.artists);
        dest.writeString(this.album_type);
        dest.writeString(this.type);
    }

    protected AlbumItem(Parcel in) {
        super(in.readString(), in.readString(), in.readString(), in.createTypedArrayList(Image.CREATOR));
        this.artists = in.createTypedArrayList(ArtistItem.CREATOR);
        this.album_type = in.readString();
        this.type = in.readString();
    }

    public static final Creator<AlbumItem> CREATOR = new Creator<AlbumItem>() {
        @Override
        public AlbumItem createFromParcel(Parcel source) {
            return new AlbumItem(source);
        }

        @Override
        public AlbumItem[] newArray(int size) {
            return new AlbumItem[size];
        }
    };

    @Override
    public String getDescription() {
        return artists.isEmpty() ? "" : artists.get(0).name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumItem)) return false;
        if (!super.equals(o)) return false;

        AlbumItem item = (AlbumItem) o;

        if (album_type != null ? !album_type.equals(item.album_type) : item.album_type != null)
            return false;
        if (type != null ? !type.equals(item.type) : item.type != null) return false;
        return artists != null ? artists.equals(item.artists) : item.artists == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (album_type != null ? album_type.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (artists != null ? artists.hashCode() : 0);
        return result;
    }
}
