package com.michaeludjiawan.switchlayout.switcher

import android.content.Context
import com.michaeludjiawan.switchlayout.frame.FrameType
import com.michaeludjiawan.switchlayout.layout.DefaultInfoLayout
import com.michaeludjiawan.switchlayout.layout.DefaultLoadingItem

fun state(context: Context, builderAction: State.Builder.() -> Unit): State =
    State.Builder(context).apply(builderAction).build()

fun loadingState(context: Context, builderAction: (State.Builder.() -> Unit) = {}): State = state(context) {
    key = StateConstants.STATE_LOADING
    layout = DefaultLoadingItem(context)
    builderAction()
}

fun loadingFullScreenState(context: Context, builderAction: (State.Builder.() -> Unit) = {}) = loadingState(context) {
    key = StateConstants.STATE_LOADING_FULL
    frameType = FrameType.WINDOW
    builderAction()
}

fun infoState(context: Context, builderAction: State.Builder.() -> Unit = {}): State = state(context) {
    layout = DefaultInfoLayout(context)
    builderAction()
}
