package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.switcher.LoadType
import com.michaeludjiawan.switchlayout.switcher.State
import com.michaeludjiawan.switchlayout.switcher.Switcher
import com.michaeludjiawan.switchlayout.switcher.state

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Switcher {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private lateinit var contentState: State

    override fun onFinishInflate() {
        super.onFinishInflate()
        val contentLayout = getChildAt(0) as ViewGroup
        contentState = state {
            layout = contentLayout
        }
        switch { contentState }
    }

    fun switch(loadType: LoadType = LoadType.REPLACE, action: Switcher.() -> State) {
        val newState = action()

        if (newState.layout.parent == null) {
            newState.layout.visibility = View.GONE
            addView(newState.layout)
        }

        check(newState.layout.parent == this) {
            "New layout not attached to current layout."
        }

        if (loadType == LoadType.REPLACE) {
            mutableState.value?.finish()
        }

        newState.load()

        mutableState.value = newState
    }

    fun clear() {
        switch { contentState }
    }

    override fun getChildren(): Sequence<View> = children

}