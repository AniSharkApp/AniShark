package ru.anishark.app.feature.home.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HomeAnimeListItemDecoration: ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.getChildViewHolder(view).getLayoutPosition();
        parent.getChildViewHolder(view).getAdapterPosition();
        state.getItemCount();
        super.getItemOffsets(outRect, view, parent, state)
    }
}