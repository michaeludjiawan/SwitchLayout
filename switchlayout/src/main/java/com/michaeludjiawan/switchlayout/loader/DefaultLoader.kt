package com.michaeludjiawan.switchlayout.loader

import android.view.View
import android.view.ViewGroup

class DefaultLoader(private val parent: ViewGroup) : Loader {

    override fun load(itemView: ViewGroup) {
        when {
            itemView.parent == null -> {
                parent.addView(itemView)
            }
            itemView.parent != parent -> {
                (itemView.parent as ViewGroup).removeView(itemView)
                parent.addView(itemView)
            }
        }

        itemView.visibility = View.VISIBLE
    }

    override fun unload(itemView: ViewGroup) {
        parent.removeView(itemView)

        itemView.visibility = View.GONE
    }
}