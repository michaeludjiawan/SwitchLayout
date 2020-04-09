package com.michaeludjiawan.switchlayout.frame

import android.view.ViewGroup

interface Frame {
    fun load(itemView: ViewGroup)
    fun unload(itemView: ViewGroup)
}

