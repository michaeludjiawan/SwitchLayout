package com.michaeludjiawan.switchlayout.state

import com.michaeludjiawan.switchlayout.SwitchLayout
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.DefaultInfoLayout
import com.michaeludjiawan.switchlayout.layout.DefaultLoadingItem

fun state(parent: SwitchLayout, builderAction: State.Builder.() -> Unit): State =
    State.Builder(parent).apply(builderAction).build()

fun State.Builder.loadingState() {
    key = StateConstants.STATE_LOADING
    layout = DefaultLoadingItem(parent.context)
}

fun State.Builder.loadingFullScreenState() {
    key = StateConstants.STATE_LOADING_FULL
    layout = DefaultLoadingItem(parent.context)
    frameType = FrameType.WINDOW
}

fun State.Builder.errorState(errorLayoutAction: DefaultInfoLayout.() -> Unit = {}) {
    key = StateConstants.STATE_ERROR
    layout = DefaultInfoLayout(parent.context).apply(errorLayoutAction)
}

fun State.Builder.emptyState(emptyLayoutAction: DefaultInfoLayout.() -> Unit = {}) {
    key = StateConstants.STATE_EMPTY
    layout = DefaultInfoLayout(parent.context).apply(emptyLayoutAction)
}