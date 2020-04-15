package com.michaeludjiawan.switchlayout.switcher

import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.frame.BasicFrame
import com.michaeludjiawan.switchlayout.frame.DialogFrame
import com.michaeludjiawan.switchlayout.frame.Frame
import com.michaeludjiawan.switchlayout.frame.FrameType

const val STATE_LOADING = "LOADING"

class State(
    val key: String,
    val frame: Frame,
    val layout: ViewGroup
) {

    fun load() {
        frame.load(layout)
    }

    fun finish() {
        frame.unload(layout)
    }

    class Builder(private val parent: Switcher) {
        var key: String = ""
        var layout: ViewGroup = FrameLayout(parent.getContext())
        var frameType: FrameType = FrameType.LAYOUT

        fun build(): State {
            val frame = when (frameType) {
                FrameType.LAYOUT -> BasicFrame()
                FrameType.WINDOW -> DialogFrame(parent)
            }

            return State(
                key,
                frame,
                layout
            )
        }
    }
}