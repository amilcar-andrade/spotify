package droid.spotify.background;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * AbstractAsyncTaskLoader
 *
 * https://medium.com/google-developers/making-loading-data-on-android-lifecycle-aware-897e12760832
 */
public abstract class AbstractAsyncTaskLoader<T> extends AsyncTaskLoader<T> {
    private static final String TAG = AbstractAsyncTaskLoader.class.getSimpleName();
    T data;

    public AbstractAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public final T loadInBackground() {
        data = loadInBackground0();
        return data;
    }

    @Override
    protected void onStartLoading() {
        if (data != null) {
            deliverResult(data);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(T data) {
        this.data = data;
        super.deliverResult(data);
    }

    protected abstract T loadInBackground0();
}