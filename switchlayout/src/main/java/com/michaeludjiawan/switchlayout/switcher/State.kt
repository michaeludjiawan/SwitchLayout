package com.michaeludjiawan.switchlayout.switcher

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.frame.BasicFrame
import com.michaeludjiawan.switchlayout.frame.DialogFrame
import com.michaeludjiawan.switchlayout.frame.Frame
import com.michaeludjiawan.switchlayout.frame.FrameType

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

    class Builder(private val context: Context) {
        var key: String = ""
        var layout: ViewGroup = FrameLayout(context)
        var frameType: FrameType = FrameType.LAYOUT

        fun build(): State {
            val frame = when (frameType) {
                FrameType.LAYOUT -> BasicFrame()
                FrameType.WINDOW -> DialogFrame(context)
            }

            return State(
                key,
                frame,
                layout
            )
        }
    }
}