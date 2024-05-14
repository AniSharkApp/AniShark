package ru.anishark.app.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpacingItemDecoration(
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
        outRect.left = getPixelsFromDimensityIndependentPixels(startPadding)
        outRect.right = getPixelsFromDimensityIndependentPixels(endPadding)
        when (parent.getChildAdapterPosition(view)) {
            // Первый
            0 -> {
                outRect.top = getPixelsFromDimensityIndependentPixels(topPadding)
            }
            // Последний
            parent.adapter!!.getItemCount() - 1 -> {
                outRect.bottom = getPixelsFromDimensityIndependentPixels(bottomPadding)

            }
            // Иной
            else -> {
                outRect.top = getPixelsFromDimensityIndependentPixels(spacing) / 2
                outRect.bottom = getPixelsFromDimensityIndependentPixels(spacing) / 2
            }
        }
    }
}