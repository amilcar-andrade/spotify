package droid.spotify.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public final class SearchCategory<T extends SearchItem> implements Parcelable {
    public final String title;
    public final List<T> items;
    private final String itemType;

    public SearchCategory(String title, List<T> items, Class itemType) {
        this.title = title;
        this.items = items;
        this.itemType = itemType.getCanonicalName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemType);
        dest.writeString(this.title);
        dest.writeTypedList(this.items);
    }

    public boolean isEmpty() {
        return items == null ||  items.isEmpty();
    }

    protected SearchCategory(Parcel in) {
        this.itemType = in.readString();
        this.title = in.readString();
        this.items = (List<T>)in.createTypedArrayList(SearchItem.newCreator(itemType));
    }

    public static final Creator<SearchCategory> CREATOR = new Creator<SearchCategory>() {
        @Override
        public SearchCategory createFromParcel(Parcel source) {
            return new SearchCategory(source);
        }

        @Override
        public SearchCategory[] newArray(int size) {
            return new SearchCategory[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchCategory)) return false;

        SearchCategory<?> that = (SearchCategory<?>) o;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        return itemType != null ? itemType.equals(that.itemType) : that.itemType == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        return result;
    }
}
