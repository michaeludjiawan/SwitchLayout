package com.michaeludjiawan.switchlayout.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.michaeludjiawan.switchlayout.R
import com.michaeludjiawan.switchlayout.state.StateDslMarker
import kotlinx.android.synthetic.main.layout_info_default.view.*

@StateDslMarker
class DefaultInfoLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.layout_info_default, this)
        setOnClickListener { /* Block click listener */ }
    }

    fun setImage(imageResId: Int) {
        iv_info_default_image.apply {
            visibility = if (imageResId != 0) {
                View.VISIBLE
            } else {
                View.GONE
            }

            setImageResource(imageResId)
        }

    }

    fun setMessage(message: String) {
        tv_info_default_message.apply {
            visibility = if (message.isNotBlank()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            text = message
        }
    }

    fun setAction(label: String, btnOnClickListener: () -> Unit) {
        btn_info_default_action.apply {
            visibility = View.VISIBLE
            text = label
            setOnClickListener { btnOnClickListener() }
        }

    }

}