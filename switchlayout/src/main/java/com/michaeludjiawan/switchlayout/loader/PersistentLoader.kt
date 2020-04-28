package com.michaeludjiawan.switchlayout.loader

import android.view.View
import android.view.ViewGroup

class PersistentLoader : Loader {

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