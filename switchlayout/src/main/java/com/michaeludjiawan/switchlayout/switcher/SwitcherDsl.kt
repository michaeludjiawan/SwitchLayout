package com.michaeludjiawan.switchlayout.switcher

import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.DefaultInfoLayout
import com.michaeludjiawan.switchlayout.layout.DefaultLoadingItem

fun Switcher.state(builderAction: State.Builder.() -> Unit): State =
    State.Builder(this).apply(builderAction).build()

fun Switcher.loadingState(builderAction: (State.Builder.() -> Unit) = {}): State = state {
    key = STATE_LOADING
    layout = DefaultLoadingItem(getContext())
    builderAction()
}

fun Switcher.loadingFullScreenState(builderAction: (State.Builder.() -> Unit) = {}) = loadingState {
    frame {
        frameType = FrameType.WINDOW
    }
    builderAction()
}

fun Switcher.infoState(builderAction: State.Builder.() -> Unit = {}): State = state {
    layout = DefaultInfoLayout(getContext())
    builderAction()
}
