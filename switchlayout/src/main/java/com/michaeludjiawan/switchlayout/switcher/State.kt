package com.michaeludjiawan.switchlayout.switcher

import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.frame.DialogFrame
import com.michaeludjiawan.switchlayout.frame.Frame

const val STATE_LOADING = "LOADING"

class State(
    private val key: String,
    private val frame: Frame,
    private val layout: ViewGroup
) {

    fun load() {
        frame.load(layout)
    }

    fun finish() {
        frame.unload(layout)
    }

    fun getKey(): String = key

    class Builder(private val parent: Switcher) {
        private var frame: Frame = DialogFrame.Builder(parent).build()

        var key: String = ""
        var layout: ViewGroup = FrameLayout(parent.getContext())

        fun frame(init: DialogFrame.Builder.() -> Unit) {
            val builder = DialogFrame.Builder(parent)
            builder.init()
            frame = builder.build()
        }

        fun build(): State =
            State(
                key,
                frame,
                layout
            )
    }
}