package droid.spotify.ui.recycler;

import android.content.Context;
import android.content.res.Resources;
import android.spotify.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import droid.spotify.data.model.SearchCategory;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CarouselCategoryHolder> {
    private final int sideMargin;

    private final List<SearchCategory> items;
    private final LayoutInflater inflater;
    private Map<SearchCategory, CarouselAdapter> adapters = new HashMap<>();

    public SearchAdapter(Context context, List<SearchCategory> items) {
        final Resources res = context.getResources();
        inflater = LayoutInflater.from(context);
        sideMargin = res.getDimensionPixelOffset(R.dimen.carousel_margin);
        this.items = items == null ? new ArrayList<SearchCategory>() : items;
        setupSearchCategoryAdapters(context, adapters);
    }

    private void setupSearchCategoryAdapters(Context context, Map<SearchCategory, CarouselAdapter> adapters) {
        for (SearchCategory sc : items) {
            adapters.put(sc, new CarouselAdapter(context, sc.items));
        }
    }

    @Override
    public SearchAdapter.CarouselCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CarouselMarginDecoration decoration = new CarouselMarginDecoration(sideMargin);
        final SearchAdapter.CarouselCategoryHolder holder = new SearchAdapter.CarouselCategoryHolder(inflater.inflate(R.layout.carousel_category, parent, false),
                decoration);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LATER: Support showing all of the albums on a vertical rv
                Toast.makeText(v.getContext(), R.string.not_yet, Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.CarouselCategoryHolder holder, int position) {
        bindCarousel(holder, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.carousel;
    }

    private void bindCarousel(SearchAdapter.CarouselCategoryHolder holder, SearchCategory category) {
        holder.title.setText(category.title);
        holder.recyclerView.setAdapter(adapters.get(category));
    }

    static class CarouselCategoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler)
        RecyclerView recyclerView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.more)
        TextView more;

        CarouselCategoryHolder(View itemView, RecyclerView.ItemDecoration decoration) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recyclerView.addItemDecoration(decoration);
        }
    }
}