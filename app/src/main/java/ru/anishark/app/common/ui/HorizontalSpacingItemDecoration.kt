package ru.anishark.app.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingItemDecoration(
    private val startPadding: Float,
    private val endPadding: Float,
    private val topPadding: Float,
    private val bottomPadding: Float,
    private val spacing: Float
): SpacingItemDecoration(startPadding, endPadding, topPadding, bottomPadding) {

    constructor(
        allSidesPadding: Float,
        spacing: Float
    ) : this(
        startPadding = allSidesPadding,
        endPadding = allSidesPadding,
        topPadding = allSidesPadding,
        bottomPadding = allSidesPadding,
        spacing = spacing
    )

    constructor(
        verticalPadding: Float,
        horizontalPadding: Float,
        spacing: Float
    ) : this(
        startPadding = horizontalPadding,
        endPadding = horizontalPadding,
        topPadding = verticalPadding,
        bottomPadding = verticalPadding,
        spacing = spacing
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = parent.adapter ?: return
        outRect.top = getPixelsFromDimensityIndependentPixels(topPadding)
        outRect.bottom = getPixelsFromDimensityIndependentPixels(bottomPadding)
        val position = parent.getChildAdapterPosition(view)
        when (position) {
            // Первый
            0 -> {
                outRect.left = getPixelsFromDimensityIndependentPixels(startPadding)
            }
            // Последний
            adapter.getItemCount() - 1 -> {
                outRect.right = getPixelsFromDimensityIndependentPixels(endPadding)

            }
            // Иной
            else -> {
                outRect.left = getPixelsFromDimensityIndependentPixels(spacing) / 2
                outRect.right = getPixelsFromDimensityIndependentPixels(spacing) / 2
            }
        }
    }
}