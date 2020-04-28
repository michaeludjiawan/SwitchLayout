package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.loader.LoaderType
import com.michaeludjiawan.switchlayout.state.State
import com.michaeludjiawan.switchlayout.state.StateConstants
import com.michaeludjiawan.switchlayout.state.SwitchType
import com.michaeludjiawan.switchlayout.state.state

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private lateinit var contentState: State

    override fun onFinishInflate() {
        super.onFinishInflate()
        val contentLayout = getChildAt(0) as ViewGroup
        contentLayout.tag = StateConstants.STATE_CONTENT

        contentState = state(this) {
            key = StateConstants.STATE_CONTENT
            layout = contentLayout
            loaderType = LoaderType.PERSISTENT
        }
        switchToContent()
    }

    fun replace(init: State.Builder.() -> Unit) {
        val state = state(this) { init() }
        replace(state)
    }

    fun replace(state: State) {
        switch(SwitchType.REPLACE, state)
    }

    fun add(init: State.Builder.() -> Unit) {
        val state = state(this) { init() }
        add(state)
    }

    fun add(state: State) {
        switch(SwitchType.ADD, state)
    }

    fun switch(switchType: SwitchType = SwitchType.REPLACE, state: State) {
        if (switchType == SwitchType.REPLACE) {
            mutableState.value?.unload()
        }

        state.load()
        mutableState.value = state
    }

    fun switchToContent() {
        replace(contentState)
    }

}