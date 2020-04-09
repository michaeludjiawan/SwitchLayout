package com.michaeludjiawan.switchlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michaeludjiawan.switchlayout.switcher.LoadType
import com.michaeludjiawan.switchlayout.switcher.State
import com.michaeludjiawan.switchlayout.switcher.Switcher

class SwitchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Switcher {

    private val mutableState = MutableLiveData<State?>()
    val stateLiveData: LiveData<State?> = mutableState

    private val initialVisibility = HashMap<View, Int>()

    fun switch(loadType: LoadType = LoadType.REPLACE, action: Switcher.() -> State) {
        if (loadType == LoadType.REPLACE) {
            mutableState.value?.finish()
        }

        val newState = action()
        newState.load()

        mutableState.value = newState
    }

    fun clear() {
        mutableState.value?.finish()
        mutableState.value = null
    }

    override fun getChildren(): Sequence<View> = children

    override fun updateContentVisibility(contentVisibility: Int) {
        children.forEach { child ->
            initialVisibility[child] = child.visibility
            child.visibility = contentVisibility
        }
    }

    override fun resetContentVisibility() {
        children.forEach { child ->
            initialVisibility[child]?.let { visibility ->
                child.visibility = visibility
            }
        }

        initialVisibility.clear()
    }
}