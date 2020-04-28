package com.michaeludjiawan.switchlayout.state

import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.SwitchLayout
import com.michaeludjiawan.switchlayout.frame.*

@DslMarker
annotation class StateDslMarker

class State(
    val key: String,
    val layout: ViewGroup,
    val frame: Frame
) {

    fun load() {
        frame.load(layout)
    }

    fun unload() {
        frame.unload(layout)
    }

    @StateDslMarker
    class Builder(val parent: SwitchLayout) {
        var key: String = ""
        var layout: ViewGroup = FrameLayout(parent.context)
        var frameType: FrameType = FrameType.LAYOUT

        fun build(): State {
            val frame = when (frameType) {
                FrameType.LAYOUT -> BasicFrame(parent)
                FrameType.WINDOW -> DialogFrame(parent.context)
                FrameType.PERSISTENT -> PersistentFrame()
            }

            return State(
                key,
                layout,
                frame
            )
        }
    }
}