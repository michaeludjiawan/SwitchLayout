package com.michaeludjiawan.switchlayout.state

import android.content.Context
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.DefaultInfoLayout
import com.michaeludjiawan.switchlayout.layout.DefaultLoadingItem

fun state(context: Context, builderAction: State.Builder.() -> Unit): State =
    State.Builder(context).apply(builderAction).build()

fun State.Builder.loadingState(builderAction: (State.Builder.() -> Unit) = {}) {
    key = StateConstants.STATE_LOADING
    layout = DefaultLoadingItem(context)
    builderAction()
}

fun State.Builder.loadingFullScreenState(builderAction: (State.Builder.() -> Unit) = {}) = loadingState {
    key = StateConstants.STATE_LOADING_FULL
    frameType = FrameType.WINDOW
    builderAction()
}

fun State.Builder.infoState(builderAction: State.Builder.() -> Unit = {}) {
    layout = DefaultInfoLayout(context)
    builderAction()
}
