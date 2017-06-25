package droid.spotify.ui;

import android.os.Bundle;
import android.spotify.R;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import droid.spotify.background.SearchLoader;
import droid.spotify.data.model.SearchCategory;
import droid.spotify.ui.recycler.SearchAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String BUNDLE_SEARCH_KEY = "BUNDLE_SEARCH_KEY";
    private static final String BUNDLE_IS_LOADING = "BUNDLE_IS_LOADING";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.progress)
    ProgressBar progress;

    private String query = "";
    boolean isLoading = false;
    SearchAdapter adapter;
    Unbinder bind;

    private LoaderManager.LoaderCallbacks<List<SearchCategory>> callbacks = new LoaderManager.LoaderCallbacks<List<SearchCategory>>() {
        @Override
        public Loader<List<SearchCategory>> onCreateLoader(int id, Bundle args) {
            return new SearchLoader(MainActivity.this, args);
        }

        @Override
        public void onLoadFinished(Loader<List<SearchCategory>> loader, List<SearchCategory> data) {
            isLoading = false;
            progress.setVisibility(View.GONE);
            if (data.isEmpty()) {
                empty.setVisibility(View.VISIBLE);
                return;
            }
            empty.setVisibility(View.GONE);
            adapter = new SearchAdapter(MainActivity.this, data);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoaderReset(Loader<List<SearchCategory>> loader) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            query = savedInstanceState.getString(BUNDLE_SEARCH_KEY, "");
            isLoading = savedInstanceState.getBoolean(BUNDLE_IS_LOADING);
        }
        bind = ButterKnife.bind(this);
        setupSearchView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_SEARCH_KEY, query);
        outState.putBoolean(BUNDLE_IS_LOADING, isLoading);
    }

    private void setupSearchView() {
        mSearchView.setIconified(false);
        mSearchView.setQueryHint(getString(R.string.search_hint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchFor(s);
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                // Do not close the search view
                return true;
            }
        });
        if (!TextUtils.isEmpty(query)) {
            mSearchView.setQuery(query, true);
        }
    }

    private void showLoading() {
        progress.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        isLoading = true;
    }

    private void searchFor(String query) {
        if (query == null) {
            query = "";
        }

        showLoading();
        if (TextUtils.equals(query, this.query)) {
            getSupportLoaderManager().initLoader(SearchLoader.LOADER_ID, SearchLoader.createBundle(query), callbacks);
        } else {
            getSupportLoaderManager().restartLoader(SearchLoader.LOADER_ID, SearchLoader.createBundle(query), callbacks);
        }
        this.query = query;
    }
}
