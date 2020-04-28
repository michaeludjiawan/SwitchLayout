package com.michaeludjiawan.switchlayout.state

import android.view.ViewGroup
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.SwitchLayout
import com.michaeludjiawan.switchlayout.loader.*

@DslMarker
annotation class StateDslMarker

class State(
    val key: String,
    val layout: ViewGroup,
    val loader: Loader
) {

    fun load() {
        loader.load(layout)
    }

    fun unload() {
        loader.unload(layout)
    }

    @StateDslMarker
    class Builder(val parent: SwitchLayout) {
        var key: String = ""
        var layout: ViewGroup = FrameLayout(parent.context)
        var loaderType: LoaderType = LoaderType.DEFAULT

        fun build(): State {
            val loader = when (loaderType) {
                LoaderType.DEFAULT -> DefaultLoader(parent)
                LoaderType.WINDOW -> DialogLoader(parent.context)
                LoaderType.PERSISTENT -> PersistentLoader()
            }

            return State(
                key,
                layout,
                loader
            )
        }
    }
}