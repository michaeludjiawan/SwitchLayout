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

fun State.Builder.infoState(infoLayoutAction: DefaultInfoLayout.() -> Unit = {}) {
    layout = DefaultInfoLayout(context)
}

fun State.Builder.errorLayout(errorLayoutAction: DefaultInfoLayout.() -> Unit) {
    key = StateConstants.STATE_ERROR
    layout = DefaultInfoLayout(context).apply(errorLayoutAction)
}

fun State.Builder.emptyLayout(emptyLayoutAction: DefaultInfoLayout.() -> Unit) {
    key = StateConstants.STATE_EMPTY
    layout = DefaultInfoLayout(context).apply(emptyLayoutAction)
}
