package com.arunasbedzinskas.movio.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearLayoutMarginItemDecoration(
    private val space: Int,
    private val position: Position = Position.End
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val orientation = (parent.layoutManager as? LinearLayoutManager)?.orientation ?: return
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        if (orientation == LinearLayoutManager.VERTICAL) {
            handleVerticalMargin(outRect, childAdapterPosition, itemCount)
        } else {
            handleHorizontalMargin(outRect, childAdapterPosition, itemCount)
        }
    }

    private fun handleVerticalMargin(
        outRect: Rect,
        childAdapterPosition: Int,
        itemCount: Int
    ) {
        when (position) {
            Position.Start -> {
                if (childAdapterPosition == 0) return
                outRect.top = space
            }

            Position.End -> {
                if (childAdapterPosition == itemCount - 1) return
                outRect.bottom = space
            }
        }
    }

    private fun handleHorizontalMargin(
        outRect: Rect,
        childAdapterPosition: Int,
        itemCount: Int
    ) {
        when (position) {
            Position.Start -> {
                if (childAdapterPosition == 0) return
                outRect.left = space
            }

            Position.End -> {
                if (childAdapterPosition == itemCount - 1) return
                outRect.right = space
            }
        }
    }

    enum class Position {
        Start,
        End;
    }
}
