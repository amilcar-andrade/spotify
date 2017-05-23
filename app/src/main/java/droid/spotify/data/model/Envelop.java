package droid.spotify.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Envelop implements Parcelable {
    public final Album albums;
    public final Artist artists;

    public Envelop(Album albums, Artist artists) {
        this.albums = albums;
        this.artists = artists;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.albums, flags);
        dest.writeParcelable(this.artists, flags);
    }

    protected Envelop(Parcel in) {
        this.albums = in.readParcelable(Album.class.getClassLoader());
        this.artists = in.readParcelable(Artist.class.getClassLoader());
    }

    public static final Creator<Envelop> CREATOR = new Creator<Envelop>() {
        @Override
        public Envelop createFromParcel(Parcel source) {
            return new Envelop(source);
        }

        @Override
        public Envelop[] newArray(int size) {
            return new Envelop[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Envelop)) return false;

        Envelop envelop = (Envelop) o;
        if (albums != null ? !albums.equals(envelop.albums) : envelop.albums != null) return false;
        return artists != null ? artists.equals(envelop.artists) : envelop.artists == null;

    }

    @Override
    public int hashCode() {
        int result = albums != null ? albums.hashCode() : 0;
        result = 31 * result + (artists != null ? artists.hashCode() : 0);
        return result;
    }
}
