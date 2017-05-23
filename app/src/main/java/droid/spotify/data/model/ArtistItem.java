package droid.spotify.data.model;

import android.os.Parcel;

import java.util.List;

public class ArtistItem extends SearchItem  {

    public final String href;
    public final int popularity;

    public ArtistItem(String id, String name, String uri, String href, List<Image> images, int popularity) {
        super(id, name, uri, images);
        this.href = href;
        this.popularity = popularity;
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
        dest.writeString(this.href);
        dest.writeInt(this.popularity);
    }

    protected ArtistItem(Parcel in) {
        super(in.readString(), in.readString(), in.readString(), in.createTypedArrayList(Image.CREATOR));
        this.href = in.readString();
        this.popularity = in.readInt();
    }

    public static final Creator<ArtistItem> CREATOR = new Creator<ArtistItem>() {
        @Override
        public ArtistItem createFromParcel(Parcel source) {
            return new ArtistItem(source);
        }

        @Override
        public ArtistItem[] newArray(int size) {
            return new ArtistItem[size];
        }
    };

    @Override
    public String getDescription() {
        return "Popularity of " + String.valueOf(popularity) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtistItem)) return false;
        if (!super.equals(o)) return false;

        ArtistItem that = (ArtistItem) o;

        if (popularity != that.popularity) return false;
        return href != null ? href.equals(that.href) : that.href == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + popularity;
        return result;
    }
}
