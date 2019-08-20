package com.example.movieapp.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(val hor : Int , val ver : Int) : RecyclerView.ItemDecoration(){


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = hor
        outRect.bottom = ver
    }
}