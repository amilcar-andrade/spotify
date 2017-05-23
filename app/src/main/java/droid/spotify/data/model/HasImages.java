package droid.spotify.data.model;

import android.util.Range;

public interface HasImages {
    Range<Integer> IMAGE_SMALL = new Range<>(0, 199);
    Range<Integer> IMAGE_MEDIUM = new Range<>(200, 639);
    Range<Integer> IMAGE_LARGE = new Range<>(640, Integer.MAX_VALUE);

    Image getImage(Range<Integer> range);
}
