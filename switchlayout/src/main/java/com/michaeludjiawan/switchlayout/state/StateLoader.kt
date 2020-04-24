package com.michaeludjiawan.switchlayout.state

import android.view.View
import android.view.ViewGroup
import com.michaeludjiawan.switchlayout.SwitchLayout

class StateLoader(private val parent: SwitchLayout) {

    fun load(state: State) {
        updateView(state.key, state.layout)
        state.frame.load(state.layout)
    }

    fun unload(state: State?) {
        state ?: return

        state.frame.unload(state.layout)
    }

    private fun updateView(key: String, layout: ViewGroup) {
        val oldLayout: ViewGroup? = parent.findViewWithTag(key)

        if (layout == oldLayout) return

        layout.visibility = View.GONE
        layout.tag = key

        parent.removeView(oldLayout)
        parent.addView(layout)
    }

}