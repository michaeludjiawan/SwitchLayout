package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.state.LoadType
import com.michaeludjiawan.switchlayout.state.State
import com.michaeludjiawan.switchlayout.state.StateConstants
import com.michaeludjiawan.switchlayout.state.StatesBuilder

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private val states = HashMap<String, State>()

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
        val selectedState = states[key]

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

    fun switchToContent() {
        switch(StateConstants.STATE_CONTENT)
    }

    fun addState(builderAction: State.Builder.() -> Unit) {
        val state = State.Builder(context).apply(builderAction).build()
        addState(state)
    }

    fun addState(state: State) {
        states[state.key] = state
    }

    fun addStates(builderAction: StatesBuilder.() -> Unit) {
        addStates(StatesBuilder(context).apply(builderAction).build())
    }

    fun addStates(states: HashMap<String, State>) {
        this.states.putAll(states)
    }
}