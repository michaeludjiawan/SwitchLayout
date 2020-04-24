package com.michaeludjiawan.switchlayout.state

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.frame.BasicFrame
import com.michaeludjiawan.switchlayout.frame.DialogFrame
import com.michaeludjiawan.switchlayout.frame.Frame
import com.michaeludjiawan.switchlayout.frame.FrameType

@DslMarker
annotation class StateDslMarker

class State(
    val key: String,
    val layout: ViewGroup,
    val frame: Frame
) {

    @StateDslMarker
    class Builder(val context: Context) {
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
                layout,
                frame
            )
        }
    }
}