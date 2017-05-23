package droid.spotify.data.model;

public abstract class SearchResult  {

    public final int limit;
    public final String next;
    public final int offset;
    public final String previous;
    public final int total;

    public SearchResult(int limit, String next, int offset, String previous, int total) {
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResult)) return false;

        SearchResult that = (SearchResult) o;

        if (limit != that.limit) return false;
        if (offset != that.offset) return false;
        if (total != that.total) return false;
        if (next != null ? !next.equals(that.next) : that.next != null) return false;
        return previous != null ? previous.equals(that.previous) : that.previous == null;

    }

    @Override
    public int hashCode() {
        int result = limit;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + offset;
        result = 31 * result + (previous != null ? previous.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }
}
