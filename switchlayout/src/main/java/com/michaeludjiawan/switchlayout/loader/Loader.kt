package com.michaeludjiawan.switchlayout.loader

import android.view.ViewGroup

interface Loader {
    fun load(itemView: ViewGroup)
    fun unload(itemView: ViewGroup)
}

