package droid.spotify.ui.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CarouselMarginDecoration extends RecyclerView.ItemDecoration {

    private final int margin;

    public CarouselMarginDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int last = parent.getAdapter().getItemCount() - 1;
        outRect.left = margin;
        outRect.right = parent.getChildAdapterPosition(view) == last ? margin: 0;
        outRect.top = margin;
        outRect.bottom = margin;
    }
}