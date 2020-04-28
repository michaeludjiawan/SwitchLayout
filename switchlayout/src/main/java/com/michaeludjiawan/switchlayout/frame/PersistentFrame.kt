package com.michaeludjiawan.switchlayout.frame

import android.view.View
import android.view.ViewGroup

class PersistentFrame : Frame {

    override fun load(itemView: ViewGroup) {
        requireNotNull(itemView.parent) {
            "Child parent must not be null."
        }

        itemView.visibility = View.VISIBLE
    }

    override fun unload(itemView: ViewGroup) {
        requireNotNull(itemView.parent) {
            "Child parent must not be null."
        }

        itemView.visibility = View.GONE
    }

}