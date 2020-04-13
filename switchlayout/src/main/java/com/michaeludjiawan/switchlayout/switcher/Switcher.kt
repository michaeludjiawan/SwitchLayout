package com.michaeludjiawan.switchlayout.switcher

import android.content.Context
import android.view.View

interface Switcher {
    fun getId(): Int
    fun getContext(): Context
    fun getChildren(): Sequence<View>
}