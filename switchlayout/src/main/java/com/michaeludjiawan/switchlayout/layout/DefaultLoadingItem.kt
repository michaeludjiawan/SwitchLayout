package com.michaeludjiawan.switchlayout.layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.graphics.ColorUtils

class DefaultLoadingItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val loaderView: View = ProgressBar(context)

    init {
        setOnClickListener { /*Do Nothing. Added to block children's click listener*/ }
        setBackgroundAlpha(0)
        addLoaderView()
    }

    fun setBackgroundAlpha(alpha: Int) {
        val bgColor = ColorUtils.setAlphaComponent(Color.BLACK, alpha)
        setBackgroundColor(bgColor)
    }

    private fun addLoaderView() {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        loaderView.layoutParams = params

        addView(loaderView)
    }
}