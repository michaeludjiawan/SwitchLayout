package com.michaeludjiawan.switchlayout.state

import android.content.Context
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.DefaultInfoLayout
import com.michaeludjiawan.switchlayout.layout.DefaultLoadingItem

fun state(context: Context, builderAction: State.Builder.() -> Unit): State =
    State.Builder(context).apply(builderAction).build()

fun State.Builder.loadingState() {
    key = StateConstants.STATE_LOADING
    layout = DefaultLoadingItem(context)
}

fun State.Builder.loadingFullScreenState() {
    key = StateConstants.STATE_LOADING_FULL
    layout = DefaultLoadingItem(context)
    frameType = FrameType.WINDOW
}

fun State.Builder.errorState(errorLayoutAction: DefaultInfoLayout.() -> Unit = {}) {
    key = StateConstants.STATE_ERROR
    layout = DefaultInfoLayout(context).apply(errorLayoutAction)
}

fun State.Builder.emptyState(emptyLayoutAction: DefaultInfoLayout.() -> Unit = {}) {
    key = StateConstants.STATE_EMPTY
    layout = DefaultInfoLayout(context).apply(emptyLayoutAction)
}