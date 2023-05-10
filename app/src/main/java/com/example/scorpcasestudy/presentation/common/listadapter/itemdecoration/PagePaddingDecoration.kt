package com.example.scorpcasestudy.presentation.common.listadapter.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.scorpcasestudy.presentation.common.extensions.pxFromDp

class PagePaddingDecoration constructor(
    val context: Context
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)

        val pagePadding = 16f

        outRect.top = if(itemPosition == 0) pagePadding.pxFromDp(context).toInt() else 0
        outRect.bottom = pagePadding.pxFromDp(context).toInt()

        outRect.left = pagePadding.pxFromDp(context).toInt()
        outRect.right = pagePadding.pxFromDp(context).toInt()

    }
}