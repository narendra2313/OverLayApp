package com.si.overlayapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpacingDecoration(private val verticalOffset: Int, firstItemTopOffset: Int? = null, lastItemBottomOffset: Int? = null) : RecyclerView.ItemDecoration() {

    private val mFirstItemTopOffset = firstItemTopOffset ?: verticalOffset
    private val mLastItemBottomOffset = lastItemBottomOffset ?: verticalOffset

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        outRect.top = if (position == 0)
            mFirstItemTopOffset
        else
            verticalOffset

        if (position == itemCount - 1)
            outRect.bottom = mLastItemBottomOffset
    }
}