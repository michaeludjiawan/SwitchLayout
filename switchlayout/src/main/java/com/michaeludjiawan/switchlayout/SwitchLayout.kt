package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.state.LoadType
import com.michaeludjiawan.switchlayout.state.State
import com.michaeludjiawan.switchlayout.state.StateConstants

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Switcher {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private val states = mutableListOf<State>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        val contentLayout = getChildAt(0) as ViewGroup
        addState {
            key = StateConstants.STATE_CONTENT
            layout = contentLayout
        }

        switch(StateConstants.STATE_CONTENT)
    }

    fun switch(key: String, loadType: LoadType = LoadType.REPLACE) {
        val selectedState = states.find { it.key == key }

        check(selectedState != null)

        if (selectedState.layout.parent == null) {
            selectedState.layout.visibility = View.GONE
            addView(selectedState.layout)
        }

        check(selectedState.layout.parent == this) {
            "New layout not attached to current layout."
        }

        if (loadType == LoadType.REPLACE) {
            mutableState.value?.finish()
        }

        selectedState.load()

        mutableState.value = selectedState
    }

    fun clear() {
        switch(StateConstants.STATE_CONTENT)
    }

    override fun getChildren(): Sequence<View> = children

    fun addState(builderAction: State.Builder.() -> Unit): SwitchLayout {
        val state = State.Builder(context).apply(builderAction).build()
        states.add(state)
        return this
    }

    fun addState(state: State): SwitchLayout {
        states.add(state)
        return this
    }

}