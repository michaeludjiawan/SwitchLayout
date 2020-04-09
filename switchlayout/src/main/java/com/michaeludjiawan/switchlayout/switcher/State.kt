package com.michaeludjiawan.switchlayout.switcher

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.frame.DialogFrame
import com.michaeludjiawan.switchlayout.frame.Frame

const val STATE_LOADING = "LOADING"

class State(
    private val parent: Switcher,
    private val key: String,
    private val frame: Frame,
    private val layout: ViewGroup,
    private val existingStateVisibility: Int
) {

    fun load() {
        parent.updateContentVisibility(existingStateVisibility)
        frame.load(layout)
    }

    fun finish() {
        parent.resetContentVisibility()
        frame.unload(layout)
    }

    fun getKey(): String = key

    class Builder(private val parent: Switcher) {
        private var frame: Frame = DialogFrame.Builder(parent).build()

        var key: String = ""
        var layout: ViewGroup = FrameLayout(parent.getContext())
        var existingStateVisibility: Int = View.GONE

        fun frame(init: DialogFrame.Builder.() -> Unit) {
            val builder = DialogFrame.Builder(parent)
            builder.init()
            frame = builder.build()
        }

        fun build(): State =
            State(
                parent,
                key,
                frame,
                layout,
                existingStateVisibility
            )
    }
}