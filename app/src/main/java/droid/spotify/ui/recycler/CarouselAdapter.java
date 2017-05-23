package droid.spotify.ui.recycler;

import android.content.Context;
import android.spotify.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import droid.spotify.data.model.HasImages;
import droid.spotify.data.model.Image;
import droid.spotify.data.model.SearchItem;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<SearchItem> items;
    private final LayoutInflater layoutInflater;

    public CarouselAdapter(Context context, List<SearchItem> items) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.empty_item:
                return new EmptyHolder(layoutInflater.inflate(R.layout.empty_item, parent, false));
            case R.layout.carousel:
                return new CarouselHolder(layoutInflater.inflate(R.layout.carousel, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CarouselHolder) {
            ((CarouselHolder) holder).bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items == null || items.isEmpty() ? 1 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items == null || items.isEmpty() ? R.layout.empty_item : R.layout.carousel;
    }

    static class CarouselHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.art)
        ImageView imageView;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.title)
        TextView title;

        CarouselHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(SearchItem model) {
            description.setText(model.getDescription());
            title.setText(model.name);
            final Image image = model.getImage(HasImages.IMAGE_MEDIUM);
            if (image == null) {
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.image_not_found));
                return;
            }
            Glide.with(imageView.getContext())
                    .load(image.url)
                    .placeholder(R.drawable.image_not_found)
                    .into(imageView);
        }
    }

     static class EmptyHolder extends RecyclerView.ViewHolder {
        TextView empty;

        EmptyHolder(View itemView) {
            super(itemView);
            empty = (TextView) itemView;
        }
    }
}
