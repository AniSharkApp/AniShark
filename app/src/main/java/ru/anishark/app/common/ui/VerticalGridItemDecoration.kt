package ru.anishark.app.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalGridItemDecoration(
    private val startPadding: Float,
    private val endPadding: Float,
    private val topPadding: Float,
    private val bottomPadding: Float,
    private val spacingBetweenRows: Float,
    private val spacingBetweenCols: Float,
    private val itemsInColumn: Int,
) : PaddingItemDecoration(startPadding, endPadding, topPadding, bottomPadding) {
    constructor(
        allSidesPadding: Float,
        spacingBetweenRows: Float,
        spacingBetweenCols: Float,
        itemsInColumn: Int,
    ) : this(
        startPadding = allSidesPadding,
        endPadding = allSidesPadding,
        topPadding = allSidesPadding,
        bottomPadding = allSidesPadding,
        spacingBetweenRows = spacingBetweenRows,
        spacingBetweenCols = spacingBetweenCols,
        itemsInColumn = itemsInColumn,
    )

    constructor(
        verticalPadding: Float,
        horizontalPadding: Float,
        spacingBetweenRows: Float,
        spacingBetweenCols: Float,
        itemsInColumn: Int,
    ) : this(
        startPadding = horizontalPadding,
        endPadding = horizontalPadding,
        topPadding = verticalPadding,
        bottomPadding = verticalPadding,
        spacingBetweenRows = spacingBetweenRows,
        spacingBetweenCols = spacingBetweenCols,
        itemsInColumn = itemsInColumn,
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val adapter = parent.adapter ?: return
        outRect.left = getPixelsFromDimensityIndependentPixels(startPadding)
        outRect.right = getPixelsFromDimensityIndependentPixels(endPadding)
        val position = parent.getChildAdapterPosition(view)
        when (position) {
            // Первый ряд
            in 0..<itemsInColumn -> {
                outRect.top = getPixelsFromDimensityIndependentPixels(topPadding)
                outRect.bottom = getPixelsFromDimensityIndependentPixels(spacingBetweenRows) / 2
            }
            // Иной ряд
            else -> {
                outRect.top = getPixelsFromDimensityIndependentPixels(spacingBetweenRows) / 2
                outRect.bottom = getPixelsFromDimensityIndependentPixels(spacingBetweenRows) / 2
            }
        }
    }
}
