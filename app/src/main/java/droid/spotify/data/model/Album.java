package droid.spotify.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Album extends SearchResult implements Parcelable {
    public final List<AlbumItem> items;

    public Album(int limit, String next, int offset, String previous, int total, List<AlbumItem> items) {
        super(limit, next, offset, previous, total);
        this.items = items;
    }

    /* Parcelable implementation */
    protected Album(Parcel in) {
        super(in.readInt(), in.readString(), in.readInt(), in.readString(), in.readInt());
        this.items = in.createTypedArrayList(AlbumItem.CREATOR);
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

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        if (!super.equals(o)) return false;

        Album album = (Album) o;
        return items != null ? items.equals(album.items) : album.items == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
