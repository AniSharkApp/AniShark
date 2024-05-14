package ru.anishark.app.common.ui

import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

abstract class SpacingItemDecoration(
    private val startPadding: Float,
    private val endPadding: Float,
    private val topPadding: Float,
    private val bottomPadding: Float,
) : RecyclerView.ItemDecoration() {
    private val metrics: DisplayMetrics

    init {
        metrics = Resources.getSystem().displayMetrics
    }

    constructor(
        allSidesPadding: Float
    ) : this(
        startPadding = allSidesPadding,
        endPadding = allSidesPadding,
        topPadding = allSidesPadding,
        bottomPadding = allSidesPadding
    )

    constructor(
        verticalPadding: Float, horizontalPadding: Float
    ) : this(
        startPadding = horizontalPadding,
        endPadding = horizontalPadding,
        topPadding = verticalPadding,
        bottomPadding = verticalPadding
    )


    protected fun getPixelsFromDimensityIndependentPixels(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics).roundToInt()
    }
}