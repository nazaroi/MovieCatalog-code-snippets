package com.nazaroi.base.recyclerview.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nazaroi.base.ktx.dpToPx

class HorizontalPaddingDecoration(
    private val context: Context,
    private val startPaddingDp: Float,
    private val endPaddingDp: Float = startPaddingDp
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        outRect.left = if (position == 0) startPaddingDp.dpToPx(context) else 0
        outRect.right = if (position == state.itemCount - 1) endPaddingDp.dpToPx(context) else 0
    }
}