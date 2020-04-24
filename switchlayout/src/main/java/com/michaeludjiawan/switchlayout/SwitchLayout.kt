package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.state.*

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private lateinit var contentState: State

    private val stateLoader: StateLoader = StateLoader(this)

    override fun onFinishInflate() {
        super.onFinishInflate()
        val contentLayout = getChildAt(0) as ViewGroup
        contentLayout.tag = StateConstants.STATE_CONTENT

        contentState = state(context) {
            key = StateConstants.STATE_CONTENT
            layout = contentLayout
        }
        switchToContent()
    }

    fun switch(loadType: LoadType = LoadType.REPLACE, init: State.Builder.() -> Unit) {
        val state = State.Builder(context).apply(init).build()
        switch(loadType, state)
    }

    fun switch(loadType: LoadType = LoadType.REPLACE, state: State) {
        if (loadType == LoadType.REPLACE) {
            stateLoader.unload(mutableState.value)
        }

        stateLoader.load(state)
        mutableState.value = state
    }

    fun switchToContent() {
        switch(state = contentState)
    }

}