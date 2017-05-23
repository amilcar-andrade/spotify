package droid.spotify.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Artist extends SearchResult implements Parcelable {
    public final List<ArtistItem> items;

    public Artist(int limit, String next, int offset, String previous, int total, List<ArtistItem> items) {
        super(limit, next, offset, previous, total);
        this.items = items;
    }

    /* Parcelable implementation */
    protected Artist(Parcel in) {
        super(in.readInt(), in.readString(), in.readInt(), in.readString(), in.readInt());
        this.items = in.createTypedArrayList(ArtistItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.limit);
        dest.writeString(this.next);
        dest.writeInt(this.offset);
        dest.writeString(this.previous);
        dest.writeInt(this.total);
        dest.writeTypedList(this.items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        if (!super.equals(o)) return false;

        Artist artist = (Artist) o;
        return items != null ? items.equals(artist.items) : artist.items == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
